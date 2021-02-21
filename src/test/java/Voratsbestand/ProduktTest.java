package Voratsbestand;

import Hilfsmodule.Fehlermeldungen;
import Hilfsmodule.PrintHelper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Hier wird die grundsätliche Funktionalität der Produktobjekte getestet
 * @author Franz Murner
 * @version 06.July.2020
 */
class ProduktTest {
    
    StandartProdukt standartProduktTest;

    /**
     * Legt ein Standartprodukt an
     */
    @BeforeEach
    void createProduct(){
        standartProduktTest = new StandartProdukt();
        standartProduktTest.setName("Testprodukt");
        standartProduktTest.setEan("0000000000000");
        standartProduktTest.setMinimalMenge(20);
        standartProduktTest.setLagerbestand(100);
        standartProduktTest.setPackageSize(1.0, "Stk.");
        standartProduktTest.setPreis(20, Waerungen.EURO.toString());

        PrintHelper.printlnCenteredTextString("Erzeugtes Objekt", "-", 91);
        System.out.println(standartProduktTest.toStringHead());
        System.out.println(standartProduktTest.toString());
        PrintHelper.printlnString("-", 91);
    }

    /**
     * Testet ob noch die Mindestmenge im Lager verfügbar ist.
     * Falls die Mindestmenge unterschritten wird, dann wird Prod_Knapp zurückgegeben
     * Falls das Produkt aus, wird Prod_Nicht_Verf_ ausgegeben
     * Falls der Lagerbestand über der Mindestmenge liegt, dann wird Produkt verfügbar ausgegeben
     */
    @Test
    void checkAvailabilityTest(){
        assertEquals(Fehlermeldungen.PROD_VORHANDEN, standartProduktTest.checkAvailability());
        standartProduktTest.setLagerbestand(19);
        assertEquals(Fehlermeldungen.PROD_KNAPP, standartProduktTest.checkAvailability());
        standartProduktTest.setLagerbestand(0);
        assertEquals(Fehlermeldungen.PROD_NICHT_VERF, standartProduktTest.checkAvailability());
    }

    /**
     * Diese Testfunktion überprüft ob eine angefragte Menge an Produkten verfügbar ist und gibt danach einen Boolean aus
     */
    @Test
    void checkProduktmengeVerfuegbar(){
        assertTrue(standartProduktTest.produktmengeVerfuegbar(20));
        assertFalse(standartProduktTest.produktmengeVerfuegbar(200));
    }

    @Test
    void checkVerkauf(){
        assertEquals(Fehlermeldungen.KEINFEHLER, standartProduktTest.verkauf(20));
        assertEquals(80, standartProduktTest.getLagerbestand());
        assertEquals(Fehlermeldungen.PROD_MENGE_NICHT_VORHANDEN, standartProduktTest.verkauf(200));
        assertEquals(80, standartProduktTest.getLagerbestand());
        assertEquals(Fehlermeldungen.KEINFEHLER, standartProduktTest.verkauf(80));
        assertEquals(0, standartProduktTest.getLagerbestand());
        assertEquals(Fehlermeldungen.PROD_MENGE_NICHT_VORHANDEN, standartProduktTest.verkauf(1));
    }

    @Test
    void checkZukauf(){
        assertEquals(100, standartProduktTest.getLagerbestand());
        standartProduktTest.zukauf(30);
        assertEquals(130, standartProduktTest.getLagerbestand());
        System.out.println(standartProduktTest.toString());
    }

    //-->Die Tests für die EANS befinden sich in dem Package Hilfsmodule

}