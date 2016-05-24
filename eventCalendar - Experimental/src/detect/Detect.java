package detect;

import java.util.*;

public class Detect {
	private static List<Event> events = new ArrayList<>();

	public Detect(String email, String password, int option, String necesaryKeys, String optionalsKeys, int proc) {
		int numberOfEmails;
		String emailText;
		String emailSubject;
		String dateAndTime = "";
		String dateCalendar = null;
		String location = null;
		String persons = null;
		String subject;
		String org = null;
		String sender;
		String timeCalendar;
		String typesXample;// exemple
		int[] isDetected = { 0, 0, 0, 0, 0 };
		boolean toBeorNotToBe;

		int nrEvents = 0;

		typesXample = " theatre mouvie opera football dinner concert";
		FetchEmail e = new FetchEmail(email, password, option);// here we take//
																// all the mails
																// and make one
																// XML ///need
																// improve
																// protocol
		GetContent content = new GetContent("emails.xml");// we instantializate
															// the content with
															// strings from XML
		numberOfEmails = content.getNumber();
		int j = -1;
		for (int i = 0; i <= numberOfEmails - 1; i++) {// we put in list of
														// events new events
														// found
			emailText = content.getEmailText(i);
			emailSubject = content.getSubject(i);
			// download Atachaments

			// Date&Time
			GiveMeDateAndTime giveMeDAT = new GiveMeDateAndTime();
			dateAndTime = giveMeDAT.giveMeDate(emailText, "2016-05-11"); // daca
																			// nu
																			// va
																			// fi
																			// gasita
																			// nici
																			// o
																			// data
																			// va
																			// fi
																			// returnat
																			// ""
			dateCalendar = giveMeDAT.giveMeDateForCalendar(dateAndTime);
			timeCalendar = giveMeDAT.giveMeTimeForCalendar(dateAndTime);

			// put in Strings Location,Persons,Organization
			GiveMeLocationOP giveMeLOP = new GiveMeLocationOP();
			giveMeLOP.getNER(emailText, "english.all.3class.distsim.crf.ser");
			location = giveMeLOP.giveMeLocation();
			persons = giveMeLOP.giveMePersons();
			org = giveMeLOP.giveMeOrg();

			// Create array isDetected
			if (dateCalendar != null) {
				isDetected[0] = 1;
				isDetected[1] = 1;
			} else {
				isDetected[0] = 0;
				isDetected[1] = 0;

			}

			if (org != null)
				isDetected[2] = 1;
			else
				isDetected[2] = 0;

			if (location != null)
				isDetected[3] = 1;
			else
				isDetected[3] = 0;

			if (persons != null)
				isDetected[4] = 1;
			else
				isDetected[4] = 0;

			EventsType type = new EventsType();

			toBeorNotToBe = type.isEvent(emailText, emailSubject, necesaryKeys, proc, isDetected);
			if (toBeorNotToBe) { // if the mail corespond to main keys create
									// event object
				j++;
				Event ev = new Event();
				subject = type.giveMeTypeOfEvent(emailText, emailSubject, typesXample);
				sender = content.getEmailSender(i);
				ev.setSender(sender);
				// Put Date&Time to Event object
				ev.setDates(dateAndTime);
				ev.setDateCalendar(dateCalendar);
				ev.setTime(timeCalendar);
				// Put Location persons &Organization in event object
				ev.setLocation(location);
				ev.setPersons(persons);
				ev.setOrg(org);

				// put Subject in Event object
				subject = subject + "::" + emailSubject;
				ev.setSubject(subject);
				events.add(ev);

				nrEvents++;
			}

		}

		for (int i = 0; i < nrEvents; i++)
			printEvent(events.get(i));
	}

	private void printEvent(Event event) {

		System.out.println(event.getSubject());
		System.out.println(event.getDateCalendar());
		System.out.println(event.getDates());
		System.out.println(event.getTime());
		System.out.println(event.getLocation());
		System.out.println(event.getOrg());
		System.out.println(event.getPersons());
		// System.out.println(event.getSender());
		System.out.println();
		System.out.println();
		System.out.println();

	}

	public String getProtocol() {
		return "gmail";

	}

	public List<Event> getEvents() {
		return events;
	}
}
