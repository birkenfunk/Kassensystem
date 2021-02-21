package Voratsbestand;
import Hilfsmodule.EANCheck;
import Hilfsmodule.Fehlermeldungen;
import Hilfsmodule.PrintHelper;

import java.util.ArrayList;

/**
 * Diese Klasse beschäftigt sich mit der Verwaltung, Suche und Festspeicherung, sowie dem Laden der Daten aus der Produktdatenbankdatei
 * @author Franz Murner
 * @version 1.3.1 22-05-2020
 */
public class Produktverwaltung {

    private static Produktverwaltung produktverwaltung;

    //Alle Produkte
    private ArrayList <Produkt> produktliste = new ArrayList<Produkt>();
    private ArrayList <Kleidung> kleidungsliste = new ArrayList<Kleidung>();
    private ArrayList <StandartProdukt> standartProduktliste = new ArrayList<StandartProdukt>();

    /**
     * Da nur eine Produktverwaltung auf dem Kassensystem existieren soll, muss man sich das Objekt über diese Methode holen.
     * Aus diesem Grund hat es nur wenig sinn eine neue Produktvewaltung zu erzeugen.
     * Die getProduktverwaltung() Methode prüft ob bereits eine Produktverwaltung existiert und wenn nicht dann erstellt sie eine.
     * @return Das Objekt Produktverwaltung
     */
    public static Produktverwaltung getProduktverwaltung() {
        if (produktverwaltung == null){
            produktverwaltung = new Produktverwaltung();
        }

        return produktverwaltung;
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        produktverwaltung = null;
    }

    /**
     * Diese Metode dient dem hinzufügen eines neuen Produktes auf die Liste.
     * @param produkt Produktobjekt welches zuvor erstellt wurde
     * @return Fehlermeldung ob Produkt erfolgreich hinzugefügt oder Produkt mit dieser EAN schon vorhanden
     */
    public Fehlermeldungen addProduct(Produkt produkt){
        for (Produkt produktAusListe: produktliste) {
            if (produktAusListe.getEan().equals(produkt.getEan())){
                return Fehlermeldungen.PROD_BEREITS_VORHANDEN;
            }
        }

        produktliste.add(produkt);
        sortInProductList();

        return Fehlermeldungen.ERFOLGREICH;
    }

    /**
     * Diese Methode Löscht ein Objekt aus der Produktliste, Achtung es wird nicht aus der Datenbank gelöscht bis die Datenbank neu gesichert wird!!
     * @param EAN Das zu löschende Produkt
     * @return Fehlermeldung bei nichtvorhandensein des Produktes oder beim Löschen
     */
    public Fehlermeldungen removeProduct(String EAN){
        Produkt produkt = getProduct(EAN);

        if (produkt != null){
            Boolean result = produktliste.remove(produkt);
            if (result){
                sortInProductList();
                return Fehlermeldungen.PROD_WURDE_ENTFERNT;
            }else{
                return Fehlermeldungen.PROD_FEHLER_BEIM_ENTFERNEN;
            }
        }else{
            return Fehlermeldungen.PROD_NICHT_VERF;
        }
    }


    /**
     * Löscht alle Produkte aus der Produkteverwaltung, aber nicht aus der Datenbank.
     * Zum löschen aller Produkte aus der Datenbank rufe Produktverwaltung.saveProductsInDatabase auf. --> Sicherheit vor kompletten löschen der Datenbank
     */
    public void removeAllProducts(){
        produktliste = new ArrayList<Produkt>();
        sortInProductList();
    }

    /**
     * Diese Funktion nimmt einen EAN an und prüft diesen auf Validität, bei richtigem EAN wird das Produkt gesucht.
     * @param EAN
     * @return NULL: Wenn das Produkt nicht gefunden wurde. Produkt: Wenn ein Produkt gefunden wurde.
     * <pre>
     * {@code
     * Beispiel Code:
     * Produkt produkt = produktverwaltung.getProdukt("0000000000000");
     * if (produkt == null){
     *     Reagiere entsprechend;
     * }else{
     *     alles gut;
     * }
     *
     * </pre>
     *
     *
     */
    public Produkt getProduct(String EAN){
        EANCheck eanCheck = new EANCheck();
        Fehlermeldungen status = eanCheck.checkEANCode(EAN);
        if (status == Fehlermeldungen.EAN_CHECK_ERFOLGREICH){
            for (Produkt produkt: produktliste) {
                if (produkt.getEan().equals(EAN)){
                    return produkt;
                }
            }
        }else{
            System.out.println("Fehler bei der Übergabe vom Produkt: " + status.toString());
        }
        return null;
    }

    /**
     * Diese Funktion sortiert die Produkte nach Klasse des Produktes vor
     */
    private void sortInProductList(){
        kleidungsliste = new ArrayList<Kleidung>();
        standartProduktliste = new ArrayList<StandartProdukt>();

        for(Produkt produkt: produktliste){

           if (produkt.getClass() == Kleidung.class){
               kleidungsliste.add((Kleidung) produkt);
           }
           if (produkt.getClass() == StandartProdukt.class){
               standartProduktliste.add((StandartProdukt) produkt);
           }
       }
    }

    /**
     * Fügt die Kleidungsliste in die dafür vorgesehenen Spalte ein
     */
    private void printKleidungsList(){
        if (kleidungsliste.isEmpty()){
            System.out.println("Keine Produkte in Liste");
        }else{
            System.out.println(kleidungsliste.get(0).toStringHead());
            for (Kleidung kleidung: kleidungsliste) {
                System.out.println(kleidung.toString());
            }
        }
    }

    private void printNormalesProductList(){
        if (standartProduktliste.isEmpty()){
            System.out.println("Keine Produkte in Liste");
        }else{
            System.out.println(standartProduktliste.get(0).toStringHead());
            for (StandartProdukt standartProdukt : standartProduktliste) {
                System.out.println(standartProdukt.toString());
            }
        }
    }

    /**
     * Auruf alle Produkte auf der Konsole auszugeben
     */
    public void printAllProducts(){
        sortInProductList();
        PrintHelper.printlnCenteredTextString("Kleidungslager", "-", 100);
        printKleidungsList();
        PrintHelper.printlnCenteredTextString("Standartproduktlager", "-", 100);
        printNormalesProductList();
    }

    /**
     * Speicherung der Daten in die JSON Datenbank anstoßen
     */
    public void saveProductsInDatabase(){
        sortInProductList();
        ProductDBManager productDBManager = new ProductDBManager();
        productDBManager.writeIntoTheDB(kleidungsliste, standartProduktliste);
    }

    /**
     * Stößt das Laden der Produktdatenbank an
     */
    public void loadProductFromDB(){
        ProductDBManager productDBManager = new ProductDBManager();
        produktliste = productDBManager.loadFromTheDB();
        sortInProductList();
    }

    public ArrayList<Kleidung> getKleidungsliste() {
        return kleidungsliste;
    }

    public ArrayList<StandartProdukt> getStandartProduktliste() {
        return standartProduktliste;
    }
}
