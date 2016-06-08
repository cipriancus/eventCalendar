/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucia_000
 */
public class GiveMeDateAndTimeTest {
    
    public GiveMeDateAndTimeTest() {
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

    /**
     * Test of giveMeDate method, of class GiveMeDateAndTime.
     */
    @Test
    public void testGiveMeDate1() {
        System.out.println("giveMeDate1");
        String text = "12-05-2016";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "12-05-2016";
        String result = instance.giveMeDate(text);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    @Test
    public void testGiveMeDate2() {
        System.out.println("giveMeDate2");
        String text = "June 23, 2016";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "June 23, 2016";
        String result = instance.giveMeDate(text);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    @Test
    public void testGiveMeDate3() {
        System.out.println("giveMeDate3");
        String text = "09:05:16";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "09:05:16";
        String result = instance.giveMeDate(text);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    @Test
    public void testGiveMeDate4() {
        System.out.println("giveMeDate4");
        String text = "31th of May and 3th of June";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "31th of May and 3th of June";
        String result = instance.giveMeDate(text);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    @Test
    public void testGiveMeDate5() {//Test esuat de fiecare data cand ziua ese formata dintr-o cifra
        System.out.println("giveMeDate5");
        String text = "9 december 2017";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "9 december 2017";
        String result = instance.giveMeDate(text);
        assertEquals(expResult, result);
       
    }
     @Test
    public void testGiveMeDate6() {
        System.out.println("giveMeDate6");
        String text = "17 december 2017";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "17 december 2017";
        String result = instance.giveMeDate(text);
        assertEquals(expResult, result);
        
    }
    @Test
    public void testGiveMeDate7() {
        System.out.println("giveMeDate7");
        String text = "In May, 1234 is my favorite number";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "nu am gasit data";
        String result = instance.giveMeDate(text);
        assertEquals(expResult, result);
   
    }
    @Test
    public void testGiveMeDate8() {
        System.out.println("giveMeDate8");
        String text = "You 2 may come with me";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "nu am gasit data";
        String result = instance.giveMeDate(text);
        assertEquals(expResult, result);
   
    }
    @Test
    public void testGiveMeDate9() {//April se poate înlociu cu August
        System.out.println("giveMeDate9");
        String text = "Me and April 2 old friends...";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "nu am gasit data";
        String result = instance.giveMeDate(text);
        assertEquals(expResult, result);
   
    }
    @Test
    public void testGiveMeDate10() {
        System.out.println("giveMeDate10");
        String text = "I have 10 flowers ...";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "nu am gasit data";
        String result = instance.giveMeDate(text);
        assertEquals(expResult, result);
   
    }
    @Test
    public void testGiveMeDate11() {
        System.out.println("giveMeDate11");
        String text = "10thndth June 2016";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "nu am gasit data";
        String result = instance.giveMeDate(text);
        assertEquals(expResult, result);
   
    }
    /**
     * Test of giveMeTime method, of class GiveMeDateAndTime.
     */
    @Test
    public void testGiveMeTime1() {
        System.out.println("giveMeTime1");
        String text = "Let's meet at 2 AM";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "2 AM";
        String result = instance.giveMeTime(text);
        assertEquals(expResult, result);
       
    }
    
    @Test
    public void testGiveMeTime2() {
        System.out.println("giveMeTime2");
        String text = "See you at 20:30.10 other people will come ";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "20:30";
        String result = instance.giveMeTime(text);
        assertEquals(expResult, result);
       
    }
    
    @Test
    public void testGiveMeTime3() {
        System.out.println("giveMeTime3");
        String text = "I have many flowers, I mean 20:15 red and 5 yellow";
        String text2 = "I have 12.55 grames of coca";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "nu am gasit timp";
        String result = instance.giveMeTime(text);
        assertEquals(expResult, result);
       
    }
    @Test
    public void testGiveMeTime4() {
        System.out.println("giveMeTime4");
        String text = "It's 123:456";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "nu am gasit timp";
        String result = instance.giveMeTime(text);
        assertEquals(expResult, result);
       
    }
    @Test
    public void testGiveMeTime5() {
        System.out.println("giveMeTime5");
        String text = "Friday, April 1, 2012 at 8pm. ";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "8pm";
        String result = instance.giveMeTime(text);
        assertEquals(expResult, result);
       
    }
    
    @Test
    public void testGiveMeTime6() {
        System.out.println("giveMeTime6");
        String text = "Please join us on Friday, May 29, 2016 , 8:00 pm at Emeriko Collegiate ";
        GiveMeDateAndTime instance = new GiveMeDateAndTime();
        String expResult = "8:00 pm";
        String result = instance.giveMeTime(text);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of RemoveNullValue method, of class GiveMeDateAndTime.
     */
    @Test
    public void testRemoveNullValue1() {//elemente cu lungime mai mare ca 0, egale cu 0 sau null
        System.out.println("RemoveNullValue1");
        String[] firstArray = {"ana",null,"dan","","N123Sd"};
        String[] expResult = {"ana","dan","N123Sd"};
        String[] result = GiveMeDateAndTime.RemoveNullValue(firstArray);
        assertArrayEquals(expResult, result);
        
    }
    @Test
    public void testRemoveNullValue2() {//toate elementele sunt nule sau de lungime zero
        System.out.println("RemoveNullValue2");
        String[] firstArray = {"",null,null,"",""};
        String[] expResult = {};
        String[] result = GiveMeDateAndTime.RemoveNullValue(firstArray);
        assertArrayEquals(expResult, result);
        
    }

    /**
     * Test of maxString method, of class GiveMeDateAndTime.
     */
    @Test
    public void testMaxString1() {//array ul primeste stringuri de dfierite lungimi si-l returneaza pe cel mai mare
        System.out.println("maxString1 stringuri de diferite lungimi");
        String[] array = {"a","abc","ab"};
        String expResult = "abc";
        String result = GiveMeDateAndTime.maxString(array);
        assertEquals(expResult, result);
       
    }
    @Test
    public void testMaxString2() {// arrayul nu are niciun element, functia ar trebui sa trateze NullPointerException
        System.out.println("maxString2 array null");
        String[] array = null;
        String expResult = null;
        String result = GiveMeDateAndTime.maxString(array);
        assertEquals(expResult, result);
       
    }

    
    
}
