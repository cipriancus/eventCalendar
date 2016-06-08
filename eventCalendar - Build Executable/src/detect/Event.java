package detect;
//Autor:Popovici

public class Event {
	private String dateCalendar;
	private String dates;
	private String time;
	private String location;
	private String subject;
	private String persons;
	private String org;	
	private String sender;
	
	public String getDates()
	{
		return this.dates;	
	}public String getTime()
	{
		return this.time;	
	}
	public String getDateCalendar()
	{
		return this.dateCalendar;	
	}
	public String getLocation()
	{
		return this.location;	
	}
	public String getSubject()
	{
		return this.subject;	
	}
	
	public String getPersons() {
		return persons;
	}
	public String getOrg() {
		return org;
	}
	public String getSender()
	{
		return this.sender;	
	}
	
	public void setDates(String date)
	{
		this.dates=date;
	}public void setTime(String time)
	{
		this.time=time;
	}
	public void setLocation(String location)
	{
		this.location=location;
	}
	public void setSubject(String subject)
	{
		this.subject=subject;
	}
	
	public void setSender(String sender)
	{
		this.sender=sender;
	}
	public void setPersons(String persons) {
		this.persons = persons;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public void setDateCalendar(String dateCalend) {
		this.dateCalendar=dateCalend;
		
	}
	
	@Override
	public String toString() {
		return "Event data: " + dateCalendar + " time " + time;
	}
	
	public String toStringFull(){
		return "Event data: " + dateCalendar + " time " + time+ " location "+location+" org "+org+" persons "+persons;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCalendar == null) ? 0 : dateCalendar.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((org == null) ? 0 : org.hashCode());
		result = prime * result + ((persons == null) ? 0 : persons.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (dateCalendar == null) {
			if (other.dateCalendar != null)
				return false;
		} else if (!dateCalendar.equals(other.dateCalendar))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (org == null) {
			if (other.org != null)
				return false;
		} else if (!org.equals(other.org))
			return false;
		if (persons == null) {
			if (other.persons != null)
				return false;
		} else if (!persons.equals(other.persons))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	
	
}
