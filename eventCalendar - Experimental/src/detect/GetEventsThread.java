package detect;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.EmailAddress;
import parseXML.AllEventsXMLParser;

public class GetEventsThread {

	private List<Event> allEvents = new ArrayList<Event>();
	private List<EmailAddress> allEmail = new ArrayList<EmailAddress>();
	private String mandatory;
	private String optional;


	public GetEventsThread(List<Event> allEvents, List<EmailAddress> allEmail, String mandatory, String optional) {
		this.allEvents.addAll(allEvents);
		this.mandatory = mandatory;
		this.optional = optional;
		this.allEmail.addAll(allEmail);
	}

	public void run() {
		for (EmailAddress iterator : allEmail) {
			Detect detect = new Detect(iterator.getAddress(), iterator.getPassword(), iterator.getRead(), mandatory,
					optional, 1);
			allEvents.addAll(detect.getEvents());
		}

		AllEventsXMLParser eventParser = new AllEventsXMLParser();
		eventParser.serialise(allEvents);
	}
	
	public List<Event> getEvents(){
		return allEvents;
	}
}
