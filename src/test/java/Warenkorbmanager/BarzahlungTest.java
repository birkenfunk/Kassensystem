package Warenkorbmanager;

import Warenkorb.Barzahlung;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BarzahlungTest {

    /**
     * Die Klasse BarzahlungTest dient zum Testen der Methode barzahlungTaetigen in der Klasse Barzahlung
     * @author Andreas Göttsberger
     * @version 1.2 (27.07.2020)
     */

    @BeforeEach
    public void setup5(){

        System.out.println("Test der Methode barzahlungTätigen wird gestartet");

    }

    @Test
    public void testbarzahlungTaetigen(){

        Barzahlung test1 = new Barzahlung();

        assertTrue(test1.barzahlungTaetigen(50,50));

        assertTrue(test1.barzahlungTaetigen(50,70.0));

        assertFalse(test1.barzahlungTaetigen(50.0,49));

        assertFalse(test1.barzahlungTaetigen(50,30.00));

        assertFalse(test1.barzahlungTaetigen(50,0));


    }

    @AfterEach
    public void teardown5(){
        System.out.println("Test der Methode barzahlungTätigen wurde durchgeführt");
    }
}
