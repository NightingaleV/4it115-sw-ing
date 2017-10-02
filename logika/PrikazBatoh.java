package logika;


/**
 * trida PrikazBatoh implementuje pro hru prikaz batoh
 * 
 * @author  Vitezslav slavik 
 * @version prosinec 2016
 */
public class PrikazBatoh implements IPrikaz
{
    private static final String NAZEV = "batoh";
    private HerniPlan plan;

    /**
     * Constructor for objects of class PrikazBatoh
     */
    public PrikazBatoh(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Prikaz vypise obsah batohu
     */
    public String proved(String... parametry) {
        if(plan.getBatoh().obsahBatohu().equals("V tvém inventáři se nachází tyto předměty:")){
            return "Tvůj inventář je momentálně prázdný, vezmi nějaký předmět.";}
        else{
            return plan.getBatoh().obsahBatohu();
        }
    }
    
    public String getNazev() {
        return NAZEV;
    }
}
