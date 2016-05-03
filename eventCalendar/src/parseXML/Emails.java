package parseXML;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="emails")
public class Emails<K,T> {
	
	protected Map<K,T> map=new HashMap();

    public Emails(){
    	
    }

    public Emails(Map<K,T> map){
    	this.map=map;
    }

    @XmlElement(name="Item")
    public Map<K,T> getList(){
    	return map;
    }
}