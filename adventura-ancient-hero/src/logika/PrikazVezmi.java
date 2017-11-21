/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazVezmi představují ...
 *
 * @author    Vitezslav Slavik
 * @version   prosinec 2016
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    
    private HerniPlan plan;
    public IHra hra;
    //== Konstruktory a tovární metody =============================================

    /**
     * Constructor for objects of class PrikazVezmi
     */
    public PrikazVezmi(HerniPlan plan)
    {
        this.plan = plan;

    }
    
    
    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda provadi sber predmetu. Predmet se ulozi do batohu.
     * 
     * @param  nazev predmetu, ktery chceme vzit
     */
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "Nevím, co za předmět mám sebrat.";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = plan.getAktualniProstor().odeberVec(nazevVeci);
        if (vec == null) {
            return "Předmět '" + nazevVeci + "' se v této místnosti nenachází";
        }
        
        if (!vec.isPrenositelna()) {
            plan.getAktualniProstor().vlozVec(vec);
            return "Předmět '" + nazevVeci + "' je i na tebe dost těžký. Neuneseš ho.";
        }
        
        // vlozeni itemu do batohu pokud je v inventari misto.
        Batoh batoh = plan.getBatoh();
        if (!batoh.jeMisto()) {
               plan.getAktualniProstor().vlozVec(vec);
               return "Tvůj inventář je plný, musíš vyhodit nějaký předmět, pro jeho uvolnění.";
           }

           batoh.pridejVec(vec);
           return "Předmět '" + nazevVeci + "' se ti uložil do tvého inventáře.";

    }

    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
