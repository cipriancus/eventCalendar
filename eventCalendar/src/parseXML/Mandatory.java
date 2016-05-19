package parseXML;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="mandatory")
public class Mandatory<T> {
	protected List<T> list=new ArrayList<>();
	  public Mandatory(){
	    	
	    }

	    public Mandatory(List<T> list){
	    	this.list=list;
	    }

	    @XmlElement(name="mandatoryItem")
	    public List<T> getList(){
	    	return list;
	    }
}
