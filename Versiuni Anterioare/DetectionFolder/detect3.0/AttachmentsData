/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detect;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.apache.tika.exception.TikaException;

/**
 *
 * @author Teo
 */
public class AttachmentsData {

    public List<Fisier> f;
    int contor;

    public AttachmentsData(String userName,String password,int option) {


        contor=0;
        AttachmentsDetection receiver = new AttachmentsDetection();

        try {
            receiver.downloadEmailAttachments(userName, password,option);


        } catch (TikaException ex) {
            Logger.getLogger(AttachmentsDetection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(AttachmentsData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AttachmentsData.class.getName()).log(Level.SEVERE, null, ex);
        }

        f = new ArrayList<>();
    }
    void getContent() throws FileNotFoundException{
        try { Fisier get;
            XMLDecoder x;
            x = new XMLDecoder(new BufferedInputStream(new FileInputStream("fisier.xml")));
            int index=(Integer) x.readObject();
            for(int i=0;i<index;i++){
                get=(Fisier) x.readObject();

                f.add(get);

            }     } catch (FileNotFoundException ex) {
            Logger.getLogger(AttachmentsData.class.getName()).log(Level.SEVERE, null, ex);
        }





    }

    void addFisier(Fisier fisier) {
        contor++;
        f.add(fisier);
    }

    int getNrOfAtachements() {
        return f.size();

    }

    String getDateReceivedAtachement(int i) {
        return f.get(i).getData();
    }

    String getSubjectAtachement(int i) {
        return f.get(i).getSubiect();
    }

    String getTextOfAtachement(int i) {
        String message ="We haven't found attachments";
        if(!f.isEmpty()){
            return f.get(i).getText();

        }
        else

            return message;
    }

}
