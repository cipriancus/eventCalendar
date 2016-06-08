package detect;
//Cernescu

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class GetContent {
	private EmailList l;
	private List<Email>s;
	private XMLDecoder x;
	public GetContent(String filename)
	{
		try {
			x = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
		l=(EmailList) x.readObject();
		s=l.getEmails();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String getEmailText(int i){
		return s.get(i).getContent();
	}
	
	public String getSubject(int i){
		return s.get(i).getSubject();
	}
	
	public String getEmailSender(int i){
		return s.get(i).getSender();
	}

	public String getReciveDate(int i){ return s.get(i).getDate();}
	
	public int getNumber(){
		return s.size();
	}
	
}



