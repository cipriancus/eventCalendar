package model;

import java.util.StringTokenizer;

/**
 * 
 * @author Ciprian
 *
 */
public class Name {
	/**
	 * Valori: list, mandatory, optional
	 */
	private String importance;
	/**
	 * Valoare numelor date din interfata ca exemplu
	 */
	private String listOfNames;

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getListOfNames() {
		return listOfNames;
	}

	public boolean setListOfNames(String listOfNames) {
		for (int iterator = 0; iterator < listOfNames.length(); iterator++) {
			if (Character.isAlphabetic(listOfNames.charAt(iterator)) == false) {
				if (listOfNames.charAt(iterator) != ',')
					if (listOfNames.charAt(iterator) != ' ')
						return false;
			}
		}
		if (validate(listOfNames) == true) {
			this.listOfNames = listOfNames;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String string = "Name with importance=" + importance;
		if (importance.equals("optional")) {
			return string + " and list of names " + listOfNames;
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
