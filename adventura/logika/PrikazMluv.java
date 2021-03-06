package logika;


/**
 * Trida PrikazMluv vytvari moznost rozhovoru s postavami prikazem "mluv"
 * 
 * @author Vitezslav Slavik 
 * @version prosinec 2016
 */
public class PrikazMluv implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "mluv";
    private HerniPlan plan;

    /**
     * Constructor for objects of class PrikazMluv
     */
    public PrikazMluv(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     * Metoda provadi prikaz mluv, vrati rozhovor s osobou.
     */
    public String proved(String... parametry) {
        if (parametry.length != 1) {
            return "Můžeš mluvit jen s jednou postavu naráz.";
        }   
        Prostor aktualniProstor = plan.getAktualniProstor();
        String jmenoPostavy = parametry[0];
        Postava postava = aktualniProstor.vyberPostava(jmenoPostavy);
        Vec kopi = new Vec("kopí", "Kopí je tvoje prodloužená ruka, lze i hodit.", true);
        if (postava==null){
            return "Tato postava v této místnosti není.";}
        if (postava.getJmeno().equals("Kentaur")) {
            plan.getBatoh().pridejVec(kopi);
            return postava.toString();
        }
        else {    
            return postava.toString();
        }
    }
    
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
