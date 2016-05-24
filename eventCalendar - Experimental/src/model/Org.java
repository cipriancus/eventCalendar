package model;

import java.util.StringTokenizer;

public class Org {
	/**
	 * Valori: list, mandatory, optional
	 */
	private String importance;
	/**
	 * Valoare numelor date 
	 * din interfata ca exemplu
	 */
	private String listOfOrg;
	
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}
	public String getListOfOrg() {
		return listOfOrg;
	}
	public boolean setListOfOrg(String listOfOrg) {
		for(int iterator=0;iterator<listOfOrg.length();iterator++){
			if(Character.isAlphabetic(listOfOrg.charAt(iterator))==false ){
				if(listOfOrg.charAt(iterator)!=',')
					if(listOfOrg.charAt(iterator)!=' ')
				return false;
			}
		}
		if(validate(listOfOrg)){
		this.listOfOrg = listOfOrg;
		return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		String string="Organisation with importance="+importance;
		if(importance.equals("optional")){
			return string+" and list of organisations "+ listOfOrg;
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
