package Funktionen;

import Hilfsmodule.EANCheck;
import User.Rechte;
import User.UserManager;
import Voratsbestand.Groessen;
import Voratsbestand.Kleidung;
import Voratsbestand.Produkt;
import Voratsbestand.Produktverwaltung;
import Warenkorb.Warenkorbmanager;
import Funktionen.Subfunktion;

/**
 * Hier wird das Testscenario in zweiter Linie konfiguriert
 * @author Vitus Henkel
 * @version 27.07.2020
 */
public class Szenarien{

    /**
     * Login des Root Users
     * Username: root
     * Password: 123
     * @param userManager <- im startup2 def
     */
     static public void scenario_logIn_root(UserManager userManager){

         Subfunktion.printCharln('-',50);

         System.out.println("User Login ...");
         System.out.println("Erwartet: Login erfolgreich.");

        if (userManager.Login("root", "123")== Rechte.SUPER_ADMIN)      //login over root
            System.out.println("Ergebnis: Login erfolgreich.");                          //login success
        else
            System.out.println("Ergebnis: Login verweigert.");                           //login denied

         Subfunktion.printCharln('-',50);
    }

    /**
     * Die Gesamtanzahl der vorhandenen User wird ausgegeben.
     * @param userManager <- im startup2 def
     */
    static public void scenario_search_total_Amount_User_1(UserManager userManager){
        System.out.println("Anzahl der User wird ermittelt ...");
        System.out.println("Erwartet: 4");
        System.out.print("Ergebnis: ");
        System.out.println(userManager.AnzahlListenElemente());

        Subfunktion.printCharln('-',50);
    }

    /**
     * Creating User
     * Username: Salesman1
     * Password: 123456
     * Rights: ?
     * @param userManager <- im startup2 def
     */
    static public void scenario_addUser(UserManager userManager){

        System.out.println("User wird hinzugefügt ...");
        System.out.println("Erwartet: User wurde angelegt.");

        userManager.UserEinfuegen("Salesman1","123456");

        System.out.println("Ergebnis: User wurde angelegt.");

        Subfunktion.printCharln('-',50);
    }

    /**
     * Die Gesamtanzahl der vorhandenen User wird ausgegeben.
     * @param userManager <- im startup2 def
     */
    static public void scenario_search_total_Amount_User_2(UserManager userManager){
        System.out.println("Anzahl der User wird ermittelt ...");
        System.out.println("Erwartet: 5");
        System.out.print("Ergebnis: ");
        System.out.println(userManager.AnzahlListenElemente());

        Subfunktion.printCharln('-',50);
    }

    /**
     * Remove User
     * Username: Salesman2
     * @param userManager <- im startup2 def
     */
    static public void scenario_removeUser(UserManager userManager){
        System.out.println("User wird gelöscht ...");
        System.out.println("Erwartet: User wurde gelöscht.");

        userManager.UserLoeschen("Salesman1");
        System.out.println("Ergebnis: User wurde gelöscht.");

        Subfunktion.printCharln('-',50);
    }

    /**
     * Die Gesamtanzahl der vorhandenen User wird ausgegeben.
     * @param userManager <- im startup2 def
     */
    static public void scenario_search_total_Amount_User_3(UserManager userManager){
        System.out.println("Anzahl der User wird ermittelt ...");
        System.out.println("Erwartet: 4");
        System.out.print("Ergebnis: ");
        System.out.println(userManager.AnzahlListenElemente());

        Subfunktion.printCharln('-',50);
    }

    /**
     * Creating a new product 1.
     * Name: Kampfanzug
     * EAN: 1010000000000
     * @param productManager <- im startup2 def
     */
    static public void scenario_addProduct_1(Produktverwaltung productManager){

        System.out.println("Produkt 1 wird dem Bestand hinzugefügt ...");
        System.out.println("Erwartet: Bestand wurde hinzugefügt.");

        Kleidung tracksuit = new Kleidung();
        tracksuit.setName("Kampfanzug");
        tracksuit.setEan("1010000000000");
        tracksuit.setFarbe("weiß");
        tracksuit.setGroesse(Groessen.M.toString());
        tracksuit.setStoff("Baumwolle");
        tracksuit.setPackageSize(1.0, "Stk.");
        tracksuit.setPreis(35, "€");
        tracksuit.setMarke("KWON");
        tracksuit.setLagerbestand(10);
        productManager.addProduct(tracksuit);

        System.out.println("Ergebnis: Bestand wurde hinzugefügt.");

        Subfunktion.printCharln('-',50);
    }

    /**
     * Searches for an existing product 1.
     * EAN: 1010000000000
     * @param productManager <- im startup2 def
     */
    static public void scenario_searchProduct_1(Produktverwaltung productManager){

        System.out.println("Produkt 1 wird im Bestand gesucht ...");
        System.out.println("Erwartet: Produkt wurde gefunden.");

        Produkt produkt = productManager.getProduct("1010000000000");

        if(produkt == null) {
            System.out.println("Produnkt mit dieser EAN nicht vorhanden oder EAN Falsch");
            System.out.println("Ergebnis: Produkt wurde nicht gefunden.");
        }
        else
            System.out.println("Ergebnis: Produkt wurde gefunden.");

        Subfunktion.printCharln('-',50);
    }

    /**
     * Creating a new product 2.
     * Name: Schuhe
     * EAN: 1010000000001
     * @param productManager <- im startup2 def
     */
    static public void scenario_addProduct_2(Produktverwaltung productManager){

        System.out.println("Produkt 2 wird dem Bestand hinzugefügt ...");
        System.out.println("Erwartet: Bestand wurde hinzugefügt.");

        Kleidung shoe = new Kleidung();
        shoe.setName("Schuhe");
        shoe.setEan("1010000000001");
        shoe.setFarbe("Weiß");
        shoe.setGroesse(Groessen.S.toString());
        shoe.setStoff("Stoff");
        shoe.setPackageSize(1.0, "Stk.");
        shoe.setPreis(23, "€");
        shoe.setMarke("KWON");
        shoe.setLagerbestand(10);
        productManager.addProduct(shoe);

        System.out.println("Ergebnis: Bestand wurde hinzugefügt.");

        Subfunktion.printCharln('-',50);
    }

    /**
     * Searches for an existing product 2.
     * EAN: 1010000000001
     * @param productManager <- im startup2 def
     */
    static public void scenario_searchProduct_2(Produktverwaltung productManager){

        System.out.println("Produkt 2 wird im Bestand gesucht ...");
        System.out.println("Erwartet: Produkt wurde gefunden.");

        Produkt produkt = productManager.getProduct("1010000000001");

        if(produkt == null) {
            System.out.println("Produnkt mit dieser EAN nicht vorhanden oder EAN Falsch");
            System.out.println("Ergebnis: Produkt wurde nicht gefunden.");
        }
        else
            System.out.println("Ergebnis: Produkt wurde gefunden.");

        Subfunktion.printCharln('-',50);
    }

    /**
     * Removing product 2.
     * EAN: 1010000000001
     * @param productManager <- im startup2 def
     */
    static public void scenario_removeProduct_2(Produktverwaltung productManager){
        System.out.println("Produkt 2 wird aus dem Bestand genommen ...");
        System.out.println("Erwartet: Produkt wurde entfernt.");

        productManager.removeProduct("1010000000001");

        Produkt produkt = productManager.getProduct("1010000000001");

        if(produkt == null) {
            System.out.println("Produnkt mit dieser EAN nicht vorhanden oder EAN Falsch");
            System.out.println("Ergebnis: Produkt wurde entfernt.");
        }
        else
            System.out.println("Ergebnis: Produkt wurde gefunden.");

        Subfunktion.printCharln('-',50);
    }

    /**
     * Search for a nonexistent product.
     * EAN: 1000000000002
     * @param productManager <- im startup2 def
     */
    static public void scenario_searchProduct_negative(Produktverwaltung productManager){

        System.out.println("Nicht vorhandenes Produkt wird im Bestand gesucht ...");
        System.out.println("Erwartet: Produkt wurde nicht gefunden.");

        Produkt produkt = productManager.getProduct("1010000000002");

        if(produkt == null) {
            System.out.println("Produnkt mit dieser EAN nicht vorhanden oder EAN Falsch");
            System.out.println("Ergebnis: Produkt wurde nicht gefunden.");
        }
        else
            System.out.println("Ergebnis: Produkt wurde gefunden.");

        Subfunktion.printCharln('-',50);
    }

    /**
     * All products are displayed in a list.
     * @param productManager <- im startup2 def
     */
    static public void scenario_printAllProduct_1(Produktverwaltung productManager){
        System.out.println("Eine Liste aller Produkte des Bestandes wird ausgegeben...");
        System.out.println("Erwartet: Produkte: Shirt, Polo, Kampfanzug.");
        productManager.printAllProducts();

        Subfunktion.printCharln('-',50);
    }

    /**
     * A product 1 is added to the shopping cart.
     * EAN: 0000000000000
     * Amount: 3
     * @param basketManager <- im startup2 def
     */
    static public void scenario_Basket_addProduct_1(Warenkorbmanager basketManager){

        System.out.println("Produkt 1 wird dem Warenkorb hinzugefügt ...");
        System.out.println("Erwartet: PROD_WURDE_HINZUGEFUEGT");

        System.out.print("Ergebnis: ");

        System.out.println(basketManager.addProdukt("1010000000000", 3).toString());

        Subfunktion.printCharln('-',50);
    }

    /**
     * A nonexistent Produkt is added to the shopping cart.
     * EAN: 1000000000001
     * Amount: 3
     * @param basketManager <- im startup2 def
     */
    static public void scenario_Basket_addProduct_2(Warenkorbmanager basketManager){

        System.out.println("Produkt 2 wird dem Warenkorb hinzugefügt ...");
        System.out.println("Erwartet: PROD_WURDE_HINZUGEFUEGT");

        System.out.print("Ergebnis: ");

        System.out.println(basketManager.addProdukt("0000000000000", 3).toString());

        Subfunktion.printCharln('-',50);
    }

    /**
     * Product 1 is searched in the shopping cart
     * EAN: 0000000000000
     * @param basketManager <- im startup2 def
     */
    static public void scenario_Basket_searchProduct_1(Warenkorbmanager basketManager){
        System.out.println("Produkt 1 wird im Warenkorb gesucht ...");
        System.out.println("Erwartet: In Warenkorb enthalten!");

        System.out.print("Ergebnis: ");
        basketManager.contains("1010000000000");

        Subfunktion.printCharln('-',50);
    }

    /**
     * Product 2 is searched in the shopping cart
     * EAN: 1000000000000
     * @param basketManager <- im startup2 def
     */
    static public void scenario_Basket_searchProduct_2(Warenkorbmanager basketManager){
        System.out.println("Produkt 2 wird im Warenkorb gesucht ...");
        System.out.println("Erwartet: In Warenkorb enthalten!");

        System.out.print("Ergebnis: ");
        basketManager.contains("0000000000000");

        Subfunktion.printCharln('-',50);
    }

    /**
     *  The total amount of the shopping cart is issued.
     * @param basketManager <- im startup2 def
     */
    static public void scenario_Basket_totalAmount_1(Warenkorbmanager basketManager){
        System.out.println("Der Gesamtbetrag des Warenkorbes wird ausgegeben ...");
        System.out.println("Erwartet: Gesamtbetrag = 150.0");

        System.out.print("Ergebnis: ");

        try{
            basketManager.gesamtBetrag();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Subfunktion.printCharln('-',50);
    }

    /**
     * Product 2 is removed from the shopping cart.
     * @param basketManager <- im startup2 def
     */
    static public void scenario_Basket_removeProduct_1(Warenkorbmanager basketManager){
        System.out.println("Produkt 1 wird aus dem Warenkorb entfernt ...");
        System.out.println("Erwartet: PROD_WURDE_ENTFERNT");

        System.out.print("Ergebnis: ");
        System.out.println(basketManager.removeProdukt("1010000000000"));   //Entfernt alle Produkte mit der EAN: * aus dem Warenkorb

        Subfunktion.printCharln('-',50);
    }

    /**
     * The total amount of the shopping cart is issued.
     * @param basketManager <- im startup2 def
     */
    static public void scenario_Basket_totalAmount_2(Warenkorbmanager basketManager){
        System.out.println("Der Gesamtbetrag des Warenkorbes wird ausgegeben ...");
        System.out.println("Erwartet: Gesamtbetrag = 45.0");

        System.out.print("Ergebnis: ");
        basketManager.gesamtBetrag();

        Subfunktion.printCharln('-',50);
    }
}
