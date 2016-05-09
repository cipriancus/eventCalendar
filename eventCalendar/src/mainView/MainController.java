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
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.Agenda.AppointmentImplLocal;
import parseXML.AllEventsXMLParser;
import parseXML.EmailXMLParser;

public class MainController implements Initializable {
	private List<Event> allEvents = new ArrayList<Event>();
	private Map<String, String> allEmails = new HashMap<>();
	
	@FXML
	private ToggleButton menu;
	@FXML
	private AnchorPane navList;
	@FXML
	private TextArea currentDate;
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

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		prepareSlideMenuAnimation();
		
		EmailXMLParser emailParser = new EmailXMLParser();
		emailParser.deserialise();
		this.allEmails = emailParser.getEmails();
		
		for(String email:allEmails.keySet()){
			Detect detect=new Detect(email, allEmails.get(email),10, "","keile optionale",0);
			allEvents.addAll(detect.getEvents());
		}

		AllEventsXMLParser eventParser=new AllEventsXMLParser();
		eventParser.serialise(allEvents);
		eventParser.deserialise();
		this.allEvents=eventParser.getEvents();
	}

	private void prepareSlideMenuAnimation() {
		TranslateTransition openNav = new TranslateTransition(new Duration(350), navList);
		openNav.setToX(0);
		TranslateTransition closeNav = new TranslateTransition(new Duration(350), navList);

		this.getEventList();

		currentDate.setText(getDate());
		CalendarPicker viewCalendar = new CalendarPicker();
		viewCalendar.setMinSize(1000, 0);
		calendarMenu.getChildren().add(viewCalendar);

		Agenda agenda = new Agenda();
		agenda.setMinSize(1000, 0);
		// agenda.set
		agendaDay.getChildren().add(agenda);

		menu.setOnAction((ActionEvent evt) -> {
			if (navList.getTranslateX() != 0) {
				openNav.play();
			} else {
				closeNav.setToX(-(navList.getWidth()));
				closeNav.play();
			}
		});

		viewCalendar.calendarProperty().addListener(e -> {
			//agenda.refresh();
			
			@SuppressWarnings("deprecation")
			String selectedDate = viewCalendar.calendarProperty().getValue().getTime().toLocaleString();
			String selected = selectedDate.substring(0, 12);
			for (Event event : allEvents) {
				if (event.getDate().equals(selected)) {
					StringBuilder eventDate = new StringBuilder();
					eventDate.append(event.getDate());
					eventDate.append(" ");
					eventDate.append(event.getTime());
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
					LocalDate eventTime = LocalDate.parse(eventDate.toString(), formatter);
					
					int hour;
					int minute;
					hour=Integer.parseInt(event.getTime().substring(0, 2));
					minute=Integer.parseInt(event.getTime().substring(3));
										
					Appointment[] lTestAppointments = new Appointment[] {

							new Agenda.AppointmentImplLocal()

									.withStartLocalDateTime(LocalDateTime.of(eventTime, LocalTime.of(hour, minute)))

									.withEndLocalDateTime(LocalDateTime.of(eventTime, LocalTime.of(hour+2, minute)))

									.withSummary(event.getSubject())

									.withDescription(event.getSubject() + " " + event.getPersons())

					};
					agenda.appointments().addAll(lTestAppointments);
				}
			}
		});

		exit.setOnAction((

				ActionEvent evt) -> {
			System.exit(0);
		});

		email.setOnAction((ActionEvent evt) -> {
			Stage stage = (Stage) navList.getScene().getWindow();
			Scene scene = navList.getScene();

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
			Stage stage = (Stage) navList.getScene().getWindow();
			Scene scene = navList.getScene();

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
	}

	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd   MM   yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private void getEventList() {
		AllEventsXMLParser parser = new AllEventsXMLParser();
		parser.deserialise();
		this.allEvents = parser.getEvents();
	}

}