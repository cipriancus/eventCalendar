/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testare;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andreea
 */
public class GetContentTest {
    
    @Test public void testContentTest(){
        XMLEncoder x;
        EmailList lista = new EmailList();
        
        
        Email email = new Email();
        email.setContent("Ana re mere si se le mananca!!");
        email.setDate("23-12-xs0l");
        email.setEmailNumber(12);
        email.setReciver("ana@yahoo.com");
        email.setSender("@gheorghe@gmail.info.ro");
        email.setSubject("[Vacanta!!]");
        List<Email> myMails = new ArrayList<>();
        myMails.add(email);
        lista.setEmails(myMails);
		try {
			x = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("test.xml")));
			x.writeObject(lista);
			x.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
                
        String filename = "test.xml";
        GetContent content = new GetContent(filename);
        boolean response = true;
        for(int i=0;i<content.getNumber();i++)
            response = response && ( content.getEmailSender(i) == null ? myMails.get(i).getSender() == null : content.getEmailSender(i).equals(myMails.get(i).getSender()) ) 
                    && (content.getEmailText(i) == null ? myMails.get(i).getContent() == null : content.getEmailText(i).equals(myMails.get(i).getContent())) 
                    &&( content.getSubject(i) == null ? myMails.get(i).getSubject() == null : content.getSubject(i).equals(myMails.get(i).getSubject()) )
                  ;
        assertEquals(true, response);
    } 
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
}