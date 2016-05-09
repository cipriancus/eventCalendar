package detect;


import java.util.*;

public class Detect {
	private static List<Event> events = new ArrayList<>();

	public Detect(String email, String password, int option, String necesaryKeys, String optionalsKeys, int proc) {
		int numberOfEmails;
		String emailText;
		String emailSubject;
		String date;
		String location = null;
		String persons = null;
		String subject;
		String time;
		String org = null;
		String sender;
		String typesXample;//exemple
		boolean toBeorNotToBe;
		typesXample=" theatre mouvie opera football dinner";
		FetchEmail e = new FetchEmail(email, password, option);// here we take// all the mails
																// and make one
																// XML ///need
																// improve
																// protocol
		GetContent content = new GetContent("emails.xml");// we instantializate
															// the content with
															// strings from XML
		numberOfEmails = content.getNumber();
		int j=-1;
		for (int i = 0; i <= numberOfEmails - 1; i++) {// we put in list of events new events found
														
														
			emailText = content.getEmailText(i);
			emailSubject = content.getSubject(i);
			EventsType type = new EventsType();
			toBeorNotToBe=type.isEvent(emailText,emailSubject,necesaryKeys,proc);
			
			if (toBeorNotToBe) {		//if the mail corespond to  main keys create event object
				// verificadaca este un anumit tip de event
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				j++;
				Event ev = new Event();
				subject=type.giveMeTypeOfEvent(emailText,emailSubject,typesXample);
				sender = content.getEmailSender(i);
				ev.setSender(sender);
				// Date&Time momentan doar data explicita
				GiveMeDateAndTime giveMeDAT = new GiveMeDateAndTime();
				date = giveMeDAT.giveMeDate(emailText);

				ev.setDate(date);
				time = giveMeDAT.giveMeTime(emailText);
				ev.setTime(time);
				// Location
				
				GiveMeLocationOP giveMeLOP = new GiveMeLocationOP();
				giveMeLOP.getNER(emailText, "english.all.3class.distsim.crf.ser");
			//	location = giveMeLOP.giveMeLocation();
				ev.setLocation(location);
			//	persons = giveMeLOP.giveMePersons();
				ev.setPersons(persons);
			//	org = giveMeLOP.giveMeOrg();
				ev.setOrg(org);
				////////////////////////////////// TEMPORAR/////////////////////////////////////
				subject = emailSubject;// pana facem o metoda de recunoastere a******
										// subiectului in text folosind textul
										// si
										// emailSubject
				ev.setSubject(subject);
				events.add(ev);
				printEvent(events.get(j));

			}
			
		}
	}

	

	private void printEvent(Event event) {

		System.out.println(event.getSubject());
		System.out.println(event.getDate());
		System.out.println(event.getTime());
		// System.out.println(event.getSender());
		System.out.println();
		System.out.println();
		System.out.println();

	}

	public String getProtocol() {
		return "gmail";

	}
	
	public List<Event> getEvents(){
		return events;
	}
}// Popovici
