package detect;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import model.EmailAddress;
import model.EventType;
import parseXML.AllEventsXMLParser;

public class GetEventsThread {

	private List<EventType> allEventTypes=new ArrayList<EventType>();
	private List<Event> allEvents = new ArrayList<Event>();
	private List<EmailAddress> allEmail = new ArrayList<EmailAddress>();


	public GetEventsThread(List<Event> allEvents, List<EmailAddress> allEmail,  List<EventType> allEventTypes) {
		this.allEvents.addAll(allEvents);
		this.allEmail.addAll(allEmail);
		this.allEventTypes.addAll(allEventTypes);
	}

	public void run() {
		for (EmailAddress iterator : allEmail) {
			for(EventType eventTypeIterator:allEventTypes){
			Detect detect;
			try {
				detect = new Detect(iterator.getAddress(),iterator.getPassword(),1,eventTypeIterator.getName());
				for(Event iteratorEV:detect.getEvents()){
					if(allEvents.contains(iteratorEV)==false){
						allEvents.add(iteratorEV);
					}
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}

		AllEventsXMLParser eventParser = new AllEventsXMLParser();
		eventParser.serialise(allEvents);
	}
	
	public List<Event> getEvents(){
		return allEvents;
	}
}
