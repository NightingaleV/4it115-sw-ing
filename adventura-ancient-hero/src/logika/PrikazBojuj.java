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
    public IHra hra;
    

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
        Vec stit = new Vec("stit", "Štít, kterým by měl být vybavený každý hoplita.", true);
        Vec zub_01 = new Vec("zub_hydry", "Zub vytržený z těla Hydry", true);
        Vec zub_02 = new Vec("zub_chimery", "Zub vytržený z těla Chiméry", true);
        Vec win = new Vec("win", "", true);


        if (postava == null)
        {
            return "Tato postava v této místnosti není.";
        }
        else if(postava.getJmeno().equals("harpyje") && plan.getBatoh().obsahujeVec("kopi"))
        {
            plan.getAktualniProstor().vlozVec(stit);
            plan.getAktualniProstor().removePostava("harpyje");
            plan.notifyAllObservers();
            return "BOJ: Chvíli je pozoruješ sám schován za kamenem. Všechny za doprovodu krouží nad hnízdem \n"
                    +"a po chvilce odlétají.  Ovšem jedna z nich se však vrací a hlídá hnízdo. Neváháš a plný \n"
                    +"soustředění házíš kopí. Oštěp sviští vzduchem silou blesku. Projel skrze záda a zarazil \n"
                    +"se hluboko do země. Harpyje křičí a mává křídly, je ovšem přibitá k zemi."
                    +"Bereš svůj meč a dílo dokonáváš. V hnízdě je také hoplitský štít. NAPOVEDA: vem stit\n";
        }
        else if(postava.getJmeno().equals("chimera") && plan.getBatoh().obsahujeVec("stit") && plan.getBatoh().obsahujeVec("mec") && plan.getBatoh().obsahujeVec("bronzova_zbroj"))
        {
            plan.getAktualniProstor().vlozVec(zub_02);
            plan.getAktualniProstor().removePostava("chimera");
            plan.notifyAllObservers();
            return "BOJ: Chiméra vyráží jako první. Plnou rychlostí jde proti tobě. V poslední chvíli sis připravil svůj \n"
                    +"štít a útok odrazil. Chiméra je v okamžiku ochromená nárazem. Ohnal se po tobě dračí ocas, ale hbitě \n"
                    +"si táhnul svým mečem a s precizností si ho odsekl od zbytku těla. Monstrum se vzpamatovalo a začalo \n"
                    +"ustupovat. S respektem v očích se krok po kroku vzdalovalo až zaběhlo zpět do jeskyně. Z ještě se\n"
                    +"mrskajícího dračího ocasu vytrháváš zub pro štěstí. NAPOVEDA: vem zub_chimery\n";
        }
        else if(postava.getJmeno().equals("hydra") && plan.getBatoh().obsahujeVec("prsten_moci"))
        {
            plan.getAktualniProstor().vlozVec(zub_01);
            plan.getAktualniProstor().removePostava("hydra");
            plan.notifyAllObservers();
            return "BOJ: Hlavou ti běží pochybné myšlenky. Máš zabít nesmrtelné. Přemýšlíš, jestli celá tahle výprava byl \n"
                    +"dobrý nápad nebo bláznivý nápad čestného muže. Nasadil sis prsten, strach a pochybnosti opadly, cítíš, \n"
                    +"že svedeš i nemožné. V s kopím v ruce pravé, se štítem v té levé se vrháš do chrámu vstříc monstru. Hydra \n"
                    +"odpočívá u oltáře. Drnčení tvé zbroje jí probudilo.\n"
                    + "\n"
                    +"Přibíhá k tobě a hlavy se na tebe sápají. První udeříš štítem, druhou sekneš přes oči hrotem svého oštěpu, \n"
                    +"té třetí uhýbáš skokem do strany. Vybíháš na protiútok, však další hlava tě odhodí 20 loktů daleko. Tvá \n"
                    +"zbroj zmenšila náraz. Kopí ti vypadne na zem. Bereš meč, usekneš jednu z hlav. \n\nOpravdu jí ihned narostou "
                    +"dvě další. \nRozeběhneš se pro kopí a kryješ se štítem. Své kopí házíš a trefeš krk jeden za druhý, až oštěp \n"
                    +"projíždí krkem všem osmi hlavám. Řev dračích hlav bylo slyšel do všech okolních měst a vesnic a lidé \n"
                    +"utíkali do svých domovů v domění, že je postihla zloba bohů. Hlavy se nemohou hýbat a ty využiješ této chvíle\n"
                    +"a svým mečem řežeš podél hadího těla až se vysypají všechny vnitřnosti. Hydra padá do kaluže své vlastní\n"
                    +"krve. Vyrveš dračí zub a dáš ho do batohu. NAPOVEDA: vem zub_hydry\n";
        }
        else if(postava.getJmeno().equals("hades") && plan.getBatoh().obsahujeVec("zub_hydry") && plan.getBatoh().obsahujeVec("zub_chimery") && plan.getBatoh().obsahujeVec("zub_kerbera"))
        {
            plan.getBatoh().pridejVec(win);
            plan.getAktualniProstor().removePostava("hades");
            plan.notifyAllObservers();
            return "BOJ: Přistoupíš k Hádovi. Moc prstenu prostupuje tvým tělem. Tasíš meč. \n"
                    +"Tahem jako malíř kreslící na své plátno setneš hlavu. Nabubřelý bůh okusil lidskou smrtelnost.\n"
                    +"\n"
                    +"Persefona: Dokázal si to, zbavil jsi mě jeho okovů. Podsvětí ovšem musí mít svého vládce, nyní musíš zaujmout\n"
                    +"jeho místo. Ráda budu ženou udatného muže jako jsi ty.\n"
                    +"STÁVÁŠ SE BOHEM A VLÁDCEM PODSVĚTÍ\n"
                    +"Ve tvém sídle se koná svatba, na kterou se schází božská smetánka. Loučíš se svým bratrem. Dojemná chvíle \n"
                    +"zanechala v sále zatracených ticho. Jednou se zase uvidíme, bratře.\n"
                    +"Hádes od té doby svůj čas tráví v chodbě neklidu, kde se hádá s ostatními, kdo by má větší právo, \ndostat svůj život zpět. ";
        }
        else if(
                postava.getJmeno().equals("persefona") || postava.getJmeno().equals("bratr") || postava.getJmeno().equals("kerberos") || postava.getJmeno().equals("hostinsky") || postava.getJmeno().equals("starec") || postava.getJmeno().equals("stamgast") || postava.getJmeno().equals("kentaur") || postava.getJmeno().equals("persefona")
                ){
            return "Tato postava s tebou nechce bojovat.";
        }
        else {
            return sebevrazda;
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
