/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detectfile;


public class Event {
	private String date;
	private String location;
	private String time;
	private String persons;
	private String org;	
	
	
	public String getDate()
	{
		return this.date;	
	}
	public String getLocation()
	{
		return this.location;	
	}
	
	public String getTime()
	{
		return this.time;	
	}
	public String getPersons() {
		return persons;
	}
	public String getOrg() {
		return org;
	}
	
	
	
	
	public void setDate(String date)
	{
		this.date=date;
	}
	public void setLocation(String location)
	{
		this.location=location;
	}
	
	public void setTime(String time)
	{
		this.time=time;
	}
	
	public void setPersons(String persons) {
		this.persons = persons;
	}
	public void setOrg(String org) {
		this.org = org;
	}


	
	
}