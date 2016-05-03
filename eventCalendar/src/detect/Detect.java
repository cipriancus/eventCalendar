package detect;


import java.util.*;

public class Detect {
	private  List<Event> events = new ArrayList<>();

	public  List<Event> getEvents() {
		return events;
	}
	
	public Detect(String email, String password, int option) {
		int numberOfEmails;
		String emailText;
		String emailSubject;
		String date;
		String location = null;
		String persons;
		String subject;
		String time;
		String org = null;
		String sender;
		LinkedHashSet<String> persons1;
		LinkedHashSet<String> org1;
		FetchEmail e = new FetchEmail(email, password, option);// here we take
																// all the mails
																// and make one
																// XML ///need
																// improve
																// protocol
		GetContent content = new GetContent("emails.xml");// we instantializate
															// the content with
															// strings from XML
		numberOfEmails = content.getNumber();
		for (int i = 0; i <= numberOfEmails -1; i++) {// we put in list of
														// events new events
														// found
			emailText = content.getEmailText(i);
			emailSubject = content.getSubject(i);
			Event ev = new Event();

			// Sender events.get(i).setSender(sender);

			sender = content.getEmailSender(i);
			ev.setSender(sender);
			// Date&Time momentan doar data explicita
			GiveMeDateAndTime giveMeDAT = new GiveMeDateAndTime();
			
			date = giveMeDAT.giveMeDate(emailText);
			System.out.println(date);

			ev.setDate(date);
			time = giveMeDAT.giveMeTime(emailText);
			ev.setTime(time);
			
			// Location
			GiveMeLocationOP giveMeLOP = new GiveMeLocationOP();
			giveMeLOP.getNER(emailText, "english.all.3class.distsim.crf.ser");
			
			ev.setLocation(location);
			persons1 = giveMeLOP.giveMePersons();
			//System.out.println("lalalalala " +persons1);
			ev.setPersons(persons1);
			org1 = giveMeLOP.giveMeOrg();
			ev.setOrg(org);
			
			////////////////////////////////// TEMPORAR/////////////////////////////////////
			subject = emailSubject;// pana facem o metoda de recunoastere a
									// subiectului in text folosind textul si
									// emailSubject
			ev.setSubject(subject);
			events.add(ev);
			printEvent(events.get(i));
		}
	}

	private void printEvent(Event event) {
		System.out.println("The subject is " + event.getSubject());
		System.out.println("The date is " + event.getDate());
		System.out.println("The time is " + event.getTime());
		System.out.println("The organization is" + event.getOrg());
		System.out.println("The persons are" + event.getPersons());
		
		// System.out.println(event.getSender());
		System.out.println();
		System.out.println();
		System.out.println();

	}

	public String getProtocol() {
		return "gmail";

	}
}// Popovici
