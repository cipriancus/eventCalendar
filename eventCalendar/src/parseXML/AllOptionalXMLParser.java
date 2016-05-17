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

public class AllOptionalXMLParser {

	protected List<String> list = new ArrayList<String>();

	private File source = new File("allOptional.xml");

	public void serialise(List<String> optional) {
		try {
			list.clear();
			list.addAll(optional);

			JAXBContext ctx = JAXBContext.newInstance(Optional.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter sw = new StringWriter();

			m.marshal(new Optional<String>(optional), sw);

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
			JAXBContext ctx = JAXBContext.newInstance(Optional.class);
			Unmarshaller u = ctx.createUnmarshaller();

			Optional<String> list = (Optional<String>) u.unmarshal(source);

			this.list = list.getList();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getOptional() {
		return list;
	}
}
