package logika;


/**
 * Trida vytvarejici prikaz poloz.
 * 
 * @author Vitezslav Slavik 
 * @version prosinec 2016
 */
public class PrikazPoloz implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "poloz";
    private Batoh batoh;
    private HerniPlan plan;

    /**
     * Constructor for objects of class PrikazPoloz
     */
    public PrikazPoloz(HerniPlan plan)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     * Provadi prikaz odhod s tim ze odhodi vec z batohu pokud tam takova vec je do aktualni mistnosti.
     * 
     * @param  jmeno predmetu ktery chceme odhodit
     */
    @Override
    public String proved(String... parametry)
    {
        if(parametry.length == 0){
            return "Jaký předmět chceš odhodit? Zadej jeho název";
        }
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Batoh batoh = plan.getBatoh();
        Vec vec = batoh.getVec(nazevVeci);
        if(vec == null){
            return "Takový předmět se v inventáři nenechází, pro zobrazení inventáře napiš \"batoh\"";
        }
        else{
            plan.getBatoh().odeberVec(nazevVeci);
            aktualniProstor.vlozVec(vec);
            return "Odložil si ze svého inventáře předmět " + nazevVeci + " a nechal jej v místnosti.";
        }
    }
    
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
