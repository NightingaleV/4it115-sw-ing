package logika;


/**
 * Trida utvarejici prikaz bojuj. 
 * 
 * @author Vitezslav Slavik 
 * @version leden 2016
 */
public class PrikazBojuj implements IPrikaz
{
    // instance variables - replace the example below with your own
    private static final String NAZEV = "bojuj";
    private HerniPlan plan;
    private String sebevrazda = "Správný válečník pozná, kdy je útok sebevražedný, a tento teda je. Sežeň si lepší vybavení.";
    

    /**
     * Constructor for objects of class PrikazBojuj
     */
    public PrikazBojuj(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     * Provadi prikaz bojuj, provede boj s postavou. Pri vyhranem boji, prida predmet
     *
     * @param nazev postavy se kterou chce hrac bojovat
     * @return zpráva, kterou hra vypíše hráči
     */
    public String proved(String... parametry) {
        
        if (parametry.length == 0) {
            return "S kým mám bojovat";  
        }

        Prostor aktualniProstor = plan.getAktualniProstor();
        String jmenoPostavy = parametry[0];
        Postava postava = aktualniProstor.vyberPostava(jmenoPostavy);
        Vec stit = new Vec("štít", "Štít, kterým by měl být vybavený každý hoplita.", true);
        Vec zub_01 = new Vec("zub_Hydry", "Zub vytržený z těla Hydry", true);
        Vec zub_02 = new Vec("zub_Chiméry", "Zub vytržený z těla Chiméry", true);
        Vec win = new Vec("win", "", true);

        if (postava == null) {
            return "Tato postava v této místnosti není.";
        }
        else {
            if (postava.getJmeno().equals("Harpyje")) {
                if (plan.getBatoh().obsahujeVec("kopí") && plan.getBatoh().obsahujeVec("meč") && plan.getBatoh().obsahujeVec("bronzová_zbroj")) {
                    if(plan.getBatoh().jeMisto()){
                        plan.getBatoh().pridejVec(stit);
                    }
                    else {
                        plan.getAktualniProstor().vlozVec(stit);
                    }
                    plan.getAktualniProstor().vymazPostavu("Harpyje");
                    return "BOJ: Chvíli je pozoruješ sám schován za kamenem. Všechny za doprovodu krouží nad hnízdem a po chvilce odlétají. Ovšem jedna z nich \n"
                    +"se však vrací a hlídá hnízdo. Neváháš a plný soustředění házíš kopí. Oštěp sviští vzduchem silou blesku. Projel skrze záda a zarazil se\n"
                    +"hluboko do země. Harpyje křičí a mává křídly, je ovšem přibitá k zemi. Bereš svůj meč a dílo dokonáváš. V hnízdě si našel vedle kostí\n"
                    +"také hoplitský štít.\n"; 
                }
                else {
                    return sebevrazda;
                    }
                }
                
            if (postava.getJmeno().equals("Chiméra")) {
                if (plan.getBatoh().obsahujeVec("štít")) {
                    if(plan.getBatoh().jeMisto()){
                        plan.getBatoh().pridejVec(zub_02);
                    }
                    else {
                        plan.getAktualniProstor().vlozVec(zub_02);
                    }
                    plan.getAktualniProstor().vymazPostavu("Chiméra");
                    return "BOJ: Chiméra vyráží jako první. Plnou rychlostí jde proti tobě. V poslední chvíli sis připravil svůj štít a útok odrazil. Chiméra je v\n"
                    +"okamžiku ochromená nárazem. Ohnal se po tobě dračí ocas, ale hbitě si táhnul svým mečem a s precizností si ho odsekl od zbytku těla.\n"
                    +"Monstrum se vzpamatovalo a začalo ustupovat. S respektem v očích se krok po kroku vzdalovalo až zaběhlo zpět do jeskyně. Z ještě se\n"
                    +"mrskajícího dračího ocasu vytrháváš zub pro štěstí.\n"; 
                }
                else {
                    return sebevrazda;
                    }
                }  
            
            if (postava.getJmeno().equals("Hydra")) {
                if (plan.getBatoh().obsahujeVec("prsten_moci")) {
                    if(plan.getBatoh().jeMisto()){
                        plan.getBatoh().pridejVec(zub_01);
                    }
                    else {
                        plan.getAktualniProstor().vlozVec(zub_01);
                    }
                    plan.getAktualniProstor().vymazPostavu("Hydra");
                    return "BOJ: Hlavou ti běží pochybné myšlenky. Máš zabít nesmrtelné. Přemýšlíš, jestli celá tahle výprava byl dobrý nápad nebo\n"
                    +"bláznivý nápad čestného muže. Nasadil sis prsten, strach a pochybnosti opadly, cítíš, že svedeš i nemožné. V s kopím v ruce\n"
                    +"pravé, se štítem v té levé se vrháš do chrámu vstříc monstru. Hydra odpočívá u oltáře. Drnčení tvé zbroje jí probudilo.\n"
                    +"\n"
                    +"Přibíhá k tobě a hlavy se na tebe sápají. První udeříš štítem, druhou sekneš přes oči hrotem svého oštěpu, té třetí uhýbáš\n"
                    +"skokem do strany. Vybíháš na protiútok, však další hlava tě odhodí 20 loktů daleko. Tvá zbroj zmenšila náraz. Kopí ti vypadne\n"
                    +"na zem. Bereš meč usekneš jednu z hlav. Opravdu jí ihned narostou dvě další. Rozeběhneš se pro kopí a kryješ se štítem. Své\n"
                    +"kopí házíš a trefeš krk jeden za druhý, až oštěp projíždí krkem všem osmi hlavám. Řev všech dračích hlav bylo slyšel do všech\n"
                    +"okolních měst a vesnic a lidé utíkali do svých domovů v domnění, že je postihla zloba bohů. Hlavy se nemohou hýbat a ty\n"
                    +"využiješ této chvíle a svým mečem řežeš podél hadího těla až se vysypají všechny vnitřnosti. Hydra padá do kaluže své vlastní\n"
                    +"krve. Vyrveš dračí zub a dáš ho do batohu.\n"; 
                }
                else {
                    return sebevrazda;
                    }
                }
                
            if (postava.getJmeno().equals("Hádés")) {
                if (plan.getBatoh().obsahujeVec("zub_Hydry")) {
                    if(plan.getBatoh().jeMisto()){
                        plan.getBatoh().pridejVec(win);
                    }
                    else {
                        plan.getAktualniProstor().vlozVec(win);
                    }
                    return "BOJ: Přistoupíš k Hádovi. Tasíš meč. Vládce podsvětí se začne hlasitě smát, poté si všimne tvého dračího náhrdelníku a tvář mu strne.\n"
                    +"Tahem jako malíř kreslící na své plátno setneš hlavu. Nabubřelý bůh okusil lidskou smrtelnost.\n"
                    +"\n"
                    +"Persefona: Dokázal si to, zbavil jsi mě jeho okovů. Podsvětí ovšem musí mít svého vládce, nyní musíš zaujmout jeho místo.\n"
                    +"Ráda budu ženou udatného muže jako jsi ty.\n"
                    +"STÁVÁŠ SE BOHEM A VLÁDCEM PODSVĚTÍ\n"
                    +"Ve tvém sídle se koná svatba, na kterou se schází božská smetánka. Loučíš se svým bratrem. Dojemná chvíle nechala v sále\n"
                    +"zatracených ticho. Jednou se zase uvidíme bratře.\n"
                    +"Hádes od té doby svůj čas tráví v chodbě neklidu, kde se hádá s ostatními, kdo by má větší právo dostat svůj život zpět. "; 
                }
                else {
                    return sebevrazda;
                    }
                }      
            
            else {
                return "S touto postavou nelze bojovat";
            }
        }

    }
    
   
    
    /**
     * Metoda vrací název příkazu (slovo, které používá hrác pro jeho vyvolání
     *
     * @return název příkazu
     */
    public String getNazev() {
        return NAZEV;
    }
   
}
