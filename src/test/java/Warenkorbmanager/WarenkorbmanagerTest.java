
package Warenkorbmanager;



import Hilfsmodule.EANCheck;
import Hilfsmodule.Fehlermeldungen;
import Voratsbestand.*;
import Warenkorb.Warenkorbmanager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Die Klasse TestWarenkorbmanager dient zum Testen der Methoden in der Klasse Warenkorbmanager
 * @author Philip Hilspach, Andreas Göttsberger
 * @version 1.4 (27.07.2020)
 */

public class WarenkorbmanagerTest {
    /**
     * Hier werden alle Methoden der Klasse Warenkorbmanager getestet
     *
     */

    Kleidung marke1;
    Kleidung marke2;
    Kleidung marke3;
    Produktverwaltung produktverwaltung;
    Warenkorbmanager warenkorbmanager;

    @BeforeAll
    public static void beforeclass(){
        EANCheck.setPZCheckEnabled(false);  // Wird benötigt um gültige EAN Prüfziffern zu ignorieren. Vereinfacht das Arbeiten mit dem EAN.
        System.out.println("Test der Klasse TestWarenkorbmanager wird gestartet");
    }

    /**
     * Hier wird die Methode addProdukt aus dem Warenkorbmanager getestet
     */

    @BeforeEach
    public void setup(){

        System.out.println("Test der Methode addProdukt wird gestartet");

        produktverwaltung = Produktverwaltung.getProduktverwaltung();
        produktverwaltung.removeAllProducts(); //Loeschen aller Produkte
        warenkorbmanager = Warenkorbmanager.getWarenkorbmanager();

        marke1 = new Kleidung();
        marke1.setName("Polo");
        marke1.setEan("0000000000001");
        marke1.setFarbe("Weiß");
        marke1.setGroesse(Groessen.S.toString());
        marke1.setStoff("Stoff");
        marke1.setPackageSize(1.0, "Stk.");
        marke1.setPreis(80, Waerungen.EURO.toString());
        marke1.setMarke("Kokodilmarke");
        produktverwaltung.addProduct(marke1);
        marke1.setLagerbestand(2);

        marke2 = new Kleidung();
        marke2.setName("Polo");
        marke2.setEan("0000000000002");
        marke2.setFarbe("Weiß");
        marke2.setGroesse(Groessen.S.toString());
        marke2.setStoff("Stoff");
        marke2.setPackageSize(1.0, "Stk.");
        marke2.setPreis(80, Waerungen.EURO.toString());
        marke2.setMarke("Kokodilmarke");
        produktverwaltung.addProduct(marke2);
        marke2.setLagerbestand(2);

        marke3 = new Kleidung();
        marke3.setName("Polo");
        marke3.setEan("0000000000003");
        marke3.setFarbe("Weiß");
        marke3.setGroesse(Groessen.S.toString());
        marke3.setStoff("Stoff");
        marke3.setPackageSize(1.0, "Stk.");
        marke3.setPreis(80, Waerungen.EURO.toString());
        marke3.setMarke("Kokodilmarke");
        produktverwaltung.addProduct(marke3);
        marke3.setLagerbestand(2);



    }

    @Test
    public void testAddProdukt(){

        assertEquals(Fehlermeldungen.PROD_WURDE_HINZUGEFUEGT, warenkorbmanager.addProdukt("0000000000001",1));

        assertEquals(Fehlermeldungen.PROD_NICHT_VERF, warenkorbmanager.addProdukt("000000001",1));

        assertEquals(Fehlermeldungen.PROD_NICHT_VERF, warenkorbmanager.addProdukt("0000000000000001",1));

        assertEquals(Fehlermeldungen.PROD_WURDE_HINZUGEFUEGT, warenkorbmanager.addProdukt("0000000000001",1));

        assertEquals(Fehlermeldungen.PROD_WURDE_HINZUGEFUEGT, warenkorbmanager.addProdukt("0000000000002",1));

        assertEquals(Fehlermeldungen.PROD_MENGE_NICHT_VORHANDEN, warenkorbmanager.addProdukt("0000000000001",3));

    }

    @AfterEach
    public void teardown(){
        System.out.println("Test der Methode addProdukt wurde durchgeführt");
    }

    @BeforeEach
    public void initRemove(){
        System.out.println("Test der Methode remove Produkt wird gestartet");
    }


    /**
     *   Methode überprüft, ob die Anzahl übereinstimmt nachdem ein Element entfernt wurde.
     *
     */

    @Test
    public void removeProduktTest (){

       warenkorbmanager.addProdukt("0000000000001", 1);
       warenkorbmanager.addProdukt("0000000000002", 1);
       warenkorbmanager.addProdukt("0000000000003", 1);

       assertEquals(warenkorbmanager.removeProdukt("0000000000001"), Fehlermeldungen.PROD_WURDE_ENTFERNT);
       assertEquals(warenkorbmanager.removeProdukt("0000000000002"), Fehlermeldungen.PROD_WURDE_ENTFERNT);
       assertEquals(warenkorbmanager.removeProdukt("0000000000003"), Fehlermeldungen.PROD_WURDE_ENTFERNT);
       assertEquals(warenkorbmanager.removeProdukt("0000000000003"), Fehlermeldungen.PROD_NICHT_VERF);

    }


    @AfterEach
    public void cleanRemove(){
        System.out.println("Test der Methode remove Produkt wurde durchgeführt");
    }




    @AfterAll
    public static void afterclass(){
        System.out.println("Test der Klasse TestWarenkorbmanager wurde beendet");
    }


    @Test
    public void gesamtBetrag(){
        Warenkorbmanager w1 = new Warenkorbmanager();
        Produktverwaltung p1 = Produktverwaltung.getProduktverwaltung();
        p1.loadProductFromDB();
        w1.addProdukt("0000000000000",1);
        w1.addProdukt("0000000000001",1);
        assertEquals(95.0,w1.gesamtBetrag());

    }




    /**
     * Finalizen der produktverwaltug um sicherzugehen, dass nichts falsches noch in Datenbank steht
     */
    @AfterAll
    public static void destroyProduktverwaltung(){
        Produktverwaltung.getProduktverwaltung();
        try {
            Produktverwaltung.getProduktverwaltung().finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        EANCheck.setPZCheckEnabled(true);
    }


}
