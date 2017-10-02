package logika;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BatohTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BatohTest
{
  
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
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }
    
    @Test
    public void testFunkcnost()
    {
        Batoh batoh = new Batoh();
        Vec test1 = new Vec ("test1","test1",true);
        
        assertEquals(true, batoh.pridejVec(test1));
        assertEquals(true, batoh.obsahujeVec("test1"));
    }
    
    @Test
    public void testOdlozeni()
    {
        Batoh batoh = new Batoh();
        Vec test1 = new Vec ("test1","test1",true);
        
        assertEquals(true, batoh.pridejVec(test1));
        assertEquals(true, batoh.odeberVec("test1"));
        assertEquals(false, batoh.obsahujeVec("test1"));
    }
    
    @Test
    public void testVelikost()
    {
       Batoh batoh = new Batoh();
        
       Vec test1 = new Vec ("test1","test",true);
       Vec test2 = new Vec ("test2","test",true);
       Vec test3 = new Vec ("test3","test",true);
       Vec test4 = new Vec ("test4","test",true);
       Vec test5 = new Vec ("test5","test",true);
       Vec test6 = new Vec ("test6","test",true);
       Vec test7 = new Vec ("test7","test",true);
       Vec test8 = new Vec ("test8","test",true);
       Vec test9 = new Vec ("test9","test",true);
       Vec test10 = new Vec ("test10","test",true);
       Vec test11 = new Vec ("test11","test",true);
       Vec test12 = new Vec ("test12","test",true);
       assertEquals(true, batoh.pridejVec (test1));
       assertEquals(true, batoh.pridejVec (test2));
       assertEquals(true, batoh.pridejVec (test3));
       assertEquals(true, batoh.pridejVec (test4));
       assertEquals(true, batoh.pridejVec (test5));
       assertEquals(true, batoh.pridejVec (test6));
       assertEquals(true, batoh.pridejVec (test7));
       assertEquals(true, batoh.pridejVec (test8));
       assertEquals(true, batoh.pridejVec (test9));
       assertEquals(true, batoh.pridejVec (test10));
       assertEquals(true, batoh.pridejVec (test11));
       assertEquals(false, batoh.pridejVec (test12));
       
    
    }
}
