package detect;
//Autor:Cadaru

public class EventsType {

	public boolean isEvent(String emailText, String emailSubject, String necesaryKeys, int proc, int[] isDetected) {
		String[] keywords = necesaryKeys.split(" ");
		String text =" "+ emailText + " " + emailSubject;
		int len = text.split(" ").length;
		int[] freq = new int[len];
		int count = 0;
		float myProc = 0.0f;
		int keywLength = keywords.length;
		int[] myIsDetected = new int[5];

		for (int i = 0; i < keywords.length; i++) {
			keywords[i].toLowerCase();
			
			if (text.toLowerCase().contains(keywords[i])) {
				freq[i] = 1;
			}
			if (keywords[i].equals("date")) {
				keywLength--;
				myIsDetected[0] = 1;
				freq[i]=-1;
			}
			if (keywords[i].equals("time")) {
				keywLength--;
				myIsDetected[1] = 1;
				freq[i]=-1;
			}
			if (keywords[i].equals("organization")) {
				keywLength--;
				myIsDetected[2] = 1;
				freq[i]=-1;
			}
			if (keywords[i].equals("location")) {
				keywLength--;
				myIsDetected[3] = 1;
				freq[i]=-1;
			}
			if (keywords[i].equals("person")) {
				keywLength--;
				myIsDetected[4] = 1;
				freq[i]=-1;
			}
			
		}

		for (int i = 0; i < freq.length; i++) {
			if (freq[i] == 1) {
				count++;
			}
		}
		myProc = (float) count / keywLength * 100;
		if (myProc >= proc)
			return true;
		else
			return false;

	}

	public String giveMeTypeOfEvent(String emailText, String emailSubject, String events) {
		String text = emailText + " " + emailSubject;
		String[] keywords = events.split(" ");

		for (int i = 0; i < keywords.length; i++) {
			keywords[i].toLowerCase();
			if (keywords[i].equals(text.toLowerCase())) {
				return keywords[i];
			}
		}
		return "Event";

	}
/*
	public static void main(String[] args){
		EventsType ev=new EventsType();
		String emailText="Maine ma intalnesc cu destinul.";
		String emailSubject="Intalnire cu destinul";
		String necesaryKeys="destinul maine Date Time";
		int[] isDetected=new int[5];
		isDetected[0]=1;
		isDetected[1]=0;
		isDetected[2]=0;
		isDetected[3]=0;
		isDetected[4]=0;
		System.out.println(ev.isEvent(emailText, emailSubject, necesaryKeys, 30, isDetected));
	}
*/
}
