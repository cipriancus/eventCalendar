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

public class AllMandatoryXMLParser {

	protected List<String> list = new ArrayList<String>();

	private File source = new File("allMandatory.xml");

	public void serialise(List<String> mandatory) {
		try {
			list.clear();
			list.addAll(mandatory);

			JAXBContext ctx = JAXBContext.newInstance(Mandatory.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter sw = new StringWriter();

			m.marshal(new Mandatory<String>(mandatory), sw);

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
			JAXBContext ctx = JAXBContext.newInstance(Mandatory.class);
			Unmarshaller u = ctx.createUnmarshaller();

			Mandatory<String> list = (Mandatory<String>) u.unmarshal(source);

			this.list = list.getList();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getMandatory() {
		return list;
	}
}
