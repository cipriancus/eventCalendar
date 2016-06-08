package parseXML;

import java.util.ArrayList;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import detect.Event;

/**
 * Clasa pentru parsarea XML de evenimente
 * @author Ionut Predoaia
 *
 * @param <T>
 */
@XmlSeeAlso(Event.class)
@XmlRootElement(name="events")
public class AllEventList<T> {
	
	protected List<T> list=new ArrayList<>();

    public AllEventList(){
    	
    }

    public AllEventList(List<T> list){
    	this.list=list;
    }

    @XmlElement(name="event")
    public List<T> getList(){
    	return list;
    }
}