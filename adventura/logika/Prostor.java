package logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Vitezslav Slavik
 * @version ZS 2016/2017
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Set<Postava> postavy;
    private Map<String, Vec> veci;
    private boolean zamcena = false;
    private Vec klic;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashMap<>();
        postavy = new HashSet <Postava> ();
        this.zamcena = zamcena;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return  "--------------------------------------------------------------------------------------------------------------------\n"
                +"Nacházíš se v " + nazev + ".\n"
                + popis + "\n"
                + popisVychodu() + "\n"
                + popisVeci()
                + seznamPostav();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "KAM MŮŽEŠ JÍT:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }
    
    public String popisVeci() {
        String vracenyText = "PŘEDMĚTY:";
        for (String nazev : veci.keySet()) {
            vracenyText += " " + nazev;
        }
        
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným nickénem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného nickéna není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    /**
     * Vraci, zda je prostor zamcenej
     *
     * @return true - je zamčen
     */
    public boolean isZamcena() {
        return zamcena;
    }

    /**
     * Nastavuje zamek prostoru
     *
     * @param zamcena - true pokud ma byt zamcen
     */
    public void setZamcena(boolean stav) {
        this.zamcena = stav;        
    }

    /**
     * Metoda vraci klic k odemknuti prostoru
     *
     * @return klíč
     */
    public Vec getKlic() {
        return klic;
    }

    /**
     * Metoda nastavi klid k odemknuti prostoru
     */
    public void setKlic(Vec klic) {
        this.klic = klic;
    }
    
    public void vlozVec(Vec vec) {
        veci.put(vec.getNazev(), vec);
    }
       
    /**
     * Prida postavu do hry
     * @return osoba
     */
    public void setPostava(Postava o) {
        postavy.add (o);
    }
    
    /**
     * Vymaze osobu ze hry
     * @return osoba
     */
    public Postava vymazPostavu(String nick) {
        for ( Postava u : postavy ){
            if (u.getJmeno().equals(nick)) {
                Postava vybranaOsoba = u;
                postavy.remove(u);
                return vybranaOsoba;
            }}
        return null;
    }
    
    /**
     * Vypise seznam postav v mistnosti
     * @return seznam
     */
    public String seznamPostav() {
        String seznam = "\nPOSTAVY: ";
        if (postavy.size()==0){
            seznam = seznam + " Žádné postavy se zde nenacházejí.";}
        else{
            for (Postava u : postavy) {
                seznam += "\n - " + u.getJmeno();
            }}
        return seznam;
    }
    
    /**
     * Vybere postavu pro pouziti prikazu dej
     * @return postava
     */
    public Postava vyberPostava (String jm) {
        for ( Postava u : postavy ){
            if (u.getJmeno().equals(jm)) {
                return u;
            }}
        return null;
    }
    
    /**
     * Vyhodi predmet z mistnosti
     */
    public Vec odeberVec(String nazev) {
        return veci.remove(nazev);
    }
    
}
