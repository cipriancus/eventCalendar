package parseXML;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="optional")
public class Optional<T> {
	protected List<T> list=new ArrayList<>();
	  public Optional(){
	    	
	    }

	    public Optional(List<T> list){
	    	this.list=list;
	    }

	    @XmlElement(name="optionalItem")
	    public List<T> getList(){
	    	return list;
	    }
}
