/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detectfile;

import java.util.LinkedHashSet;
import org.apache.tika.exception.TikaException;


public class Detect {
    
    public Detect(String filename) throws TikaException{
    
        FetchFile f=new FetchFile(filename);
        
                String text;
		String fileName;
		String date;
		LinkedHashSet<String> location;
		LinkedHashSet<String> persons;
		String time;
		LinkedHashSet<String> org;
                
                
        Event ev = new Event();        
                GetContent content =new GetContent("fisier.xml");
                
                
                System.out.println("asd");
                text=content.getText();
                fileName=content.getNume();
                
                GiveMeDateAndTime giveMeDAT = new GiveMeDateAndTime();
				date = giveMeDAT.giveMeDate(text);

				ev.setDate(date);
				time = giveMeDAT.giveMeTime(text);
				ev.setTime(time);
				// Location
			/*	GiveMeLocationOP giveMeLOP = new GiveMeLocationOP();
                                giveMeLOP.
				location = giveMeLOP.giveMeLocation(text);
				ev.setLocation(location);
				persons = giveMeLOP.giveMePersons(text);
				ev.setPersons(persons);
				org = giveMeLOP.giveMeOrg(text);
				ev.setOrg(org);
	*/
                        
       printEvent(ev);
        
    }
    
    private void printEvent(Event event) {

		System.out.println(event.getDate());
		System.out.println(event.getTime());
		

	}
    
}
