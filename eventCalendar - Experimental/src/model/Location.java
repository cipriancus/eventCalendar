package model;

import java.util.StringTokenizer;

public class Location {
	/**
	 * Valori: list, mandatory, optional
	 */
	private String importance;
	/**
	 * Valoare numelor date 
	 * din interfata ca exemplu
	 */
	private String listOfLocation;
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}
	public String getListOfLocation() {
		return listOfLocation;
	}
	public boolean setListOfLocation(String listOfLocation) {
		for(int iterator=0;iterator<listOfLocation.length();iterator++){
			if(Character.isAlphabetic(listOfLocation.charAt(iterator))==false){
				if(listOfLocation.charAt(iterator)!=',')
					if(listOfLocation.charAt(iterator)!=' ')
				return false;
			}
		}
		if(validate(listOfLocation)==true){
		this.listOfLocation = listOfLocation;
		return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		String string="Location with importance="+importance;
		if(importance.equals("optional")){
			return string+" and list of locations "+ listOfLocation;
		}
		return string;
	}
	

	private boolean validate(String word) {
		StringTokenizer st = new StringTokenizer(word, ",");
		while (st.hasMoreTokens()) {
			String cuvant=st.nextToken();
			int numar_spatiu = 0;
			for (int iterator = 0; iterator < cuvant.length(); iterator++) {
				if (cuvant.charAt(iterator) == ' ') {
					numar_spatiu++;
					if (numar_spatiu > 1) {
						return false;
					}
				} else if (Character.isAlphabetic(cuvant.charAt(iterator)) != true) {
					return false;
				}
			}
		}
		return true;
	}
}
