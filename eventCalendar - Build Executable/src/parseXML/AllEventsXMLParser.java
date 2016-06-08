package parseXML;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import detect.Event;

/**
 * Clasa care parseaza si face
 * marshall si unmarshall a 
 * obiectelor de tip Events 
 * luate din allEvents.xml
 * @author Ionut Predoaia
 *
 * @param <T>
 */
public class AllEventsXMLParser {

	protected List<Event> list = new ArrayList<Event>();

	private File source = new File("allEvents.xml");

	public void serialise(List<Event> allEvents) {
		try {
			list.clear();
			list.addAll(allEvents);

			JAXBContext ctx = JAXBContext.newInstance(AllEventList.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter sw = new StringWriter();

			m.marshal(new AllEventList<Event>(allEvents), sw);

			sw.close();

			BufferedWriter write = new BufferedWriter(new FileWriter(source));
			write.write(sw.toString());
			write.flush();
			write.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deserialise() {
		try {
			JAXBContext ctx = JAXBContext.newInstance(AllEventList.class);
			Unmarshaller u = ctx.createUnmarshaller();

			AllEventList<Event> list = (AllEventList<Event>) u.unmarshal(source);

			this.list = list.getList();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Event> getEvents() {
		return list;
	}
}
