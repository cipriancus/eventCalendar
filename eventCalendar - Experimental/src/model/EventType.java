package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Defineste un eveniment creat de utilizator
 * 
 * @author Ionut Bejan
 *
 */
public class EventType {
	/**
	 * Un EventType are un nume dat de user altfel se va da automat
	 * Event1,Event2 etc
	 */
	private String name = "";
	/**
	 * Retine toate datele asociate cu acesta,vezi clasa Date
	 */
	private List<Date> allDates = new ArrayList<Date>();

	/**
	 * Retine toate timpurile asociate cu acesta,vezi clasa Time
	 */
	private List<Time> allTimes = new ArrayList<Time>();

	/**
	 * Retine toate numele asociate cu acesta,vezi clasa Name
	 */
	private List<Name> allNames = new ArrayList<Name>();
	/**
	 * Retine toate organizatiile asociate cu acesta,vezi clasa Org
	 */
	private List<Org> allOrgs = new ArrayList<Org>();

	/**
	 * Retine toate location asociate cu acesta,vezi clasa Location
	 */
	private List<Location> allLocations = new ArrayList<Location>();

	/**
	 * Retine toate cuvintele mandatory asociate cu acesta,vezi clasa
	 * MandatoryWords
	 */
	private List<MandatoryWords> allMandatoryWords = new ArrayList<MandatoryWords>();

	public List<Date> getAllDates() {
		return allDates;
	}

	public void setAllDates(List<Date> allDates) {
		this.allDates.addAll(allDates);
	}

	public List<Time> getAllTimes() {
		return allTimes;
	}

	public void setAllTimes(List<Time> allTimes) {
		this.allTimes.addAll(allTimes);
	}

	public List<Name> getAllNames() {
		return allNames;
	}

	public void setAllNames(List<Name> allNames) {
		this.allNames.addAll(allNames);
	}

	public List<Org> getAllOrgs() {
		return allOrgs;
	}

	public void setAllOrgs(List<Org> allOrgs) {
		this.allOrgs.addAll(allOrgs);
	}

	public List<Location> getAllLocations() {
		return allLocations;
	}

	public void setAllLocations(List<Location> allLocations) {
		this.allLocations.addAll(allLocations);
	}

	public List<MandatoryWords> getAllMandatoryWords() {
		return allMandatoryWords;
	}

	public void setAllMandatoryWords(List<MandatoryWords> allMandatoryWords) {
		this.allMandatoryWords.addAll(allMandatoryWords);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder returnString = new StringBuilder();
		returnString.append("Event name= " + name);

		return returnString.toString()+"\n"+allDates.toString()+"\n"+allNames.toString()+"\n"+allTimes.toString()+"\n"+allLocations.toString()+"\n"+allMandatoryWords+"\n";
	}
}
