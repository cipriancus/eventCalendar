/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detect;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import static ucar.unidata.util.Format.i;
import java.util.*;
/**
 *
 * @author GABRIEL
 */
public class FetchAttachments {
    private Fisier f=new Fisier();
    public FetchAttachments(List<Fisier> lista) throws TikaException{

        try {
            InputStream is = null;
            XMLEncoder x = null;
            x = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("fisier.xml")));
            x.writeObject(lista.size());
            for(int i=0;i<lista.size();i++){

                x.writeObject(lista.get(i));}
            x.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FetchAttachments.class.getName()).log(Level.SEVERE, null, ex);
        }



    }


}
