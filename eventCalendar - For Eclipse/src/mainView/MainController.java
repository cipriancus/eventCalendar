package mainView;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import model.*;

import detect.Detect;
import detect.Email;
import detect.Event;
import detect.GetEventsThread;
import detect.GiveMeDateAndTime;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.Agenda.AppointmentImplLocal;
import parseXML.AllEventTypesXMLParser;
import parseXML.AllEventsXMLParser;
import parseXML.EmailXMLParser;

/**
 * Controller pentru clasa view principala, mainView.fxml
 * 
 * @author Ciprian Cusmuliuc
 *
 */
public class MainController implements Initializable {
	private List<Event> allEvents = new ArrayList<Event>();
	private List<EmailAddress> allEmails = new ArrayList<EmailAddress>();
	private List<EventType> allEventTypes = new ArrayList<EventType>();
	private EventType newEvent = new EventType();
	private int threads = 1;

	@FXML
	private VBox allEventsFound;
	@FXML
	private Button exit;
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
	private Button reparseButton;

	@FXML
	VBox currentEvent;
	@FXML
	Button clear_all;
	@FXML
	Button erase_last_atribute;
	@FXML
	VBox allEventTypesVBox;
	@FXML
	Text errorList;
	@FXML
	Text parseMessage;

	@FXML
	TextField eventName;

	@FXML
	ToggleButton dateAddButton;
	@FXML
	CheckBox dateMandatory;
	@FXML
	CheckBox dateOptional;
	@FXML
	CheckBox dateMultiple;
	@FXML
	CheckBox dateBetween;
	@FXML
	DatePicker dateBetweenStart;
	@FXML
	DatePicker dateBetweenFinish;

	@FXML
	ToggleButton timeAddButton;
	@FXML
	CheckBox timeMandatory;
	@FXML
	CheckBox timeOptional;
	@FXML
	CheckBox timeMultiple;
	@FXML
	CheckBox timeBetween;
	@FXML
	TextField timeBetweenStart;
	@FXML
	TextField timeBetweenFinish;

	@FXML
	ToggleButton nameAddButton;
	@FXML
	CheckBox nameMandatory;
	@FXML
	CheckBox nameOptional;
	@FXML
	CheckBox nameMultiple;
	@FXML
	TextField nameList;

	@FXML
	ToggleButton orgAddButton;
	@FXML
	CheckBox orgMandatory;
	@FXML
	CheckBox orgOptional;
	@FXML
	CheckBox orgMultiple;
	@FXML
	TextField orgList;

	@FXML
	ToggleButton locationAddButton;
	@FXML
	CheckBox locationMandatory;
	@FXML
	CheckBox locationOptional;
	@FXML
	CheckBox locationMultiple;
	@FXML
	TextField locationList;

	@FXML
	TextField mandatoryWordsList;
	@FXML
	TextField percentageWords;
	@FXML
	ToggleButton mandatoryWordsAddButton;
	@FXML
	VBox leftEmailList;
	@FXML
	Button contactUs;
	@FXML 
	Text allEventsFoundText;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		prepareSlideMenuAnimation();

		EmailXMLParser emailParser = new EmailXMLParser();
		emailParser.deserialise();
		this.allEmails = emailParser.getEmails();

		for (EmailAddress iterator : allEmails) {
			Text text = new Text();
			text.setText(iterator.toString());
			leftEmailList.getChildren().add(text);
		}

		/*--------------------------PARSAREA MAIL-URILOR, APELARE DE DETECTION----------------------*/
		Task<List<Event>> task = new Task<List<Event>>() {

			@Override
			protected List<Event> call() throws Exception {
				threads = 0;
				parseMessage.setText("Initial parsing has started, please wait");
				GetEventsThread getEvents = new GetEventsThread(allEvents, allEmails, allEventTypes);
				getEvents.run();
				return getEvents.getEvents();
			}

		};

		task.setOnSucceeded(e -> {
			allEvents = task.getValue();
			threads = 1;
			parseMessage.setText("Initial parsing is finished");
			refresh();// se face refresh cu noile evenimente
		});
		new Thread(task).start();
		/*--------------------------------------------------------------------*/

		AllEventsXMLParser eventParser = new AllEventsXMLParser();
		eventParser.deserialise();
		this.allEvents = eventParser.getEvents();

		for (Event iterator : allEvents) {
			leftAllEvents.getChildren().add(new Text(iterator.toString()));
		}
	}

	private void prepareSlideMenuAnimation() {
		this.getEventList();

		/*---------------------------IAU TOATE EVENIMENTELE DIN XML SI LE PUN IN INTERFATA*/
		AllEventTypesXMLParser parserD = new AllEventTypesXMLParser();
		parserD.deserialise();
		allEventTypes.addAll(parserD.getEvents());

		for (EventType iterator : allEventTypes) {
			allEventTypesVBox.getChildren().add(new Text(iterator.toString()));
		}

		/*---------------------------------------------------------------*/

		/*-----------------INSTANTIEZ CALENDARUL SI AGENDA-------------*/
		CalendarPicker viewCalendar = new CalendarPicker();
		viewCalendar.setMinSize(1000, 0);
		calendarMenu.getChildren().add(viewCalendar);
		viewCalendar.setLocale(java.util.Locale.ENGLISH);/***/

		Agenda agenda = new Agenda();
		agenda.setMinSize(1000, 0);
		agenda.setLocale(java.util.Locale.ENGLISH);/***/
		agendaDay.getChildren().add(agenda);
		
		allEventsFoundText.setText("Events Found: "+allEvents.size());

		/*---------------------------------------------------------*/

		for (Event iterator : allEvents) {
			allEventsFound.getChildren().add(new Text(iterator.toStringFull()));
		}

		viewCalendar.calendarProperty().addListener(e -> {
			@SuppressWarnings("deprecation")
			StringBuilder selectedDateSB = new StringBuilder();

			DateFormatSymbols date = DateFormatSymbols.getInstance(java.util.Locale.ENGLISH);
			selectedDateSB.append(
					date.getMonths()[viewCalendar.calendarProperty().getValue().getTime().getMonth()].substring(0, 3));
			selectedDateSB.append(' ');
			selectedDateSB.append(viewCalendar.calendarProperty().getValue().getTime().getDate());
			selectedDateSB.append(", ");
			selectedDateSB.append(1900 + viewCalendar.calendarProperty().getValue().getTime().getYear());
			selectedDateSB.append(' ');
			selectedDateSB.append("00:00");
			String selectedDate = selectedDateSB.toString();
			LocalDateTime time;

			StringBuilder selected = new StringBuilder();
			if (selectedDate.charAt(5) == ',') {// e data du gasit prb
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm",
						java.util.Locale.ENGLISH);
				time = LocalDateTime.parse(selectedDate.substring(0, 17), formatter);
				selected.append(selectedDate.substring(0, 12));
				selected.insert(4, '0');
			} else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm",
						java.util.Locale.ENGLISH);
				time = LocalDateTime.parse(selectedDate.substring(0, 18), formatter);
				selected.append(selectedDate.substring(0, 12));
			}
			agenda.appointments().clear();
			List<Event> insertedEv = new ArrayList<Event>();

			agenda.setDisplayedLocalDateTime(time);
			for (Event event1 : allEvents) {
				if (equals(selected.toString(), event1.getDateCalendar()) == true) {
					for (Event event2 : allEvents) {
						if (insertedEv.contains(event2) == false && event2.getDateCalendar() != null
								&& event1.getDateCalendar().substring(0, 3).equals(date.getMonths()[viewCalendar
										.calendarProperty().getValue().getTime().getMonth()].substring(0, 3))) {
							StringBuilder eventDate = new StringBuilder();

							int hour;
							int minute;

							if (event2.getTime() != null) {
								eventDate.append(event2.getDateCalendar());
								eventDate.append(" ");
								eventDate.append(event2.getTime());

								hour = Integer.parseInt(event2.getTime().substring(0, 2));
								minute = Integer.parseInt(event2.getTime().substring(3));
							} else {
								eventDate.append(event2.getDateCalendar());
								eventDate.append(" ");
								eventDate.append("08:00");
								hour = 8;
								minute = 0;
							}

							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm",
									java.util.Locale.ENGLISH);
							LocalDate eventTime = LocalDate.parse(eventDate.toString(), formatter);

							Appointment lTestAppointments = new Agenda.AppointmentImplLocal()

									.withStartLocalDateTime(LocalDateTime.of(eventTime, LocalTime.of(hour, minute)))

									.withEndLocalDateTime(LocalDateTime.of(eventTime, LocalTime.of(hour + 2, minute)))

									.withSummary(event2.getSubject()).withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group5"))

									.withDescription(event2.getSubject() + " " + event2.getPersons()
									);
							agenda.appointments().add(lTestAppointments);
							agenda.setAllowDragging(false);
							insertedEv.add(event2);
						}
					}
				}
			}
			insertedEv.clear();
		});

		exit.setOnAction((ActionEvent evt) -> {
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

		List<Date> allDates = new ArrayList<Date>();
		List<Time> allTimes = new ArrayList<Time>();
		List<Name> allNames = new ArrayList<Name>();
		List<Org> allOrgs = new ArrayList<Org>();
		List<Location> allLocations = new ArrayList<Location>();
		List<MandatoryWords> allMandatoryWords = new ArrayList<MandatoryWords>();

		settings_save_button.setOnAction((ActionEvent evt) -> {
			// serializare XML pentru mandatory si optional
			if (newEvent.getName().equals("") == true) {// nu a setat numele si
														// ii dam unul default
				newEvent.setName("Event" + allEventTypes.size());
			}

			if (allDates.size() != 0 && allTimes.size() != 0) {
				newEvent.setAllDates(allDates);
				newEvent.setAllTimes(allTimes);
				newEvent.setAllNames(allNames);
				newEvent.setAllOrgs(allOrgs);
				newEvent.setAllLocations(allLocations);
				newEvent.setAllMandatoryWords(allMandatoryWords);
				allEventTypes.add(newEvent);
				allDates.clear();
				allTimes.clear();
				allNames.clear();
				allOrgs.clear();
				allLocations.clear();
				allMandatoryWords.clear();

				/*------------------------------------------------------------*/

				/*-----------------------Adaugare lista totala de evenimente*/

				allEventTypesVBox.getChildren().add(new Text(newEvent.toString()));
				clear();

				/*-------------------------------------------------------*/
				AllEventTypesXMLParser parser = new AllEventTypesXMLParser();
				parser.serialise(allEventTypes);
			} else {
				errorList.setText("Please set at least a date and time");
			}
		});

		clear_all.setOnAction((ActionEvent evt) -> {
			clear();
		});

		dateAddButton.setOnAction((ActionEvent evt) -> {
			if (newEvent.getName().equals("") == true && eventName.getText().equals("") == false) {
				newEvent.setName(eventName.getText());
				currentEvent.getChildren().add(new Text("Event title is " + newEvent.getName()));
			}
			boolean flag = true;
			Date newDate = new Date();

			if (dateMultiple.isSelected() == true) {// multiple e mai importand
													// dct mandatory
				newDate.setImportance("multiple");
				dateMultiple.setSelected(false);
			} else if (dateMandatory.isSelected() == true) {
				newDate.setImportance("mandatory");
				dateMandatory.setSelected(false);
			} else if (dateBetween.isSelected() == true) {
				if (dateBetweenStart.getValue() != null && dateBetweenFinish.getValue() != null) {

					String startDateString = dateBetweenStart.getValue().toString();
					String finishDateString = dateBetweenFinish.getValue().toString();

					if ((Integer.parseInt(startDateString.substring(8)) > Integer
							.parseInt(finishDateString.substring(8))
							&& Integer.parseInt(startDateString.substring(5, 7)) == Integer
									.parseInt(finishDateString.substring(5, 7))
							&& Integer.parseInt(startDateString.substring(0, 4)) == Integer
									.parseInt(finishDateString.substring(0, 4)))
							|| (Integer.parseInt(startDateString.substring(5, 7)) > Integer
									.parseInt(finishDateString.substring(5, 7))
									&& Integer.parseInt(startDateString.substring(0, 4)) == Integer
											.parseInt(finishDateString.substring(0, 4)))
							|| Integer.parseInt(startDateString.substring(0, 4)) > Integer
									.parseInt(finishDateString.substring(0, 4))) {// daca
																					// sunt
																					// inversate
						errorList.setText("The start date cannot be bigger than the finish one");
						flag = false;

					} else {
						newDate.setImportance("between");
						newDate.setFinishDate(finishDateString);
						newDate.setStartDate(startDateString);
						dateBetween.setSelected(false);
					}
				} else {
					errorList.setText("The start and finish date must have a value if between is checked");
					flag = false;
				}
			} else { // daca nu se face check se considera optional
				newDate.setImportance("optional");
				dateOptional.setSelected(false);
			}

			if (flag == true) {
				allDates.add(newDate);
				currentEvent.getChildren().add(new Text(newDate.toString()));
			}
		});

		timeAddButton.setOnAction((ActionEvent evt) -> {
			if (newEvent.getName().equals("") == true && eventName.getText().equals("") == false) {
				newEvent.setName(eventName.getText());
				currentEvent.getChildren().add(new Text("Event title is " + newEvent.getName()));
			}
			boolean flag = true;
			Time newTime = new Time();

			if (timeMultiple.isSelected() == true) {// multiple e mai importand
													// dct mandatory
				newTime.setImportance("multiple");
				timeMultiple.setSelected(false);
			} else if (timeMandatory.isSelected() == true) {
				newTime.setImportance("mandatory");
				timeMandatory.setSelected(false);
			} else if (timeBetween.isSelected() == true) {
				if (timeBetweenStart.getText().equals("") != true && timeBetweenFinish.getText().equals("") != true) {

					String startTime = timeBetweenStart.getText();
					String finishTime = timeBetweenFinish.getText();
					newTime.setImportance("between");

					if (Integer.parseInt(startTime.substring(0, 2)) < 24
							&& Integer.parseInt(finishTime.substring(0, 2)) < 24
							&& Integer.parseInt(startTime.substring(3)) < 60
							&& Integer.parseInt(startTime.substring(3)) < 60 && startTime.length() == 5
							&& finishTime.length() == 5) {
						if (newTime.setStartHour(startTime) == true && newTime.setFinishHour(finishTime) == true) {
							timeBetween.setSelected(false);
							timeBetweenStart.clear();
							timeBetweenFinish.clear();
						} else {
							errorList.setText("The start and finish hour must have the format HH:MM");
							flag = false;
						}
					} else {
						errorList.setText("Please limit the hour to 23:59");
						flag = false;
					}
				} else {
					errorList.setText("The start and finish hour must have a value if between is checked");
					flag = false;
				}
			} else { // daca nu se face check se considera optional
				newTime.setImportance("optional");
				timeOptional.setSelected(false);
			}

			if (flag == true) {
				allTimes.add(newTime);
				currentEvent.getChildren().add(new Text(newTime.toString()));
			}
		});

		nameAddButton.setOnAction((ActionEvent evt) -> {
			if (newEvent.getName().equals("") == true && eventName.getText().equals("") == false) {
				newEvent.setName(eventName.getText());
				currentEvent.getChildren().add(new Text("Event title is " + newEvent.getName()));
			}
			boolean flag = true;
			Name newName = new Name();

			if (nameMultiple.isSelected() == true) {// multiple e mai importand
													// dct mandatory
				newName.setImportance("multiple");
				nameMultiple.setSelected(false);
			} else if (nameMandatory.isSelected() == true) {
				newName.setImportance("mandatory");
				nameMandatory.setSelected(false);
			} else { // daca nu se face check se considera optional
				if (nameList.getText().equals("") != true) {

					String names = nameList.getText();

					newName.setImportance("optional");

					if (newName.setListOfNames(names) == true) {
						nameOptional.setSelected(false);
						nameList.clear();
					} else {
						errorList.setText("Please write the names in the format indicated");
						flag = false;
					}
				} else {
					errorList.setText("Please write some names for optional value");
					flag = false;
				}
			}

			if (flag == true) {
				allNames.add(newName);
				currentEvent.getChildren().add(new Text(newName.toString()));
			}
		});

		orgAddButton.setOnAction((ActionEvent evt) -> {
			if (newEvent.getName().equals("") == true && eventName.getText().equals("") == false) {
				newEvent.setName(eventName.getText());
				currentEvent.getChildren().add(new Text("Event title is " + newEvent.getName()));
			}
			boolean flag = true;
			Org newOrg = new Org();

			if (orgMultiple.isSelected() == true) {// multiple e mai importand
													// dct mandatory
				newOrg.setImportance("multiple");
				orgMultiple.setSelected(false);
			} else if (orgMandatory.isSelected() == true) {
				newOrg.setImportance("mandatory");
				orgMandatory.setSelected(false);
			} else { // daca nu se face check se considera optional
				if (orgList.getText().equals("") != true) {

					String orgs = orgList.getText();

					newOrg.setImportance("optional");

					if (newOrg.setListOfOrg(orgs) == true) {
						orgOptional.setSelected(false);
						orgList.clear();
					} else {
						errorList.setText("Please write the names of the organisations in the indicated format");
						flag = false;
					}
				} else {
					errorList.setText("Please write some organisations for optional value");
					flag = false;
				}
			}

			if (flag == true) {
				allOrgs.add(newOrg);
				currentEvent.getChildren().add(new Text(newOrg.toString()));
			}
		});

		locationAddButton.setOnAction((ActionEvent evt) -> {
			if (newEvent.getName().equals("") == true && eventName.getText().equals("") == false) {
				newEvent.setName(eventName.getText());
				currentEvent.getChildren().add(new Text("Event title is " + newEvent.getName()));
			}
			boolean flag = true;
			Location newLoc = new Location();

			if (locationMultiple.isSelected() == true) {// multiple e mai
														// importand
				// dct mandatory
				newLoc.setImportance("multiple");
				locationMultiple.setSelected(false);
			} else if (locationMandatory.isSelected() == true) {
				newLoc.setImportance("mandatory");
				locationMandatory.setSelected(false);
			} else { // daca nu se face check se considera optional
				if (locationList.getText().equals("") != true) {

					String locations = locationList.getText();

					newLoc.setImportance("optional");

					if (newLoc.setListOfLocation(locations) == true) {
						locationOptional.setSelected(false);
						locationList.clear();
					} else {
						errorList.setText("Please write the names of the locations in the indicated format");
						flag = false;
					}
				} else {
					errorList.setText("Please write some locations for optional value");
					flag = false;
				}
			}

			if (flag == true) {
				allLocations.add(newLoc);
				currentEvent.getChildren().add(new Text(newLoc.toString()));
			}
		});

		mandatoryWordsAddButton.setOnAction((ActionEvent evt) -> {
			if (newEvent.getName().equals("") == true && eventName.getText().equals("") == false) {
				newEvent.setName(eventName.getText());
				currentEvent.getChildren().add(new Text("Event title is " + newEvent.getName()));
			}
			boolean flag = true;
			MandatoryWords newWords = new MandatoryWords();

			if (mandatoryWordsList.getText().equals("") != true && percentageWords.getText().equals("") != true) {

				String words = mandatoryWordsList.getText();
				String percentage = percentageWords.getText();
				if (newWords.setListOfWords(words) == true) {
					for (int iterator = 0; iterator < percentage.length(); iterator++) {
						if (Character.isDigit(percentage.charAt(iterator)) == false) {
							if (percentage.charAt(iterator) != '%') {
								errorList.setText("Please write only numbers in the percentage field");
								flag = false;
							}
						}
					}
					if (flag != false) {
						mandatoryWordsList.clear();
						percentageWords.clear();
						newWords.setPercentage(Integer.parseInt(percentage));
					}
				} else {
					errorList.setText("Please write the words in the indicated format");
					flag = false;
				}
			} else {
				errorList.setText("Please write some words and a percentage");
				flag = false;
			}

			if (flag == true) {
				allMandatoryWords.add(newWords);
				currentEvent.getChildren().add(new Text(newWords.toString()));
			}
		});

		reparseButton.setOnAction((ActionEvent evt) -> {
			if (threads > 0) {
				Task<List<Event>> task = new Task<List<Event>>() {

					@Override
					protected List<Event> call() throws Exception {
						threads = 0;
						parseMessage.setText("Parsing, please wait...");
						GetEventsThread getEvents = new GetEventsThread(allEvents, allEmails, allEventTypes);
						getEvents.run();
						return getEvents.getEvents();
					}
				};

				task.setOnSucceeded(e -> {
					allEvents = task.getValue();
					parseMessage.setText("DONE !");
					refresh();// se face refresh cu noile evenimente
					allEventsFoundText.setText("Events Found: "+allEvents.size());
					threads = 1;
				});
				new Thread(task).start();
			} else {
				parseMessage.setText("Please wait while other parsing is done");
			}
		});

		contactUs.setOnAction((ActionEvent evt) -> {
			try {
				Desktop.getDesktop().browse(new URI("https://github.com/cipriancus/eventCalendar"));
			} catch (Exception e) {
				e.printStackTrace();
			}
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

	private void clear() {
		newEvent = new EventType();
		currentEvent.getChildren().clear();

		eventName.clear();

		dateMandatory.setSelected(false);
		dateOptional.setSelected(false);
		dateMultiple.setSelected(false);
		dateBetween.setSelected(false);

		// dateBetweenStart;
		// dateBetweenFinish;

		timeMandatory.setSelected(false);
		timeOptional.setSelected(false);
		timeMultiple.setSelected(false);
		timeBetween.setSelected(false);
		timeBetweenStart.clear();
		timeBetweenFinish.clear();

		nameMandatory.setSelected(false);
		nameOptional.setSelected(false);
		nameMultiple.setSelected(false);
		nameList.clear();

		orgMandatory.setSelected(false);
		orgOptional.setSelected(false);
		orgMultiple.setSelected(false);
		orgList.clear();

		locationMandatory.setSelected(false);
		locationOptional.setSelected(false);
		locationMultiple.setSelected(false);
		locationList.clear();

		mandatoryWordsList.clear();
		percentageWords.clear();
	}

	private void refresh() {
		allEventsFound.getChildren().clear();
		leftAllEvents.getChildren().clear();
		for (Event iterator : allEvents) {
			leftAllEvents.getChildren().add(new Text(iterator.toString()));
			allEventsFound.getChildren().add(new Text(iterator.toStringFull()));
		}
	}
}