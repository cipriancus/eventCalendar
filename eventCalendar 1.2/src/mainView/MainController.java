package mainView;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import detect.Detect;
import detect.Email;
import detect.Event;
import detect.GiveMeDateAndTime;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.Agenda.AppointmentImplLocal;
import parseXML.AllEventsXMLParser;
import parseXML.AllMandatoryXMLParser;
import parseXML.AllOptionalXMLParser;
import parseXML.EmailXMLParser;

public class MainController implements Initializable {
	private List<Event> allEvents = new ArrayList<Event>();
	private Map<String, String> allEmails = new HashMap<>();
	private List<String> mandatory = new ArrayList<String>();
	private List<String> optional = new ArrayList<String>();

	@FXML
	private Button exit;
	@FXML
	private Button settings;
	@FXML
	private Button email;
	@FXML
	private AnchorPane calendarMenu;
	@FXML
	private AnchorPane agendaDay;
	@FXML
	private VBox leftAllEvents;
	@FXML
	private Button settings_save_button;

	@FXML
	private VBox mandatoryVB;
	@FXML
	private VBox optionalVB;
	@FXML
	private TextField mandatoryItemText;
	@FXML
	private TextField optionalItemText;
	@FXML
	private ToggleButton mandatoryAddButton;
	@FXML
	private ToggleButton optionalAddButton;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		prepare();

		EmailXMLParser emailParser = new EmailXMLParser();
		emailParser.deserialise();
		this.allEmails = emailParser.getEmails();

		StringBuilder mandatoryString = new StringBuilder();
		for (String iterator : mandatory) {
			mandatoryString.append(iterator);
			mandatoryString.append(' ');
		}

		StringBuilder optionalString = new StringBuilder();
		for (String iterator : optional) {
			optionalString.append(iterator);
			optionalString.append(' ');
		}

		for (String email : allEmails.keySet()) {
			Detect detect = new Detect(email, allEmails.get(email), 10, mandatoryString.toString(),
					optionalString.toString(), 100);
			allEvents.addAll(detect.getEvents());
		}

		AllEventsXMLParser eventParser = new AllEventsXMLParser();
		eventParser.serialise(allEvents);
		eventParser.deserialise();
		this.allEvents = eventParser.getEvents();

		AllMandatoryXMLParser mandatoryParser = new AllMandatoryXMLParser();
		mandatoryParser.deserialise();
		mandatory.addAll(mandatoryParser.getMandatory());

		for (String iterator : mandatory) {
			Text text = new Text();
			text.setText(iterator);
			mandatoryVB.getChildren().add(text);
		}

		AllOptionalXMLParser optionalParser = new AllOptionalXMLParser();
		optionalParser.deserialise();
		optional.addAll(optionalParser.getOptional());

		for (String iterator : optional) {
			Text text = new Text();
			text.setText(iterator);
			optionalVB.getChildren().add(text);
		}

		for (Event iterator : allEvents) {
			Text text = new Text();
			text.setText(iterator.toString());
			leftAllEvents.getChildren().add(text);
		}

	}

	private void prepare() {
		/*
		 * obligatorii: data, locatie, organizatie, timp, etc optionale: data,
		 * locatie, organizatie
		 */

		this.getEventList();

		CalendarPicker viewCalendar = new CalendarPicker();
		viewCalendar.setMinSize(1000, 0);
		calendarMenu.getChildren().add(viewCalendar);

		Agenda agenda = new Agenda();
		agenda.setMinSize(1000, 0);
		agendaDay.getChildren().add(agenda);

		viewCalendar.calendarProperty().addListener(e -> {
			@SuppressWarnings("deprecation")
			String selectedDate = viewCalendar.calendarProperty().getValue().getTime().toLocaleString();
			agenda.appointments().clear();
			LocalDateTime time;

			StringBuilder selected = new StringBuilder();
			if (selectedDate.charAt(5) == ',') {// e data du dd=1
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm");
				time = LocalDateTime.parse(selectedDate.substring(0, 17), formatter);
				selected.append(selectedDate.substring(0, 12));
				selected.insert(4, '0');
			} else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
				time = LocalDateTime.parse(selectedDate.substring(0, 18), formatter);
				selected.append(selectedDate.substring(0, 12));
			}

			agenda.setDisplayedLocalDateTime(time);

			for (Event event : allEvents) {
				if (equals(selected.toString(), event.getDateCalendar()) == true) {
					StringBuilder eventDate = new StringBuilder();

					int hour;
					int minute;

					if (event.getTime() != null) {
						eventDate.append(event.getDateCalendar());
						eventDate.append(" ");
						eventDate.append(event.getTime());

						hour = Integer.parseInt(event.getTime().substring(0, 2));
						minute = Integer.parseInt(event.getTime().substring(3));
					} else {
						eventDate.append(event.getDateCalendar());
						eventDate.append(" ");
						eventDate.append("08:00");
						hour = 8;
						minute = 0;
					}

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm");
					LocalDate eventTime = LocalDate.parse(eventDate.toString(), formatter);

					Appointment lTestAppointments = new Agenda.AppointmentImplLocal()

							.withStartLocalDateTime(LocalDateTime.of(eventTime, LocalTime.of(hour, minute)))

							.withEndLocalDateTime(LocalDateTime.of(eventTime, LocalTime.of(hour + 2, minute)))

							.withSummary(event.getSubject())

							.withDescription(event.getSubject() + " " + event.getPersons());
					agenda.appointments().add(lTestAppointments);
				}
			}
		});

		exit.setOnAction((

				ActionEvent evt) -> {
			System.exit(0);
		});

		email.setOnAction((ActionEvent evt) -> {
			Stage stage = (Stage) calendarMenu.getScene().getWindow();
			Scene scene = calendarMenu.getScene();

			AnchorPane root;
			try {
				root = FXMLLoader.load(getClass().getResource("../email.fxml"));
				root.getStylesheets().add("MainView.css");
				scene.setRoot(root);
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		settings.setOnAction((ActionEvent evt) -> {
			Stage stage = (Stage) calendarMenu.getScene().getWindow();
			Scene scene = calendarMenu.getScene();

			AnchorPane root;
			try {
				root = FXMLLoader.load(getClass().getResource("../settings.fxml"));
				root.getStylesheets().add("MainView.css");
				scene.setRoot(root);
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		mandatoryAddButton.setOnAction((ActionEvent evt) -> {
			if (mandatoryItemText.getText() != null && mandatory.contains(mandatoryItemText.getText()) == false) {
				mandatory.add(mandatoryItemText.getText());
				Text text = new Text();
				text.setText(mandatoryItemText.getText());
				mandatoryVB.getChildren().add(text);

			}
		});

		optionalAddButton.setOnAction((ActionEvent evt) -> {
			if (optionalItemText.getText() != null && optional.contains(optionalItemText.getText()) == false) {
				optional.add(optionalItemText.getText());
				Text text = new Text();
				text.setText(optionalItemText.getText());
				optionalVB.getChildren().add(text);
			}
		});

		settings_save_button.setOnAction((ActionEvent evt) -> {
			// serializare XML pentru mandatory si optional

			AllMandatoryXMLParser mandatoryParserXML = new AllMandatoryXMLParser();
			mandatoryParserXML.serialise(mandatory);

			AllOptionalXMLParser optionalParserXML = new AllOptionalXMLParser();
			optionalParserXML.serialise(optional);
		});
	}

	private void getEventList() {
		AllEventsXMLParser parser = new AllEventsXMLParser();
		parser.deserialise();
		this.allEvents = parser.getEvents();

	}

	private boolean equals(String selected, String event) {
		if (selected != null && event != null) {
			for (int iterator = 0; iterator < event.length(); iterator++) {
				if (selected.charAt(iterator) != event.charAt(iterator))
					return false;
			}
			return true;
		} else {
			return false;
		}
	}

}