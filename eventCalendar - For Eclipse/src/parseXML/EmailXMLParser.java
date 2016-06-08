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

import model.EmailAddress;

/**
 * Clasa care parseaza si face
 * marshall si unmarshall a 
 * obiectelor de tip email 
 * luate din allEmails.xml
 * @author Magureanu Andrei
 *
 * @param <T>
 */
public class EmailXMLParser {
	
	protected List<EmailAddress> list=new ArrayList<EmailAddress>();
	
	private File source=new File("allEmails.xml");
	
	public void serialise(List<EmailAddress> allEmails){
		try {
		list.clear();
		list.addAll(allEmails);
		
		JAXBContext ctx=JAXBContext.newInstance(Emails.class);
		Marshaller m=ctx.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
		
		StringWriter sw=new StringWriter();
		
		m.marshal(new Emails<EmailAddress>(allEmails), sw);
		
		sw.close();
		
		BufferedWriter write=new BufferedWriter(new FileWriter(source));
		write.write(sw.toString());
		write.flush();
		write.close();
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deserialise(){
		try {
			JAXBContext ctx=JAXBContext.newInstance(Emails.class);
			Unmarshaller u=ctx.createUnmarshaller();
			
			Emails<EmailAddress> list=(Emails<EmailAddress>)u.unmarshal(source);
			
			this.list=list.getList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List getEmails(){
		return list;
	}
}
