package logika;
import java.util.*;
import utils.Subject;
import utils.Observer;


/**
 * Trida batoh nam prida do hry funkcni inventar.
 * 
 * @author Vitezslav Slavik
 * @version prosinec 2016
 */
public class Batoh implements Subject
{
    // atributy inventare a jeho omezeni
    public final Map<String, Vec> seznamVeci;
    private static final int velikost_inventare = 10;
    public HerniPlan plan;
    private final List<Observer> listObserveru = new ArrayList<>();

    /**
     * Konstruktor tridy
     */
    public Batoh(){
        seznamVeci = new HashMap<>();
    }
    
    /**
     * Metoda pro zjisteni obsahu inventare
     */
    public String obsahBatohu()
    {
        String text = "V tvém inventáři se nachází tyto předměty:";
        for(String nazevVeci : seznamVeci.keySet()){
            text += nazevVeci + " ";
        }
        return text;
    }
    
    /**
     * Metoda prida veci do inventare (pokud neni plny)
     * 
     * @param  predmet Parametr je vec, ktera se ma pridat
     * @return Vrati true pokud se predmet pridal
     */
    public boolean pridejVec (Vec predmet)
    {
        if(jeMisto()){
            seznamVeci.put(predmet.getNazev(),predmet);
            notifyAllObservers();
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Metoda odstrani vec z inventare pokud tam je
     * @param predmet - vec ktera se ma z batohu odstranit
     * @return true pokud se vec odstrani
     */
    public boolean odeberVec(String predmet)
    {
        if (seznamVeci.containsKey(predmet)){
            seznamVeci.remove(predmet);
            notifyAllObservers();
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Zjisti, zda je predmet v batohu
     *
     * @param nazev - nazev hledané věci
     * @return true pokud je v batohu
     */
    public boolean obsahujeVec (String nazev) {
        return seznamVeci.containsKey(nazev);
    }

    /**
    *  Metodou ziskame predmet
    */   
    public Vec getVec(String nazevVeci) {
        return seznamVeci.get(nazevVeci);
    }
    
    /**
     * Metoda zajistuje omezenou kapacitu inventare
     */
    public boolean jeMisto(){
        return (seznamVeci.size() <= velikost_inventare);
    }

    public Collection<String> getObsahBatohu() {

        return Collections.unmodifiableCollection(seznamVeci.keySet());

    }

    /**
     * Přidáni listu k observeru
     *
     * @param observer observer
     */
    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    /**
     * Odstranění liztu z observeru
     *
     * @param observer observer
     */
    @Override
    public void deleteObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    /**
     * Vyvolání aktualizace observeru
     */
    @Override
    public void notifyAllObservers() {
        for (Observer observer : listObserveru) {
            observer.update();
        }
    }
}
