package detect;


import java.util.LinkedHashSet;

//nimic de testat
public class Event {
	private String date;
	private String location;
	private String subject;
	private String time;
	private LinkedHashSet<String> persons;
	private String org;	
	private String sender;
	
	public String getDate()
	{
		return this.date;	
	}
	public String getLocation()
	{
		return this.location;	
	}
	public String getSubject()
	{
		return this.subject;	
	}
	public String getTime()
	{
		return this.time;	
	}
	public LinkedHashSet<String> getPersons() {
		return persons;
	}
	public String getOrg() {
		return org;
	}
	public String getSender()
	{
		return this.sender;	
	}
		
	public void setDate(String date)
	{
		this.date=date;
	}
	public void setLocation(String location)
	{
		this.location=location;
	}
	public void setSubject(String subject)
	{
		this.subject=subject;
	}
	public void setTime(String time)
	{
		this.time=time;
	}
	public void setSender(String sender)
	{
		this.sender=sender;
	}
	public void setPersons(LinkedHashSet<String> persons1) {
		this.persons = persons1;
	}
	public void setOrg(String org) {
		this.org = org;
	}	
}
