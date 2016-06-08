package parseXML;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class EmailXMLParser {
	
	protected Map<String,String> map=new HashMap();
	
	private File source=new File("allEmails.xml");
	
	public void serialise(Map<String,String> allEmails){
		try {
		map.clear();
		map.putAll(allEmails);
		
		JAXBContext ctx=JAXBContext.newInstance(Emails.class);
		Marshaller m=ctx.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
		
		StringWriter sw=new StringWriter();
		
		m.marshal(new Emails<String,String>(allEmails), sw);
		
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
			
			Emails<String,String> list=(Emails<String,String>)u.unmarshal(source);
			
			this.map=list.getList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map getEmails(){
		return map;
	}
}
