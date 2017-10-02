package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra.
 *
 * @author   Jarmila Pavlíčková
 * @version  ZS 2016/2017
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("Taverna", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("vezmi medovník");
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujeVec("medovník"));
        hra1.zpracujPrikaz("vezmi obolos");
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujeVec("obolos"));
        hra1.zpracujPrikaz("vezmi stater");
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujeVec("stater"));
        hra1.zpracujPrikaz("dej Hostinský stater");
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujeVec("pivo"));
        hra1.zpracujPrikaz("dej Štamgast pivo");
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujeVec("meč"));
        hra1.zpracujPrikaz("jdi Zbrojnice");
        assertEquals("Zbrojnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("vezmi bronzová_zbroj");
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujeVec("bronzová_zbroj"));
        hra1.zpracujPrikaz("jdi Lernovské_bažiny");
        assertEquals("Lernovské_bažiny", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("mluv Kentaur");
        hra1.zpracujPrikaz("jdi Hora_Ida");
        assertEquals("Hora_Ida", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("bojuj Harpyje");
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujeVec("štít"));
        hra1.zpracujPrikaz("jdi Rokle_Kragu");
        hra1.zpracujPrikaz("bojuj Chiméra");
        hra1.zpracujPrikaz("jdi Podsvětí");
        assertEquals("Podsvětí", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej Kerberos medovník");
        hra1.zpracujPrikaz("jdi řeka_Styx");
        assertEquals("řeka_Styx", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej Cháron obolos");
        hra1.zpracujPrikaz("jdi Chodba_Neklidu");
        assertEquals("Chodba_Neklidu", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi Sál_Zatracených");
        assertEquals("Sál_Zatracených", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("vezmi prsten_moci");
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujeVec("prsten_moci"));
        hra1.zpracujPrikaz("jdi Lernovské_bažiny");
        hra1.zpracujPrikaz("jdi Chrám_mrtvých");
        hra1.zpracujPrikaz("bojuj Hydra");
        hra1.zpracujPrikaz("jdi Lernovské_bažiny");
        hra1.zpracujPrikaz("jdi Hora_Ida");
        hra1.zpracujPrikaz("jdi Rokle_Kragu");
        hra1.zpracujPrikaz("jdi Podsvětí");
        hra1.zpracujPrikaz("jdi řeka_Styx");
        hra1.zpracujPrikaz("jdi Chodba_Neklidu");
        hra1.zpracujPrikaz("jdi Sál_Zatracených");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("bojuj Hádés");
        assertEquals(true, hra1.konecHry());
        
    }
}
