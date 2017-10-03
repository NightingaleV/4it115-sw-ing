package logika;


/**
 * Trida Postava vytvari moznost pridani postav do hry
 * 
 * @author Vitezslav Slavik
 * @version prosinec 2016
 */
public class Postava
{
    private String jmeno;
    private String proslov;
    private Vec coMa;
    private Vec coChce;
    private String mluvPo;
    private String proslovNechce;
    private String proslovChce;
    public boolean vymeneno = false;

    /**
     * Constructor for objects of class Postava
     */
    public Postava(String jmeno, String proslov, String proslovNechce, String proslovChce, String mluvPo, Vec coChce, Vec coMa)
    {
        this.jmeno = jmeno;
        this.proslov = proslov;
        this.proslovNechce = proslovNechce;
        this.proslovChce = proslovChce;
        this.mluvPo = mluvPo;
        this.vymeneno = vymeneno;
        this.coChce = coChce;
        this.coMa = coMa;
    }
    
    /**
     * Metoda vracejici rec osoby
     */
    public String toString() {
        return jmeno+": " + proslov;
    }
    
    /**
     * Metoda vracejici jmeno osoby
     */
    public String getJmeno() {
        return jmeno;
    }
    
    /**
     * Metoda vracejici predmet co osoba chce
     */
    public Vec getCoChce() {
        return coChce;
    }
    
    /**
     * Metoda vracejici predmet co da osoba vymenou
     */
    public Vec getCoMa() {
        return coMa;
    }
    
    /**
     * Metoda vracejici stav vymeny
     */
    public boolean probehlaVymena(){
        return vymeneno;
    }
    
     /**
     * Metoda vraci rozhovory pri vymene
     */
    public String getChci() {
        {return jmeno+": "+proslovChce;}
    }
    
    public String getNechci() {
        {return jmeno+": "+proslovNechce;}
    }
    
    public String getProslovPo() {
        {return jmeno+": "+mluvPo;}
    }
    
}
