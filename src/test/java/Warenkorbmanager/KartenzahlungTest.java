package Warenkorbmanager;

import Warenkorb.Kartenzahlung;
import Warenkorb.IbanCheck;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class KartenzahlungTest {

    /**
     * Die Klasse KartenzahlungTest dient zum Testen der Methoden in den Klassen Kartenzahlung und IbanCheck
     * @author Andreas Göttsberger
     * @version 1.1 (27.07.2020)
     */

    @BeforeAll
    public static void beforeclass(){
        System.out.println("Test der Klasse KartenzahlungTest wird gestartet");
    }

    @BeforeEach
    public void setup(){

        System.out.println("Test der Methode checkeLängeIban wird gestartet");

    }

    @Test
    public void testCheckeLaengeIban(){

        IbanCheck test = new IbanCheck("DE49123456789012345678");

        assertTrue(test.checkeLaengeIban("DE49123456789012345678")); //richtige Länge
        assertFalse(test.checkeLaengeIban("DE4912345678901234567")); //falsche Länge
        assertFalse(test.checkeLaengeIban("DE491234567890123456711111111")); //falsche Länge
        assertFalse(test.checkeLaengeIban("DE49123456")); //falsche Länge

    }

    @AfterEach
    public void teardown(){
        System.out.println("Test der Methode testCheckeLängeIban wurde durchgeführt");
    }

    @BeforeEach
    public void setup2(){
        System.out.println("Test der Methode PrüfeIban wird gestartet");
    }

    @Test
    public void testPruefeIban(){

        IbanCheck test = new IbanCheck("DE49123456789012345678");

        assertTrue(test.pruefeIban("DE49711500000000215632"));   //richtige IBAN
        assertFalse(test.pruefeIban("DE49888800000000215632"));  //falsche IBAN

    }

    @AfterEach
    public void teardown2(){
        System.out.println("Test der Methode PrüfeIban wurde durchgeführt");
    }

    @BeforeEach
    public void setup3(){
        System.out.println("Test der Methode validiereKontostand wird gestartet");
    }

    @Test
    public void testValidiereKontobetrag(){

        Kartenzahlung test1 = new Kartenzahlung("DE49711500000000215632",30.50);

        assertTrue(test1.validiereKontobetrag(200.00)); //Einkaufswert kann bezahlt werden
        assertFalse(test1.validiereKontobetrag(500.01)); //Einkaufswert kann nicht bezahlt werden

    }

    @AfterEach
    public void teardown3(){
        System.out.println("Test der Methode validiereKontostand wurde durchgeführt");
    }

    @BeforeEach
    public void setup4(){
        System.out.println("Test der Methode kartenzahlungTätigen wird gestartet");
    }

    @Test
    public void testKartenzahlungTaetigen(){

        Kartenzahlung test2 = new Kartenzahlung("DE49711500000000215632",30.50);

        assertTrue(test2.kartenzahlungTaetigen("DE49711500000000215632",300));   //passt alles
        assertFalse(test2.kartenzahlungTaetigen("DE49711500000000215632",300));  //Zu wenig Geld auf Konto bzw. Einkaufswert zu hoch
        assertFalse(test2.kartenzahlungTaetigen("DE4971150000000021563",0));   //Iban falsch

    }

    @AfterEach
    public void teardown4(){
        System.out.println("Test der Methode kartenzahlungTätigen wurde durchgeführt");
    }

    @AfterAll
    public static void afterclass(){
        System.out.println("Test der Klasse KartenzahlungTest wurde beendet");
    }

}
