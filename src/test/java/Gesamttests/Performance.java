package Gesamttests;

import Funktionen.Subfunktion;
import Voratsbestand.Groessen;
import Voratsbestand.Kleidung;
import Voratsbestand.Produktverwaltung;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;

/**
 * Test class for stress test.
 * @author Vitus Henkel
 * @version 27.07.2020
 */
@DisplayName("Performance Test")
public class Performance {

    Produktverwaltung productManager = Produktverwaltung.getProduktverwaltung();     //def ProductManager

    @Test
    @DisplayName("100 Produkte")
    public void scenario_addProduct_10() {
        productManager.loadProductFromDB();

        assertTimeout(ofMillis(10),()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("Produkt " + (i+1) + " wird dem Bestand hinzugefuegt ...");
                System.out.println("Erwartet: Bestand wurde hinzugefuegt.");

                Kleidung tracksuit = new Kleidung();
                tracksuit.setName("Kampfanzug" + i);
                tracksuit.setEan(""+i);
                tracksuit.setFarbe("weiß");
                tracksuit.setGroesse(Groessen.M.toString());
                tracksuit.setStoff("Baumwolle");
                tracksuit.setPackageSize(1.0, "Stk.");
                tracksuit.setPreis(35, "€");
                tracksuit.setMarke("KWON");
                tracksuit.setLagerbestand(10);
                productManager.addProduct(tracksuit);

                System.out.println("Ergebnis: Bestand wurde hinzugefuegt.");

                Subfunktion.printCharln('-',50);
            }
        });
    }

    @Test
    @DisplayName("1000 Produkte")
    public void scenario_addProduct_100() {
        productManager.loadProductFromDB();

        assertTimeout(ofMillis(100),()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("Produkt " + (i+1) + " wird dem Bestand hinzugefuegt ...");
                System.out.println("Erwartet: Bestand wurde hinzugefuegt.");

                Kleidung tracksuit = new Kleidung();
                tracksuit.setName("Kampfanzug" + i);
                tracksuit.setEan(""+i);
                tracksuit.setFarbe("weiß");
                tracksuit.setGroesse(Groessen.M.toString());
                tracksuit.setStoff("Baumwolle");
                tracksuit.setPackageSize(1.0, "Stk.");
                tracksuit.setPreis(35, "€");
                tracksuit.setMarke("KWON");
                tracksuit.setLagerbestand(10);
                productManager.addProduct(tracksuit);

                System.out.println("Ergebnis: Bestand wurde hinzugefuegt.");

                Subfunktion.printCharln('-',50);
            }
        });
    }

    @Test
    @DisplayName("10000 Produkte")
    public void scenario_addProduct_1000() {
        productManager.loadProductFromDB();

        assertTimeout(ofMillis(1000),()->{
            for (int i = 0; i < 1000; i++) {
                System.out.println("Produkt " + (i+1) + " wird dem Bestand hinzugefuegt ...");
                System.out.println("Erwartet: Bestand wurde hinzugefuegt.");

                Kleidung tracksuit = new Kleidung();
                tracksuit.setName("Kampfanzug" + i);
                tracksuit.setEan(""+i);
                tracksuit.setFarbe("weiß");
                tracksuit.setGroesse(Groessen.M.toString());
                tracksuit.setStoff("Baumwolle");
                tracksuit.setPackageSize(1.0, "Stk.");
                tracksuit.setPreis(35, "€");
                tracksuit.setMarke("KWON");
                tracksuit.setLagerbestand(10);
                productManager.addProduct(tracksuit);

                System.out.println("Ergebnis: Bestand wurde hinzugefuegt.");

                Subfunktion.printCharln('-',50);
            }
        });
    }
}