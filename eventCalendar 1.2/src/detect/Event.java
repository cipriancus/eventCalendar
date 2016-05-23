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

	public void setDateCalendar(String dateCalendar) {
		this.dateCalendar = dateCalendar;
	}

	public void setPersons(String persons) {
		this.persons = persons;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getDates() {
		return this.dates;
	}

	public String getTime() {
		return this.time;
	}

	public String getDateCalendar() {
		return this.dateCalendar;
	}

	public String getLocation() {
		return this.location;
	}

	public String getSubject() {
		return this.subject;
	}

	public String getPersons() {
		return persons;
	}

	public String getOrg() {
		return org;
	}

	public String getSender() {
		return this.sender;
	}

	public void setDates(String date) {
		this.dates = date;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Override
	public String toString() {
		return "Event data: " + dateCalendar+ " time " + time + " locatie " + location + " cu " + persons + " organizatii "
				+ org;
	}
}
