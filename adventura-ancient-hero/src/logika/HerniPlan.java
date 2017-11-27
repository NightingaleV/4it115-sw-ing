package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;


/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Vitezslav Slavik
 * @version    ZS 2016/2017
 */
public class HerniPlan implements Subject{

    
    private String nicnechce = "Nezaslouží si nic jinného než smrt.";
    private Prostor aktualniProstor;
    private Batoh batoh;

    private List<Observer> listObserveru = new ArrayList<>();
    // private Batoh batoh;

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        batoh = new Batoh();

        // this.batoh = new Batoh();
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
       
        
        Prostor taverna = new Prostor("taverna","\n"+"Na první pohled obyčejná menší taverna. Na baru přiopitý štamgast s mečem u pasu vypráví své strasti \n"
                                                 +"hostinskému, který však věnuje svou pozornost usměvavé dívce z kuchyně. U blízkého stolu parta \n"
                                                 +"sedláků hraje karty,  pochytil si pár slov z rozhovoru a hádají se, číže kráva nadojí nejvíc. \n"
                +"U schodů do patra se válí nějaký mince. V rohu sedí uplně sám starý muž v oděvu kněžího a zaujatě \n"
                +"si čte texty na pergamenu. Na parapetu u okna nechává dívčina vychladnout medový koláč." + "\n", 373,330);
        Prostor zbrojnice = new Prostor("zbrojnice", "\n Přišel si do zbrojnice. Je naplněná vybavením pro hoplitskou jednotku.\n", 115, 330);
        Prostor lernovske_baziny = new Prostor("lernovske_baziny", "\n"
                +"Vešel jsi do bažin. Všude mlha, sotva se držíš stezky, kterou ušlapala kopyta koní. Cítíš na zádech, \n " +
                "že tě někdo pozoruje z povzdálí. Pro jistotu máš svůj meč připravený v ruce. U pradávného stromu na \n" +
                "rozcestí stojí podivná bytost. Napůl muž napůl kůň.\n", 363, 267);
        Prostor hora_ida = new Prostor ("hora_ida", "\nVyšplhal jsi na vrcholek.  Před tebou se otevírá pustina, místo kde nic pozemského už nežije \n " +
                "ani neroste. Všude jen skály a kameny. Kousek opodál vidíš nějaké hnízdo. Kolem je několik harpyjí. \n" +
                "Napůl žena, napůl pták. V hejnu můžou být tvrdým oříškem i pro zkušeného válečníka. \n", 363, 200);
        Prostor rokle_kragu = new Prostor ("rokle_kragu", "\nJdeš po této pustině a cítíš, že podsvětí je blízko. Rozhlédneš se, kolem tebe \n"
                +"jsou všude kosti, hotové pohřebiště zvířecí i lidské. Slyšíš řev lva. Z jeskyně před tebou vyšla \n"
                +"chiméra. Upřeně se na tebe dívá. Naskakuje ti husí kůže. Tohle monstrum má zpředu podobu lva\n"
                +",zadní část divoké kozy a ocas draka, přímo jak z nejhorší noční můry. \n", 363, 137);
        Prostor podsveti = new Prostor ("podsveti", " \n Jsi konečně zde. Vchod hlídá tříhlavý pes Kerberos. Toto obří stvoření má dech jako hnijící" +
                "\nmrtvola a vypadá hladově. Třeba má chuť na koláč \n", 363, 75);
        Prostor reka_styx = new Prostor ("reka_styx", "\nU řeky se seskupují zástupy duší a čekají na převozníka. Po chvilce připlouvá loď.\n"
                                                      +"Vystupuje z ní zamračený pohublý stařec a bere od duší mince.\n", 363, 15);
        Prostor cesta_smrti = new Prostor ("cesta_smrti", "Chodba smrti, které duše zoufají nad svou smrtí. Hádají se zde, kdo\n"
                +"má větší právo se vrátit na pozemský svět. Opodál v rohu sedí tvůj mrtvý bratr.\n", 115, 15);
        Prostor sal_zatracenych = new Prostor ("sal_zatracenych", "Sál zatracených: Velkolepý sál samotného vládce podsvětí. U hodovního stolu sedí\n"
                +"nejstatečnější válečníci, které dostihla smrt v právě probíhající Peloponéské válce. Hádés sedí\n"
                +"na svém železném trůně a je potěšen z příjemné společnosti. Opodál sedí sama královna podsvětí\n"
                +"Persefona. Nádherná dívka, ze které však na pohled srší smutek a žal.\n", 115, 75);
        Prostor chram_mrtvych = new Prostor ("chram_mrtvych", "\nChrám zpustošený řáděním monstra. Již z dálky slyšíš dračí řev.\n",115, 267);
        

        // přiřazují se průchody mezi prostory (sousedící prostory)
        taverna.setVychod(zbrojnice);
        taverna.setVychod(lernovske_baziny);
        zbrojnice.setVychod(taverna);
        zbrojnice.setVychod(lernovske_baziny);
        lernovske_baziny.setVychod(zbrojnice);
        lernovske_baziny.setVychod(taverna);
        lernovske_baziny.setVychod(hora_ida);
        lernovske_baziny.setVychod(chram_mrtvych);
        chram_mrtvych.setVychod(lernovske_baziny);
        hora_ida.setVychod(lernovske_baziny);
        hora_ida.setVychod(rokle_kragu);
        rokle_kragu.setVychod(hora_ida);
        rokle_kragu.setVychod(podsveti);
        podsveti.setVychod(rokle_kragu);
        podsveti.setVychod(reka_styx);
        reka_styx.setVychod(podsveti);
        reka_styx.setVychod(cesta_smrti);
        cesta_smrti.setVychod(reka_styx);
        cesta_smrti.setVychod(sal_zatracenych);
        sal_zatracenych.setVychod(lernovske_baziny);
        sal_zatracenych.setVychod(cesta_smrti);


        // vytvoříme několik věcí
        Vec pivo = new Vec("pivo", "Pivo s pořádnou pěnou, ale žádnej podmírák.", true);
        Vec medovnik = new Vec ("medovnik","Medový koláč", true);
        Vec zlata_mince = new Vec("stater", "Zlatá mince", true);
        Vec obolos = new Vec("obolos", "Stribrna mince", true);
        Vec bronzova_zbroj = new Vec("bronzova_zbroj", "Klasická zbroj, kterou byl vybaven každý hoplita. Teda pokud nebyl zrovna nahý.", true);
        Vec kopi = new Vec("kopi", "Kopí je tvoje prodloužená ruka, lze i hodit.", true);
        Vec mec = new Vec("mec", "Klasický xifos. Meč kterým byl vybavený každý hoplita.", true);
        Vec stit = new Vec("stit", "Štít, kterým by měl být vybavený každý hoplita.", true);
        Vec stul = new Vec("stul", "Stůl nakterém je medovník.", false);
        Vec prsten = new Vec("prsten_moci", "Z člověka udělá poloboha", true);
        Vec zub_03 = new Vec("zub_kerbera", "Zub pekelného psa Kerbera.", true);
        Vec zub_01 = new Vec("zub_hydry", "Zub vytržený z těla Hydry", true);
        Vec zub_02 = new Vec("zub_chimery", "Zub vytržený z těla Chiméry", true);
        Vec vstupenka = new Vec("palubni_listek", "Permanentka", true);
        Vec vec01 = new Vec("vec01", "", false);
        Vec vec02 = new Vec("vec02", "", false);
        Vec win = new Vec("win", "", true);

        
        rokle_kragu.setZamcena(true);
        rokle_kragu.setKlic(stit);
        podsveti.setZamcena(true);
        podsveti.setKlic(zub_02);
        reka_styx.setZamcena(true);
        reka_styx.setKlic(zub_03);
        cesta_smrti.setZamcena(true);
        cesta_smrti.setKlic(vstupenka);

        // umístíme věci do prostorů
        taverna.vlozVec(zlata_mince);
        taverna.vlozVec(medovnik);
        taverna.vlozVec(obolos);
        taverna.vlozVec(stul);
        
        zbrojnice.vlozVec(bronzova_zbroj);
        zbrojnice.vlozVec(kopi);
        sal_zatracenych.vlozVec(prsten);

        
        taverna.setPostava(new Postava("stamgast", "Nějakej problem holomku? Hele kup mi pívo a možná ti nedam na budku",
        "Budeš provokovat, tohle ti nežeru!", "Dík, konečně něco tekutého.", "Pfff, nech si můj meč, já jdu stejně do politiky...", pivo, mec ));
        taverna.setPostava(new Postava("hostinsky", "Co si dáš mladej, nějaký pívo? Za jeden škopek chci zlatej stater, na sekeru nenalejvam. ",
        "Seš hluchej? Dej mi zlatej stater, tohle nechcu!", "Tady máš to pívo mladej.", "Další pívo ti nenaleju, však se tu budeš kolíbat, jak opilý koráb.", zlata_mince, pivo ));
        taverna.setPostava(new Postava("starec", "Jsem služebník bohů z nedalekého chrámu. Před několika dny na chrám zaútočilo obrovské \n" +
                "monstrum. Jako jediný jsem stačil utéct. Obluda se tam usadila, pro mě nemá smysl se vracet,\n"
                +"raději odcestuji pryč. Čekal jsem, že v chrámu budem v bezpečí, že nás ochrání bůh podsvětí, ovšem \nmýlil  jsem se."
                +"\nTY: Ty si Hádův služebník, víš jak se dostat do podsvětí?! Chci vykoupit svého bratra ze spárů smrti.\n"
                +"Stařec: Vím, ale rozmysli než vydáš se tam. Odtud se vrátilo jen pár lidí. Áno, třeba Odysseus, takhle \n" +
                "se jeden jmenoval. Taky mladý a naivní jako ty. Muka ho čekala na cestě tam... Ale dobrá tedy. V rokli \nza horama je místo, kde se země otevírá mrtvým.\n",
        "Nic nepotřebuji, díky!", "", "",vec01,vec02));
        lernovske_baziny.setPostava(new Postava("kentaur", "Vidím, že si zabloudil, sem žádný člověk z města nezavítá.\n" +
                "Pověděl jsi mu o cestě do podsvětí...\n"+
                "Kentaur: jdeš tedy správně bláznivý člověče. Přes hory do rokle. Ovšem v horách žijí harpyje. Bez kopí \n" +
                "máš proti nim jen pramalou šanci. Dál se nachází hnízdo chiméry, jít proti ní bez hoplitského brnění,\nštítu a meče by byl velmi špatný nápad."
                                                           ,"Nic nepotřebuji, díky!","","",vec01,vec02));
        hora_ida.setPostava(new Postava("harpyje","Neumí mluvit...",nicnechce, "","",vec01,vec02));
        rokle_kragu.setPostava(new Postava("chimera","Neumí mluvit...",nicnechce, "","",vec01,vec02));
        podsveti.setPostava(new Postava("kerberos","Neumí mluvit, jen vrčet a štěkat...","Vrčí na tebe a nevypadá, že by tohle chtěl.", "Dal jsi Kerberovi medový koláč. Příběhy o Aeneasovi a dalších nelhaly. Hlavy se začínají hádat \na prát, která bude jíst."
                                                    +"Vítězí ta prostřední a vychutnává si koláč.",
                                        "Všiml sis, že jedna hlava si v souboji o koláč vyrazila zub, který leží na zemi. \nZub sbíráš do batohu, jen tak pro štěstí.",medovnik,zub_03));
        reka_styx.setPostava(new Postava("charon","Ha živý. Takové tu dole moc nevídám. Pro tebe platí stejná pravidla, dej mi minci a převezu tě dál.","Hele chci stříbrnej obolos, jinný uplatky neberu.", "Ha máš minci?","No konečně, tak můžem vyrazit",obolos,vstupenka));
        cesta_smrti.setPostava(new Postava("bratr","Tak se zase setkáváme. Ty ale nejsi mrtvý, znamená to, že jsi se pro mě vrátil?\n"
                                                      +"TY: Jdu za vládcem podsvětí vyjednávat o tvém propuštění. Tvůj život byl příliš krátký a vzat neprávem. Já bych měl být na tvém místě, \nzachránil jsi mi přece život.","Chci se vrátit s tebou na svět.", "","",vec01,vec02));
        sal_zatracenych.setPostava(new Postava("hades","Vzkutku troufalé, aby živý přišel mezi mrtvé. Mladická nerozvážnost...\n"
                +"TY: Přišel jsem pro svého bratra! Je zde uvězněn místo mě neprávem. \n"
                +"\n"
                +"HÁDÉS: Hm, dobrá tedy. Pustím tvého bratra, pokud pro mě splníš úkol. V mém chrámu se usadila Hydra. \n" +
                "Jak víš, hydra je monstrum nesmrtelné, usekneš hlavu a narostou jí dvě další. Zab ji pro mě a tvůj \n" +
                "bratr může jít.\n\n Avšak jako běžný smrtelník nemáš šanci jí porazit. Vem si prsten, který dávám svým \n" +
                "šampionům. Dodá ti sílu, se kterou se rodí jen bůh. Jako důkaz mi z Hydry dones zub.\n"
                +"A teď se dej se do práce!\n","Dones mi zub hydry jako důkaz a tvůj bratr může jít.", "Tak ses vrátil, máš pro mě zub jako důkaz?",
                "Ha, zub z mrtvé hydry. Jak jsem slíbil, tvůj bratr se vrátí mezi živé. Ty máš za sebou dlouhou cestu,\n" +
                        "posaď se, pojez a pověz mi, jak si bojoval s tou mrchou.\n"
                        +"Hoduješ a vyprávíš...\n"
                        +"Hádés: Dobrý souboj, jsem rád, že budu mít za společnost takového válečníka. Pohostil ses u mě v \n" +
                        "podsvětí, to znamená, že tu musíš zůstat. Prsten si nech, zde v podsvětí nemá na tebe žádný vliv. \n" +
                        "Bude ti připomínat, kdo je tu opravdový král.\n", zub_01, win));
        sal_zatracenych.setPostava(new Postava("persefona"," " +
                "Unesl mě a uvěznil, můj mocichtivý manžel.... Mezi mrtvými teď musím celý život být. Za  \n" +
                "domácího mazlíčka tříhlavého psa mám, který v noci leze do postele nám, protože nerad spí sám. \n" +
                "\n"+
                "Zabij mého manžela, zabij ho pro mě. Dělej jak říká, využij sílu prstenu k usmrcení hydry. Vytrhni jí zub. Vím\n" +
                "čí zuby již nosíš v batohu. Avšak nevíš, že Chiméra, Kerberos a Hydra jsou sourozenci. Děti stohlavého\n" +
                "obra Týfóna a Echidny, napůl ženy, napůl hada. Zuby z těchto monster udělají z jejich nositele boha. \n" +
                "Využij tuto sílu k naplnění svého osudu.\n"
                                                         ,"Chci smrt mého manžela, nic jinného si nepřeji.", "", "",vec01,vec02));
        chram_mrtvych.setPostava(new Postava("hydra","Neumí mluvit...",nicnechce, "","",vec01,vec02));

        aktualniProstor = taverna;  // hra začíná v taverně
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       notifyAllObservers();
    }
    
    public boolean hracVyhral() {
        if (getBatoh().obsahujeVec("kopi")) {
            return true;
        }

        return false;
    }

    public Batoh getBatoh(){
        return this.batoh;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }

}
