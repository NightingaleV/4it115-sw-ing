package logika;


/**
 * Trida utvarejici prikaz dej
 * 
 * @author Vitezslav Slavik 
 * @version prosinec 2016
 */
public class PrikazDej implements IPrikaz
{
    // instance variables - replace the example below with your own
    private static final String NAZEV = "dej";
    private HerniPlan plan;
    /**
     * Constructor for objects of class PrikazDej
     */
    public PrikazDej(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda provadi "vymenu" predmetu. Predmet dame postave, ta nam da jinny predmet.
     * 
     * @param  jmeno osoby a nazev predmetu
     */
    public String proved(String... parametry){
        if(parametry.length == 0 | parametry.length == 1 | parametry.length >= 3){
        return "Musíš mít presne dva parametry\"osobu\" a \"věc\".";
        }
        Prostor aktualniProstor = plan.getAktualniProstor();
        String komuDavam = parametry[0];
        String coDavame = parametry[1];
        Postava postava = aktualniProstor.vyberPostava(komuDavam);
        if(postava == null){
        return "Tato postava zde není.";
        }
       Vec vec = plan.getBatoh().getVec(coDavame);
        if(vec == null){
        return "Tato věc v batohu neni";
        }
        if(vec.equals(postava.getCoChce())){
            Vec coDostaneme = postava.getCoMa();
            plan.getBatoh().pridejVec(coDostaneme);
            plan.getBatoh().odeberVec(coDavame);
            postava.vymeneno = true;
            return postava.getChci()+"\nDal jsi "+vec.getNazev()+ " osobě " + postava.getJmeno()+
            " a na oplátku jsi dostal "+coDostaneme.getNazev()
            + System.lineSeparator() + postava.getProslovPo() +".";
        }
        return postava.getNechci();
    }
    
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
