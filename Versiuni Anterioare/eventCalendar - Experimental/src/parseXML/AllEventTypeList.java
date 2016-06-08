package parseXML;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import model.EventType;

/**
 * Clasa pentru parsarea XML de tipuri
 * de evenimente
 * @author Ilie Grosu
 *
 * @param <T>
 */
@XmlSeeAlso(EventType.class)
@XmlRootElement(name="events")
public class AllEventTypeList<T>{
	protected List<T> list=new ArrayList<>();

    public AllEventTypeList(){
    	
    }

    public AllEventTypeList(List<T> list){
    	this.list=list;
    }

    @XmlElement(name="eventType")
    public List<T> getList(){
    	return list;
    }
}
