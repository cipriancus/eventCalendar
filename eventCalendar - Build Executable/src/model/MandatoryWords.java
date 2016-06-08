package model;

import java.util.StringTokenizer;

/**
 * 
 * @author Ciprian
 *
 */
public class MandatoryWords {
	/**
	 * Valoare numelor date 
	 * din interfata ca exemplu
	 */
	private String listOfWords;
	/**
	 * Procentul
	 */
	private int percentage=0;
	
	public String getListOfWords() {
		return listOfWords;
	}
	public boolean setListOfWords(String listOfWords) {
		for(int iterator=0;iterator<listOfWords.length();iterator++){
			if(Character.isAlphabetic(listOfWords.charAt(iterator))==false){
				if(listOfWords.charAt(iterator)!=',')
					if(listOfWords.charAt(iterator)!=' ')
				return false;
			}
		}
		if(validate(listOfWords)==true){
		this.listOfWords = listOfWords;
		return true;
		}
		return false;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	@Override
	public String toString(){
		String string="Mandatory words"+listOfWords;
		if(percentage!=0)
			return string+" percentage="+percentage;
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
