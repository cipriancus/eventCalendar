package detect;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//autor Cadaru +Popovici

public class EventsType {

	public boolean isEvent(String emailText, String emailSubject, String TypeOfEvents, int[] isDetected,String dateCalendar,String timeCalendar) {
		String[] keywords;
		int itIs = 1;
		int proc;
		DomXmlParser myXml = new DomXmlParser();
		if (itIs == 1)
			if (myXml.isSetDateMandatory(TypeOfEvents) && isDetected[0] == 0) {
				itIs = 0;System.out.println("DATE NU  ok");
			}

		if (itIs == 1)
			if (myXml.isSetTimeMandatory(TypeOfEvents) && isDetected[1] == 0) {
				itIs = 0;System.out.println("TIME NU  ok"+isDetected[1]+myXml.isSetTimeMandatory(TypeOfEvents));
			}

		if (itIs == 1)
			if (myXml.isSetOrganizationMandatory(TypeOfEvents) && isDetected[2] == 0) {
				itIs = 0;System.out.println("ORG NU  ok");
			}

		if (itIs == 1)
			if (myXml.isSetLocationMandatory(TypeOfEvents) && isDetected[3] == 0) {
				itIs = 0;System.out.println("LOC NU  ok");
			}

		if (itIs == 1)
			if (myXml.isSetNameMandatory(TypeOfEvents) && isDetected[4] == 0) {
				itIs = 0;System.out.println("NAME NU  ok");
			}
		//verify between dates
		if(itIs==1)
		if(myXml.isSetDateBetween(TypeOfEvents))
		{
			Date date1 = null,date2 = null,date3 = null;
			String[] dates;
			
			dates=myXml.getDate(TypeOfEvents);      
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date1 = format.parse(dates[0]);
				date2= format.parse(dates[1]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			format = new SimpleDateFormat("MMM dd, yyyy"); 
			try {
				System.out.println("_____________________________"+dateCalendar+"________________________________-");
				if(dateCalendar!=null)  
				date3= format.parse(dateCalendar);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(date3!=null) 
			{	
				if(date1.compareTo(date3)==1 || date2.compareTo(date3) ==-1)
					itIs=0;
			}
			else itIs=0;
		}
		
		//verify between time
		if(itIs==1)
		if(myXml.isSetTimeBetween(TypeOfEvents))
		{
			Date date1 = null,date2 = null,date3 = null;
			String[] dates;
			
			dates=myXml.getTime(TypeOfEvents);      
			
			DateFormat format = new SimpleDateFormat("HH:mm");
			try {
				System.out.println("_____________________________"+timeCalendar+"________________________________-");
				date1 = format.parse(dates[0]);
				date2= format.parse(dates[1]);
				if(timeCalendar!=null)  
					date3= format.parse(timeCalendar);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println("time1"+date1);
			System.out.println("time2"+date2);
			System.out.println("time3"+date3);

			if(date3!=null) 
			{	
				if(date1.compareTo(date3)==1 || date2.compareTo(date3) ==-1)
					{itIs=0;System.out.println("l-am futut1");}
					System.out.println("1?3"+date1.compareTo(date3));
					System.out.println("2?3"+date2.compareTo(date3));
			}
			else {itIs=0;System.out.println("l-am futut2");}
		}
		
		
		
		
		//veryfy mandatory word in text
		
		if (itIs == 1)
		if(myXml.getMandatoryWords(TypeOfEvents)!=null)
		{
			keywords = myXml.getMandatoryWords(TypeOfEvents);
			System.out.println(keywords);
			proc =20;// trebuie preluat procentul din xml
			String text = " " + emailText + " " + emailSubject;
			int len = text.split(" ").length;
			int[] freq = new int[len];
			int count = 0;
			float myProc = 0.0f;
			int keywLength=0;
			if(keywords!=null)
				keywLength = keywords.length;

			for (int i = 0; i < keywLength; i++) {
				keywords[i].toLowerCase();

				if (text.toLowerCase().contains(keywords[i])) {
					freq[i] = 1;
				}
			}

			for (int i = 0; i < freq.length; i++) {
				if (freq[i] == 1) {
					count++;
				}
			}
			myProc = (float) count / keywLength * 100;
			if (myProc < proc)
				{itIs=0;System.out.println("Nu mere");}
		}
		
		if(itIs==1)
			return true;
		else 
			return false;
	}

	public String giveMeTypeOfEvent(String emailText, String emailSubject, String events) {
		String text = emailText + " " + emailSubject;
		String[] keywords = events.split(" ");

		for (int i = 0; i < keywords.length; i++) {
			keywords[i].toLowerCase();
			if (keywords[i].equals(text.toLowerCase())) {
				return keywords[i];
			}
		}
		return "Event";

	}
	
}
