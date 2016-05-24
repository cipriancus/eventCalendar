package model;

public class Time {
	/**
	 * Valori: list, mandatory, optional,between
	 */
	private String importance;
	/**
	 * Valoare de inceput a orei daca se bifeaza importance ca between
	 */
	private String startHour;
	/**
	 * Valoare de sfarsit a orei daca se bifeaza importance ca between
	 */
	private String finishHour;

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getStartHour() {
		return startHour;
	}

	public boolean setStartHour(String startHour) {
		if (importance.equals("between") && startHour.length() == 5) {
			if (Character.isDigit(startHour.charAt(0)) && Character.isDigit(startHour.charAt(1))
					&& Character.isDigit(startHour.charAt(3)) && Character.isDigit(startHour.charAt(4))
					&& startHour.contains(":")) {
				this.startHour = startHour;
				return true;
			}
		}
		return true;
	}

	public String getFinishHour() {
		return finishHour;
	}

	public boolean setFinishHour(String finishHour) {
		if (importance.equals("between") && finishHour.length() == 5) {
			if (Character.isDigit(finishHour.charAt(0)) && Character.isDigit(finishHour.charAt(1))
					&& Character.isDigit(finishHour.charAt(3)) && Character.isDigit(finishHour.charAt(4))
					&& finishHour.contains(":")) {
				this.finishHour = finishHour;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		String string="Time with importance="+importance;
		if(importance.equals("between")){
			return string+" and start time="+startHour+" and finish hour="+finishHour;
		}
		return string;
	}
}
