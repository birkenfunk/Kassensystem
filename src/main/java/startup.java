import Funktionen.Subfunktion;
import User.UserManager;
import Voratsbestand.*;
import Warenkorb.Warenkorbmanager;
//import javafx.application.Application;
//

/**
 * Die Klasse startup dient zum Starten des Progamms mit einer Gui
 * @author Alexander Asbeck, Andreas Göttsberger, Vitus Henkel, Philip Hillsbach, Franz Murner
 * @version 1.3
 */
public class startup /*extends Application*/{


    public static void main(String[] args) {

        produktdatenbankVerwalten();
      //  produktInWarenkorbLegen();
        Subfunktion.printCharln('-',50);
        UserManager manager = UserManager.getUserManager();
        //launch(args);
        System.exit(0);
    }



    

    static void produktdatenbankVerwalten(){
        Produktverwaltung produktverwaltung = Produktverwaltung.getProduktverwaltung();

        produktverwaltung.loadProductFromDB(); //Immer am Programmstart

        //Beispiel für ein getProduct aufruf
        Produkt produkt = produktverwaltung.getProduct("0000000000002");
        System.out.println(produkt == null ? "Produnkt mit dieser EAN nicht vorhanden oder EAN Falsch" : produkt.getName());

        //produktverwaltung.printAllProducts();
        //produkteAnlegen(produktverwaltung);
        produktverwaltung.saveProductsInDatabase();
        produktverwaltung.printAllProducts();
    }

    static void produkteAnlegen(Produktverwaltung produktverwaltung){
        Kleidung tomtailorshirt = new Kleidung();
        tomtailorshirt.setName("Shirt");
        tomtailorshirt.setEan("0000000000000");
        tomtailorshirt.setFarbe("Blau");
        tomtailorshirt.setGroesse(Groessen.M.toString());
        tomtailorshirt.setStoff("Baumwöllchen");
        tomtailorshirt.setPackageSize(1.0, "Stk.");
        tomtailorshirt.setPreis(15, Waerungen.EURO.toString());
        tomtailorshirt.setMarke("Tom Tailor");
        produktverwaltung.addProduct(tomtailorshirt);

        Kleidung krokomarke = new Kleidung();
        krokomarke.setName("Polo");
        krokomarke.setEan("0000000000001");
        krokomarke.setFarbe("Weiß");
        krokomarke.setGroesse(Groessen.S.toString());
        krokomarke.setStoff("Stoff");
        krokomarke.setPackageSize(1.0, "Stk.");
        krokomarke.setPreis(80, Waerungen.EURO.toString());
        krokomarke.setMarke("Kokodilmarke");
        produktverwaltung.addProduct(krokomarke);
    }

    static void produktInWarenkorbLegen(){
        Warenkorbmanager warenkorbmanager = Warenkorbmanager.getWarenkorbmanager();
        System.out.println(warenkorbmanager.addProdukt("0000000000000", 1).toString());
        System.out.println(warenkorbmanager.addProdukt("0000000000001", 1).toString());
    }

    /**
     * Erstellung der Gui
     * @param primaryStage Stage, die Gestartet wird
     * @throws Exception
     */
/*    @Override
    public void start(Stage primaryStage) throws Exception {
        //Gui.Window.startGUI(primaryStage);
    }*/
}
