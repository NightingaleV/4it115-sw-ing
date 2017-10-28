package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *  
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Riha, Vitezslav Slavik
 * @version    ZS 2016/2017
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda provadi prikaz jdi.
     * 
     * @param  nazev sousedniho prostoru
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá! Sleduj východy";
        }
        
        if (sousedniProstor.isZamcena()) {
            if (plan.getBatoh().obsahujeVec(sousedniProstor.getKlic().getNazev())) {
                sousedniProstor.setZamcena(false);
                
                plan.setAktualniProstor(sousedniProstor);
                return sousedniProstor.dlouhyPopis();

            }
            else {
                return "Dál nemůžeš, nejspíš ti brání nějaká potvora. Nejdřív se jí zbav.";
            }
        }
        
        else {
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
