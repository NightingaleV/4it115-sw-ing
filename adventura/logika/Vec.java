/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.Map;


/*******************************************************************************
 * Instance třídy Vec představují ...
 *
 * @author    Vitezslav Slavik
 * @version   prosinec 2016
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    private boolean prenositelna;
    private Map<String,Vec> seznamVeci;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, String popis, boolean prenositelna)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getNazev() {
        return nazev;
    }
    
    public String getPopis() {
        return popis;
    }
    
    /**
     * Metoda vracejici prenositelnost veci
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }
    
    public void vlozVec (Vec neco) {
        seznamVeci.put(neco.getNazev(), neco);
    }
    // Možná bude potřeba přidat settery pro atributy 'popis' a 'prenositelna'.
    // Atribut 'nazev' by se měnit neměl.

    //== Soukromé metody (instancí i třídy) ========================================

}
