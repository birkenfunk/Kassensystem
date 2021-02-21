package Voratsbestand;

import Hilfsmodule.EANCheck;
import Hilfsmodule.PrintHelper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Hier wird abegeprüft, ob das Speichern und Laden, die richtigkeit der Daten und das Entfernen aus der Datenbank funktioniert
 * @author franzmurner
 * @version 16.July.2020
 */
class ProductDBManagerTest {

    Produktverwaltung produktverwaltung;
    StandartProdukt dummyProduct;
    Kleidung dummyKleidung;

    @BeforeAll
    static void disableEANPZCheck(){
        EANCheck.setPZCheckEnabled(false);
    }

    @BeforeEach
    void ladeProduktverwaltungDatenbank(){
        produktverwaltung = Produktverwaltung.getProduktverwaltung();
        produktverwaltung.loadProductFromDB();
    }

    @BeforeEach
    void createDummyProdukts(){
        dummyProduct = new StandartProdukt();
        dummyProduct.setName("DummyProduct");
        dummyProduct.setEan("1234567890123");
        dummyProduct.setPackageSize(40, "kg");
        dummyProduct.setPreis(100, Waerungen.EURO.toString());
        dummyProduct.setMinimalMenge(200);

        dummyKleidung = new Kleidung();
        dummyKleidung.setName("DummyKleidung");
        dummyKleidung.setEan("3210987654321");
        dummyKleidung.setPackageSize(2, "stk");
        dummyKleidung.setPreis(42, Waerungen.EURO.toString());
        dummyKleidung.setMinimalMenge(200);
        dummyKleidung.setStoff("Testwolle");
        dummyKleidung.setGroesse(Groessen.M.toString());
        dummyKleidung.setMarke("Markentest");
        dummyKleidung.setFarbe("Rot");
    }

    @Test
    void testeDatenbankSpeicherung() {


        PrintHelper.printlnString("*", 100);
        System.out.println("Erwartet: vorbesetzteTabelle");
        PrintHelper.printlnString("*", 100);

        produktverwaltung.printAllProducts();

        PrintHelper.printString("\n", 3);

        produktverwaltung.addProduct(dummyProduct);
        produktverwaltung.addProduct(dummyKleidung);

        produktverwaltung.saveProductsInDatabase();
        produktverwaltung.removeAllProducts();
        PrintHelper.printlnString("*", 100);
        System.out.println("Erwartet: leere Tabellen");
        PrintHelper.printlnString("*", 100);
        produktverwaltung.printAllProducts();
        PrintHelper.printString("\n", 3);


        assertTrue(produktverwaltung.getKleidungsliste().isEmpty());
        assertTrue(produktverwaltung.getKleidungsliste().isEmpty());

        produktverwaltung.loadProductFromDB();

        PrintHelper.printlnString("*", 100);
        System.out.println("Erwartet: gefüllte Tabellen");
        PrintHelper.printlnString("*", 100);
        produktverwaltung.printAllProducts();
        PrintHelper.printString("\n", 3);


        assertEquals(dummyProduct, produktverwaltung.getProduct(dummyProduct.getEan()));
    }

    @Test
    void testeDatenAufRichtigkeit(){
        produktverwaltung.addProduct(dummyProduct);
        produktverwaltung.addProduct(dummyKleidung);
        produktverwaltung.saveProductsInDatabase();

        //Daten aus der Datenbank
        Produkt produkt = produktverwaltung.getProduct(dummyKleidung.getEan());

        if (produkt != null && produkt.getClass() == Kleidung.class){
            Kleidung datenbankKleidung = (Kleidung) produkt;
            assertEquals(dummyKleidung.getName(), datenbankKleidung.getName());
            assertEquals(dummyKleidung.getEan(), datenbankKleidung.getEan());
            assertEquals(dummyKleidung.getEinheit(), datenbankKleidung.getEinheit());
            assertEquals(dummyKleidung.getWaerung(), datenbankKleidung.getWaerung());
            assertEquals(dummyKleidung.getLagerbestand(), datenbankKleidung.getLagerbestand());
            assertEquals(dummyKleidung.getMengeProEinheit(), datenbankKleidung.getMengeProEinheit());
            assertEquals(dummyKleidung.getMinimalMenge(), datenbankKleidung.getMinimalMenge());
            assertEquals(dummyKleidung.getPreis(), datenbankKleidung.getPreis());
            assertEquals(dummyKleidung.getFarbe(), datenbankKleidung.getFarbe());
            assertEquals(dummyKleidung.getStoff(), datenbankKleidung.getStoff());
            assertEquals(dummyKleidung.getGroesse(), datenbankKleidung.getGroesse());
            assertEquals(dummyKleidung.getMarke(), datenbankKleidung.getMarke());
        }

        //Daten aus der Datenbank
        produkt = produktverwaltung.getProduct(dummyProduct.getEan());

        if (produkt != null && produkt.getClass() == StandartProdukt.class){
            StandartProdukt datenbankStandartProdukt = (StandartProdukt) produkt;

            assertEquals(dummyProduct.getName(), datenbankStandartProdukt.getName());
            assertEquals(dummyProduct.getEan(), datenbankStandartProdukt.getEan());
            assertEquals(dummyProduct.getEinheit(), datenbankStandartProdukt.getEinheit());
            assertEquals(dummyProduct.getWaerung(), datenbankStandartProdukt.getWaerung());
            assertEquals(dummyProduct.getLagerbestand(), datenbankStandartProdukt.getLagerbestand());
            assertEquals(dummyProduct.getMengeProEinheit(), datenbankStandartProdukt.getMengeProEinheit());
            assertEquals(dummyProduct.getMinimalMenge(), datenbankStandartProdukt.getMinimalMenge());
            assertEquals(dummyProduct.getPreis(), datenbankStandartProdukt.getPreis());
        }
    }

    //Achtung Methode hat Probleme und muss evtl. per Hand neu geschrieben werden
    @Test
    void testeObDatenbankeintraegeGeloescht(){
        produktverwaltung.addProduct(dummyProduct);
        produktverwaltung.addProduct(dummyKleidung);
        produktverwaltung.saveProductsInDatabase(); //Speichert die aktuelle Sitzung ab
        produktverwaltung.removeAllProducts(); //Löscht Sitzung (Array List)
        produktverwaltung.loadProductFromDB(); //Lädt die Produkte aus der Datenbank in die Sitzung (Arraylist und sortiert sie)

        assertNotNull(produktverwaltung.getProduct(dummyKleidung.getEan()));
        assertNotNull(produktverwaltung.getProduct(dummyProduct.getEan()));

        produktverwaltung.removeProduct(dummyKleidung.getEan());
        produktverwaltung.removeProduct(dummyProduct.getEan());
        produktverwaltung.saveProductsInDatabase(); //Speichert die aktuelle Sitzung ab
        produktverwaltung.removeAllProducts(); //Löscht Sitzung (Array List)
        produktverwaltung.loadProductFromDB(); //Lädt die Produkte aus der Datenbank in die Sitzung (Arraylist und sortiert sie)

        assertNull(produktverwaltung.getProduct(dummyKleidung.getEan()));
        assertNull(produktverwaltung.getProduct(dummyProduct.getEan()));
    }

    @AfterEach
    void removeDummys(){
        produktverwaltung.removeProduct(dummyProduct.getEan());
        produktverwaltung.removeProduct(dummyKleidung.getEan());
        produktverwaltung.saveProductsInDatabase();
    }

}

