/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detectfile;

import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 *
 * @author GABRIEL
 */
public class FetchFile {
    private Fisier f=new Fisier();
    public FetchFile(String filename) throws TikaException{
    
    	InputStream is = null;

		try {
			is = new BufferedInputStream(
					new FileInputStream(new java.io.File(filename)));

			Parser parser = new AutoDetectParser();
			ContentHandler handler = new BodyContentHandler();

			Metadata metadata = new Metadata();

			parser.parse(is, handler, metadata, new ParseContext());
                        
                        System.out.println(handler.toString());
                      
                        f.setText(handler.toString());
                        f.setNume(filename);
                        XMLEncoder x;
				try {
					x = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("fisier.xml")));
					 x.writeObject(f);
					    x.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                        

			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    
    }
    
    
    
}
