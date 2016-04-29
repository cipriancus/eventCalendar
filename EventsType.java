package detect;

public class EventsType {

	public boolean isEvent(String emailText, String emailSubject, String necesaryKeys, int proc) {
		String[] keywords = necesaryKeys.split(" ");
		String text=emailText+" "+emailSubject;
		int len=text.split(" ").length;
		int[] freq = new int[len];
		int count = 0;
		float myProc = 0.0f;

		for (int i = 0; i < keywords.length; i++) {
			keywords[i].toLowerCase();
			if (text.toLowerCase().contains(keywords[i])) {
				freq[i] = 1;
			}
		}

		for (int i = 0; i < freq.length; i++) {
			if (freq[i] == 1) {
				count++;
			}
		}
		myProc = (float) count / keywords.length * 100;
		if (myProc > proc)
			return true;
		else
			return false;
		
	}

	public String giveMeTypeOfEvent(String emailText, String emailSubject,String events) {
		String text=emailText+" "+emailSubject;
		String[] keywords = events.split(" ");
		
		for(int i=0;i<keywords.length;i++){
			keywords[i].toLowerCase();
			if(keywords[i].equals(text.toLowerCase())){
				return keywords[i];
			}
		}
		return "Event";
		
	}

}
