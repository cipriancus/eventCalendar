package parseXML;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import model.EmailAddress;
/**
 * Clasa pentru parsarea XML de email-uri
 * @author Ilie Grosu
 *
 * @param <T>
 */
@XmlSeeAlso(EmailAddress.class)
@XmlRootElement(name="emails")
public class Emails<T> {
	
	protected List<EmailAddress> list=new ArrayList<EmailAddress>();

    public Emails(){
    	
    }

    public Emails(List<T> list){
    	this.list=(List<EmailAddress>) list;
    }

    @XmlElement(name="email")
    public List getList(){
    	return list;
    }
}