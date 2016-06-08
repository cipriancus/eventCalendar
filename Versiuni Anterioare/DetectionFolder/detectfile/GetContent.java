/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detectfile;


import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;


public class GetContent {
	private Fisier f;
	private XMLDecoder x;
	public GetContent(String filename)
	{ 
		try {
			x = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
		f=(Fisier) x.readObject();
		
		

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
                System.out.println("asd");
	}
	
	
	
	public String getNume(){
		return f.getNume();
	}
	
	public String getText(){
		return f.getText();
	}
	
	
}
