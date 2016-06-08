package detect;
//Autor:Popovici

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Detect {
	private static List<Event> events = new ArrayList<>();

	public Detect(String email, String password, int option, String TypeOfEvents) throws FileNotFoundException {
		int numberOfEmails;
		String emailText;
		String emailSubject;
		String dateAndTime = "";
		String dateCalendar = null;
		String location = null;
		String persons = null;
		String subject = null;
		String org = null;
		String sender;
		String timeCalendar = null;
		String reciveDate;
		boolean toBeorNotToBe;
		int[] isDetected = { 0, 0, 0, 0, 0 };
		int k;
		int nrEvents = 0;

		FetchEmail e = new FetchEmail(email, password, option);// here we take//
																// all the mails
																// and make one
																// XML ///need
																// improve
																// protocol
		GetContent content = new GetContent("emails.xml");// we instantializate
															// the content with
															// strings from XML

	//*	AttachmentsData attach=new AttachmentsData(email,password,option);
    //    attach.getContent();
        
        int atachToProces=0;//*attach.getNrOfAtachements();
        int nrAtachProcesed=1;

		numberOfEmails = content.getNumber();
		System.out.println(numberOfEmails);
		// we put in list of events new events found
		for (int i = 1; i < numberOfEmails ; i++) {/////////////////////////////////////inlocuieste asta
			emailText = content.getEmailText(i);
			emailSubject = content.getSubject(i);
			reciveDate = content.getReciveDate(i); // get date of reciving
													
			subject = "";
			subject = emailSubject;
			
			
			//check for atachments first
			 if(nrAtachProcesed < atachToProces)//we still have attachments to process 
			 {
				i--;
				System.out.println("procesing Atachment");
			    nrAtachProcesed++;
			   //* emailText=attach.getTextOfAtachement(nrAtachProcesed);
			   //* emailSubject =attach.getSubjectAtachement(nrAtachProcesed);
			   //*reciveDate=attach.getDateReceivedAtachement(nrAtachProcesed);
			  
			 }
			 else
				 System.out.println("procesing Email");
			

			// prepare date of reciving
			SimpleDateFormat format1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
			Object date = null;
			try {
				date = format1.parse(reciveDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			reciveDate = format2.format(date);

			DomXmlParser myXml = new DomXmlParser();
			// Date&Time
			GiveMeDateAndTime giveMeDAT = new GiveMeDateAndTime();
			dateAndTime = giveMeDAT.giveMeDate(emailText, reciveDate);
			dateCalendar = giveMeDAT.giveMeDateForCalendar(dateAndTime, 0);
			timeCalendar = giveMeDAT.giveMeTimeForCalendar(dateAndTime);

			// put in Strings Location,Persons,Organization

			GiveMeLocationOP giveMeLOP = new GiveMeLocationOP();
			giveMeLOP.getNER(emailText, "english.all.3class.distsim.crf.ser");
			location = giveMeLOP.giveMeLocation();
			persons = giveMeLOP.giveMePersons();
			org = giveMeLOP.giveMeOrg();

			// Create array isDetected
			if (dateCalendar != null)
				isDetected[0] = 1;
			else
				isDetected[0] = 0;

			if (timeCalendar != null)
				isDetected[1] = 1;
			else
				isDetected[1] = 0;

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
			////////

			toBeorNotToBe = type.isEvent(emailText, emailSubject, TypeOfEvents, isDetected, dateCalendar, timeCalendar);
			if (toBeorNotToBe) { // if the mail corespond to main keys create
									// event object
				String s = giveMeDAT.giveMeDateForCalendar(dateAndTime, 0);
				k = 0;
				if (myXml.isSetDateMultiple(TypeOfEvents)) {
					while (s != null) {
						s = giveMeDAT.giveMeDateForCalendar(dateAndTime, k);
						k++;
						System.out.println(s + "data multipla");

						if (s != null) {
							dateCalendar = s;

							System.out.println("eveniment  ok");

							Event ev = new Event();
							sender = content.getEmailSender(i);
							ev.setSender(sender);
							// Put Date&Time to Event object
							ev.setDates(dateAndTime);
							ev.setDateCalendar(dateCalendar);
							ev.setTime(timeCalendar);
							// Put Location persons &Organization in event
							// object
							ev.setLocation(location);
							ev.setPersons(persons);
							ev.setOrg(org);

							// put Subject in Event object
							subject = subject + "::" + TypeOfEvents;
							ev.setSubject(subject);
							events.add(ev);

							nrEvents++;
						}
					}
				} else {
					System.out.println("eveniment  ok");

					Event ev = new Event();
					// subject=type.giveMeTypeOfEvent(emailText,emailSubject,typesXample);
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
					subject = subject + "::" + TypeOfEvents;
					ev.setSubject(subject);
					events.add(ev);

					nrEvents++;
				}

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

	public List<Event> getEvents(){
		return events;
	}
	
	public String getProtocol() {
		return "gmail";

	}
}
