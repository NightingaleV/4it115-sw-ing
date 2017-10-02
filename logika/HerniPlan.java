package logika;


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
public class HerniPlan {

    
    private String nicnechce = "Nezaslouží si nic jinného než smrt.";
    private Prostor aktualniProstor;
    private Batoh batoh;
    

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
       
        
        Prostor taverna = new Prostor("Taverna","Na první pohled obyčejná menší taverna. Na baru přiopitý štamgast vypráví své strasti života hostinskému, který však \n"
                                                 +"věnuje svou pozornost usměvavé dívce z kuchyně. U blízkého stolu parta sedláků hraje karty,  pochytil si pár slov z rozhovoru a\n"
                                                 +"hádají se, číže kráva nadojí nejvíc. U schodů do patra se válí nějaký mince. V rohu sedí uplně sám starý muž v oděvu\n"
                                                 +"kněžího a zaujatě si čte texty na pergamenu. Na parapetu u okna nechává dívčina vychladnout medový koláč.\n");
        Prostor zbrojnice = new Prostor("Zbrojnice", "Přišel si do zbrojnice. Je naplněná vybavením pro hoplitskou jednotku.\n");                                         
        Prostor lernovske_baziny = new Prostor("Lernovské_bažiny", "Vešel jsi do bažin. Všude mlha, sotva se držíš stezky, kterou ušlapala kopyta koní. Cítíš na zádech, že tě někdo pozoruje z povzdálí. \nPro jistotu máš "
                                                     +"svůj meč připravený v ruce. U pradávného stromu na rozcestí stojí podivná bytost. Napůl muž napůl kůň.\n");
        Prostor hora_ida = new Prostor ("Hora_Ida", "Vyšplhal jsi na vrcholek.  Před tebou se otevírá pustina, místo kde nic pozemského \n"
                                                    +"už nežije ani neroste. Všude jen skály a kameny. Kousek opodál vidíš nějaké hnízdo. Kolem je \n" 
                                                    +"několik harpyjí. Napůl žena, napůl pták. V hejnu můžou být tvrdým oříškem i pro zkušeného válečníka. \n");
        Prostor rokle_kragu = new Prostor ("Rokle_Kragu", "Jdeš po této pustině a cítíš, že podsvětí je blízko. Rozhlédneš se, kolem tebe \n"
                                                          +"jsou všude kosti, hotové pohřebiště zvířecí i lidské. Slyšíš řev lva. Z jeskyně před tebou vyšla \n"
                                                          +"chiméra. Upřeně se na tebe dívá. Naskakuje ti husí kůže. Tohle monstrum má zpředu podobu \n"
                                                          +"lva zadní část divoké kozy ocas draka, přímo jak z nejhorší noční můry. \n");                                            
        Prostor podsveti = new Prostor ("Podsvětí", "Jsi konečně zde. Vchod hlídá tříhlavý pes Kerberos. Toto obří stvoření má dech jako hnijící mrtvola a vypadá hladově.\n"
                                                     +"Třeba má chuť na koláč");
        Prostor reka_styx = new Prostor ("řeka_Styx", "U řeky se seskupují zástupy duší a čekají na převozníka. Po chvilce připlouvá loď.\n" 
                                                      +"Vystupuje z ní zamračený pohublý stařec a bere od duší mince.\n");
        Prostor chodba_neklidu = new Prostor ("Chodba_Neklidu", "Chodba neklidných duší, které si zoufají nad svou smrtí. Hádají se zde, kdo\n"
                                                            +"má větší právo se vrátit na pozemský svět. Opodál v rohu sedí tvůj mrtvý bratr.\n");
        Prostor sal_zatracenych = new Prostor ("Sál_Zatracených", "Sál zatracených: Velkolepý sál samotného vládce podsvětí. U hodovního stolu sedí\n"
                                                                  +"nejstatečnější válečníci, které dostihla smrt v právě probíhající Peloponéské válce. Hádés\n"
                                                                  +"sedí na svém železném trůně a je potěšen z příjemné společnosti. Opodál sedí sama královna\n"
                                                                  +"podsvětí Persefona. Nádherná dívka, ze které však srší na pohled smutek a žal.\n");                                                    
        Prostor chram_mrtvych = new Prostor ("Chrám_mrtvých", "Chrám zpustošený řáděním monstra. Již z dálky slyšíš dračí řev.");                                             
        

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
        reka_styx.setVychod(chodba_neklidu);
        chodba_neklidu.setVychod(reka_styx);
        chodba_neklidu.setVychod(sal_zatracenych);
        sal_zatracenych.setVychod(lernovske_baziny);
        sal_zatracenych.setVychod(chodba_neklidu);
        
        
        

        // vytvoříme několik věcí
        Vec pivo = new Vec("pivo", "Pivo s pořádnou pěnou, ale žádnej podmírák.", true);
        Vec medovnik = new Vec ("medovník","Medový koláč", true);
        Vec zlata_mince = new Vec("stater", "Zlatá mince", true);
        Vec stribrna_mince = new Vec("obolos", "Stribrna mince", true);
        Vec bronzova_zbroj = new Vec("bronzová_zbroj", "Klasická zbroj, kterou byl vybaven každý hoplita. Teda pokud nebyl zrovna nahý.", true);
        Vec mec = new Vec("meč", "Klasický xifos. Meč kterým byl vybavený každý hoplita.", true);
        Vec stit = new Vec("štít", "Štít, kterým by měl být vybavený každý hoplita.", true);
        Vec stul = new Vec("stůl", "Stůl nakterém je medovník.", false);
        Vec prsten = new Vec("prsten_moci", "Z člověka udělá poloboha", true);
        Vec zub_03 = new Vec("zub_Kerbera", "Zub pekelného psa Kerbera.", true);
        Vec zub_01 = new Vec("zub_Hydry", "Zub vytržený z těla Hydry", true);
        Vec zub_02 = new Vec("zub_Chiméry", "Zub vytržený z těla Chiméry", true);
        Vec vstupenka = new Vec("palubní_lístek", "Permanentka", true);
        Vec vec01 = new Vec("vec01", "", false);
        Vec vec02 = new Vec("vec02", "", false);
        Vec win = new Vec("win", "", true);
        
        rokle_kragu.setZamcena(true);
        rokle_kragu.setKlic(stit);
        podsveti.setZamcena(true);
        podsveti.setKlic(zub_02);
        reka_styx.setZamcena(true);
        reka_styx.setKlic(zub_03);
        chodba_neklidu.setZamcena(true);
        chodba_neklidu.setKlic(vstupenka);

        // umístíme věci do prostorů
        taverna.vlozVec(zlata_mince);
        taverna.vlozVec(medovnik);
        taverna.vlozVec(stribrna_mince);
        taverna.vlozVec(stul);
        
        zbrojnice.vlozVec(bronzova_zbroj);
        sal_zatracenych.vlozVec(prsten);
        
        
        
        taverna.setPostava(new Postava("Štamgast", "Nějakej problem holomku? Hele kup mi pívo a možná ti nedam na budku", 
        "Budeš provokovat, tohle ti nežeru!", "Dík, konečně něco tekutého.", "Pfff, nech si můj meč, já jdu stejně do politiky...", pivo, mec ));
        taverna.setPostava(new Postava("Hostinský", "Co si dáš mladej, nějaký pívo? Za jeden škopek chci zlaťák, na sekeru nenalejvam. ", 
        "Seš hluchej? Dej mi zlaťák, tohle nechcu!", "Tady máš to pívo mladej.", "Další pívo ti nenaleju, však se tu budeš kolíbat, jak opilý koráb.", zlata_mince, pivo ));
        taverna.setPostava(new Postava("Stařec", "Jsem služebník bohů z nedalekého chrámu. Před několika dny na chrám zaútočilo obrovské monstrum. Jako jediný jsem \nstačil utéct. Obluda se tam usadila, pro mě"
                                                 +"nemá smysl se vracet, raději odcestuji pryč. Čekal jsem, že v chrámu budem v bezpečí,\n že nás ochrání bůh podsvětí, ovšem mýlil jsem se.\n"
                                                 +"TY: Ty si Hádův služebník, víš jak se dostat do podsvětí?! Chci vykoupit svého bratra ze spárů smrti.\n"
                                                 +"Stařec: Vím, ale rozmysli než vydáš se tam. Odtud se vrátilo jen pár lidí. Áno, třeba Odysseus, takhle se jeden jmenoval. Taky mladý\na naivní jako ty. Muka ho čekala na"
                                                 +" cestě tam... Ale dobrá tedy. V rokli za horama je místo, kde se země otevírá mrtvým.\n", 
        "Nic nepotřebuji, díky!", "", "",vec01,vec02));
        lernovske_baziny.setPostava(new Postava("Kentaur", "Vidím, že si zabloudil, sem žádný člověk z města nezavítá.\nPověděl jsi mu o cestě do podsvětí...\n"
                                                           +"Kentaur: jdeš tedy správně bláznivý člověče. Přes hory do rokle. Ovšem v horách žijí harpyje. Bez kopí máš proti nim jen pramalou šanci. \nVezmi si to mé, budeš ho potřebovat."
                                                           ,"Nic nepotřebuji, díky!","","",vec01,vec02));
        hora_ida.setPostava(new Postava("Harpyje","Neumí mluvit...",nicnechce, "","",vec01,vec02));
        rokle_kragu.setPostava(new Postava("Chiméra","Neumí mluvit...",nicnechce, "","",vec01,vec02));
        podsveti.setPostava(new Postava("Kerberos","Neumí mluvit, jen vrčet a štěkat...","Vrčí na tebe a nevypadá, že by tohle chtěl.", "Dal jsi Kerberovi medový koláč. Příběhy o Aeneasovi a dalších nelhaly. Hlavy se začínají hádat a prát, která bude jíst. \n"
                                                    +"Vítězí ta prostřední a vychutnává si koláč.",
                                        "Všiml sis, že jedna hlava si v souboji o koláč vyrazila zub, který leží na zemi. Zub sbíráš do batohu, jen tak pro štěstí.",medovnik,zub_03));
        reka_styx.setPostava(new Postava("Cháron","Ha živý. Takové tu dole moc nevídám. Pro tebe platí stejná pravidla, dej mi minci a převezu tě dál.","Hele chci stříbrnej obolos, jinný uplatky neberu.", "Ha máš minci?","No konečně, tak můžem vyrazit",stribrna_mince,vstupenka));
        chodba_neklidu.setPostava(new Postava("Bratr","Tak se zase setkáváme. Ty ale nejsi mrtvý, znamená to, že jsi se pro mě vrátil?\n"
                                                      +"TY: Jdu za vládcem podsvětí vyjednávat o tvém propuštění. Tvůj život byl příliš krátký a vzat neprávem. Já bych měl být na tvém místě, \nzachránil jsi mi přece život.","Chci se vrátit s tebou na svět.", "","",vec01,vec02));
        sal_zatracenych.setPostava(new Postava("Hádés","Vzkutku troufalé, aby živý přišel mezi mrtvé. Mladická nerozvážnost...\n"
                                                      +"TY: Přišel jsem pro svého bratra! Je zde uvězněn místo mě neprávem. \n"
                                                      +"Hádés: Hm, dobrá tedy. Pustím tvého bratra, pokud pro mě splníš úkol. V mém chrámu se usadila Hydra. Jak víš, hydra je monstrum\n"
                                                      +"nesmrtelné, usekneš hlavu a narostou jí dvě další. Zab ji pro mě a tvůj bratr může jít. Avšak jako běžný smrtelník nemáš šanci jí\n"
                                                      +"porazit. Vem si prsten, který dávám svým šampionům. Dodá ti sílu, se kterou se rodí jen bůh. Jako důkaz mi z Hydry dones zub.\n"
                                                      +"A teď se dej se do práce!\n","Dones mi zub hydry jako důkaz a tvůj bratr může jít.", "Tak ses vrátil, máš pro mě zub jako důkaz?",
                                                         "Ha, zub z mrtvé hydry. Jak jsem slíbil, tvůj bratr se vrátí mezi živé. Ty máš za sebou dlouhou cestu, posaď se, pojez a pověz mi, jak si\n"
                                                         +"bojoval s tou mrchou.\n"
                                                         +"Hoduješ a vyprávíš...\n"
                                                         +"Hádés: Dobrý souboj, jsem rád, že budu mít za společnost takového válečníka. Pohostil ses u mě v podsvětí, to znamená, že tu musíš\n"
                                                         +"zůstat. Prsten si nech, zde v podsvětí nemá na tebe žádný vliv. Bude ti připomínat, kdo je tu opravdový král.\n",zub_01,win));
        sal_zatracenych.setPostava(new Postava("Persefona"," Unesl mě a uvěznil, můj mocichtivý manžel.... Mezi mrtvými teď musím celý život být. Za domácího mazlíčka tříhlavého psa\n"
                                                         +"mám, který v noci leze do postele nám, protože nerad spí sám. Zabij mého manžela, zabij ho pro mě. Dělej jak říká, využij sílu prstenu\n"
                                                         +"k usmrcení hydry. Vytrhni jí zub. Vím čí zuby již nosíš v batohu. Avšak nevíš, že Chiméra, Kerberos a Hydra jsou sourozenci. Děti\n"
                                                         +"stohlavého obra Týfóna a Echidny, napůl ženy, napůl hada. Zuby z těchto monster udělají z jejich nositele boha. Využij tuto \nsílu k naplnění svého osudu.\n"
                                                         ,"Chci smrt mého manžela, nic jinného si nepřeji.", "", "",vec01,vec02));
        chram_mrtvych.setPostava(new Postava("Hydra","Neumí mluvit...",nicnechce, "","",vec01,vec02));

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
    }
    
    public boolean hracVyhral() {
        if (getBatoh().obsahujeVec("win")) {
            return true;
        }
        
        return false;
    }
    
    public Batoh getBatoh(){
        return this.batoh;
    }

}
