package model;
/**
 * 
 * @author Grosu
 *
 */
public class Date {
	/**
	 * Valori: list, mandatory, optional,between
	 */
	private String importance;
	/**
	 * Valoare de inceput a datelor daca se bifeaza importance ca between
	 */
	private String startDate;
	/**
	 * Valoare de sfarsit a datelor daca se bifeaza importance ca between
	 */
	private String finishDate;

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	@Override
	public String toString(){
		String string="Date with importance="+importance;
		if(importance.equals("between")){
			return string+" and start date="+startDate+" and finish date="+finishDate;
		}
		return string;
	}
}
