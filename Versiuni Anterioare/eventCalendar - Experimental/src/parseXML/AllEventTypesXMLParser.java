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

import model.EventType;

/**
 * Clasa care parseaza si face
 * marshall si unmarshall a 
 * obiectelor de tip EventType 
 * luate din allEventsType.xml
 * @author Ilie Grosu
 *
 * @param <T>
 */
public final class AllEventTypesXMLParser {
	protected List<EventType> list = new ArrayList<EventType>();

	private File source = new File("allEventTypes.xml");

	public void serialise(List<EventType> allEventsTypes) {
		try {
			synchronized (this) {
			list.clear();
			list.addAll(allEventsTypes);

			JAXBContext ctx = JAXBContext.newInstance(AllEventTypeList.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter sw = new StringWriter();

			m.marshal(new AllEventTypeList<EventType>(allEventsTypes), sw);

			sw.close();

			BufferedWriter write = new BufferedWriter(new FileWriter(source));
			write.write(sw.toString());
			write.flush();
			write.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deserialise() {
		try {
			synchronized (this) {
				
			JAXBContext ctx = JAXBContext.newInstance(AllEventTypeList.class);
			Unmarshaller u = ctx.createUnmarshaller();

			AllEventTypeList<EventType> list = (AllEventTypeList<EventType>) u.unmarshal(source);

			this.list = list.getList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<EventType> getEvents() {
		return list;
	}
}
