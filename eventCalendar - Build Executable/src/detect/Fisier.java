/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detect;
/**
 *
 * @author GABRIEL
 */
public class Fisier {

    private String nume;
    private String text;
    private String data;
    private String subiect;

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNume(){
        return nume;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData(){
        return data;
    }
    public void setSubiect(String subiect) {
        this.subiect = subiect;
    }

    public String getSubiect(){
        return subiect;
    }


    public void setText(String text) {
        this.text = text;
    }


    public String getText(){
        return text;
    }

}
