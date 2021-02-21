package Voratsbestand;

import Hilfsmodule.EANCheck;
import Hilfsmodule.Fehlermeldungen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test der Produktverwaltung
 * @author Franz+ Murner
 * @version 1.0 22-5-2020
 */
class ProduktverwaltungTest {

    Produktverwaltung produktverwaltung;
    StandartProdukt testProdukt;
    StandartProdukt testProduktII;


    @BeforeEach
    void setup(){
        EANCheck.setPZCheckEnabled(false); //Ausschalten des PZ Checks
        produktverwaltung = Produktverwaltung.getProduktverwaltung();
        testProdukt = new StandartProdukt();
        testProdukt.setName("10_TestProduct");
        testProdukt.setEan("0000000000000");

        testProduktII = new StandartProdukt();
        testProduktII.setName("1_TestProduct");
        testProduktII.setEan("0000000000001");

    }

    /**
     * Löschen der Statischen Produktverwaltungsreferenz von der Klasse Produktverwaltung
     */
    @AfterEach
    void destroy(){
        try {
            produktverwaltung.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    @Test
    void getProduktverwaltung() {
        Produktverwaltung produktverwaltung = Produktverwaltung.getProduktverwaltung();
        assertNotNull(produktverwaltung, "Es wurde null beim Initialisieren der Produktverwaltung zurückgegeben");
    }

    @Test
    void addProduct() {
        produktverwaltung.printAllProducts();
        assertEquals(Fehlermeldungen.ERFOLGREICH, produktverwaltung.addProduct(testProdukt));
        //Erkennung ob Produkt bereits in Liste vorhanden
        assertEquals(Fehlermeldungen.PROD_BEREITS_VORHANDEN, produktverwaltung.addProduct(testProdukt));
    }

    @Test
    void getProduct() {
        produktverwaltung.addProduct(testProdukt);
        assertNotNull(produktverwaltung.getProduct(testProdukt.getEan())); //Vorhandens Produkt
        assertNull(produktverwaltung.getProduct("1234567890127")); //Wenn Produkt nicht vorhanden
    }

    @Test
    void printAllProducts() {
        produktverwaltung.addProduct(testProdukt);
        produktverwaltung.printAllProducts();
    }


}