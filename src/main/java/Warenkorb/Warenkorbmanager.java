package Warenkorb;


import Hilfsmodule.Fehlermeldungen;
import Voratsbestand.Produkt;
import Voratsbestand.Produktverwaltung;


/**
 *  Im Warenkorbmanager wird eine Liste (Warenkorb) aufgerufen, in der ein Produkt mit einer bestimmten ean
 *  hinzugefügt und entfernt werden kann sowie geprüft wird, ob ein bestimmtes Produkt mit einer bestimmten
 *  ean in der Liste (Warenkorb) enthalten ist.
 *
 * @author Andi Göttsberger, Philip Hilspach
 * @version 1.5 (27.07.2020)
 */

public class Warenkorbmanager {


    private WarenkorbItem listenanfang;
    private static Warenkorbmanager warenkorbmanager;


    public static Warenkorbmanager getWarenkorbmanager(){
        if (warenkorbmanager == null){
            warenkorbmanager = new Warenkorbmanager();
        }

        return warenkorbmanager;
    }


    //Konstruktor
    public Warenkorbmanager(){
        this.listenanfang = null;
    }

    //Methoden/Schnittstellen

    /**
     * Die Klasse addProdukt fügt ein Produkt mit einer bestimmten ean und einer bestimmten Mengenanzahl dem Warenkorb hinzu.
     * @param ean
     * @param menge
     * @return
     */
    public Fehlermeldungen addProdukt(String ean, int menge){

        Produktverwaltung produktverwaltung = Produktverwaltung.getProduktverwaltung();
        Produkt produkt = produktverwaltung.getProduct(ean);


        if(produkt == null){
            return Fehlermeldungen.PROD_NICHT_VERF; //Liefert Fehlermeldung zurück, da Produkt nicht gefunden wurde
        }



        if(listenanfang == null){
            //Liste ist leer

            if(!(produkt.produktmengeVerfuegbar(menge)))
            { // produkt.produktmengeVerfügbar(menge) == false, if(!(true)) -> Nicht Ausführen, if(!(false)) -> Ausführem if (false == false) -> true
                return Fehlermeldungen.PROD_MENGE_NICHT_VORHANDEN;
            }

            listenanfang = new WarenkorbItem(produkt, menge, null);

            return Fehlermeldungen.PROD_WURDE_HINZUGEFUEGT;

        } else {
            // Produkt-Vorhanden, Warenkorb ist nicht leer
            WarenkorbItem vonContainsBekommen = contains(ean);

            if (vonContainsBekommen == null) {

                // Wenn das Produkt nicht im Warenkorb -> Hinzufügen

                WarenkorbItem listencursor = listenanfang; //start bei head
                while (listencursor.getNext() != null) {
                    listencursor = listencursor.getNext();  //rücke eins weiter
                }

                if(!(produkt.produktmengeVerfuegbar(menge))){ // produkt.produktmengeVerfügbar(menge) == false, if(!(true)) -> Nicht Ausführen, if(!(false)) -> Ausführem if (false == false) -> true
                    return Fehlermeldungen.PROD_MENGE_NICHT_VORHANDEN;
                }

                WarenkorbItem neuesElement = new WarenkorbItem(produkt, menge, null);

                listencursor.setNext(neuesElement);

                return Fehlermeldungen.PROD_WURDE_HINZUGEFUEGT;

            } else {
                // Wenn das Produkt im Warenkorb -> Anzahl Erhöhen
                int gesamtmenge = menge + vonContainsBekommen.getAnzahl();

                boolean produktmengeVerfuegbar = produkt.produktmengeVerfuegbar(gesamtmenge); //oder produkt.produktmengeVerfügbar(gesamtmenge) direkt in untere if-Bedingung reinschreiben, statt der Variablen


                if (produktmengeVerfuegbar == false) {

                    return Fehlermeldungen.PROD_MENGE_NICHT_VORHANDEN;

                } else {

                    vonContainsBekommen.setAnzahl(gesamtmenge);

                    return Fehlermeldungen.PROD_WURDE_HINZUGEFUEGT;

                    //gehe zu Tail:
                    //WarenkorbItem listencursor = head; //start bei head
                    // while (listencursor.getNext() != null) {
                    //    listencursor = listencursor.getNext();  //rücke eins weiter
                    // }
                    //hier ist bei n: n.next = null, d.h. n ist listencursor
                    // listencursor.setNext(newTail);  //setze Referenz b)

                }
            }
        }
    }

    /**
     * Die Methode removeProdukt entfernt ein Produkt mit einer bestimmten ean aus dem Warenkorb
     * @param ean
     * @return
     */
    public Fehlermeldungen removeProdukt(String ean){
        if (listenanfang == null){
            return Fehlermeldungen.PROD_NICHT_VERF;
        }
        else {
            if (listenanfang.getEan().equals(ean)){
                WarenkorbItem help = listenanfang.getNext();
                listenanfang = help;
                return Fehlermeldungen.PROD_WURDE_ENTFERNT;
            } else {
                if(!(listenanfang.getEan().equals(ean))){
                    WarenkorbItem vorherigerWert = listenanfang;
                    WarenkorbItem treffer = listenanfang.getNext();
                    while (!treffer.getEan().equals(ean)) {
                        vorherigerWert = treffer;
                        treffer.getNext();
                    }
                    if(treffer.getEan().equals(ean) /*&& !(treffer.getNext() == null)*/){
                        vorherigerWert.setNext(treffer.getNext());
                        return Fehlermeldungen.PROD_WURDE_ENTFERNT;
                    }

                        //return Fehlermeldungen.PROD_NICHT_VERF;



                }



            }
        }
        return Fehlermeldungen.PROD_NICHT_VERF;
    }

    /**
     * Die Methode contains prüft im Warenkorb, ob ein Produkt mit einer bestimmten ean enthalten ist.
     * @param ean
     * @return
     */
    public  WarenkorbItem contains(String ean){
        WarenkorbItem treffer = listenanfang;

        while (treffer.getNext() != null && !treffer.getEan().equals(ean)){
            treffer = treffer.getNext();
        }

        if(treffer.getEan().equals(ean)){
            System.out.println("In Warenkorb enthalten!");
            return treffer;
        }else {
            System.out.println("Nicht in Warenkorb verfügbar!");
            return null;
        }

    }

    /**
     * Berechnet rekursiv den Aktuellen Gesamtbetrag
     *
     * @return sum
     */

    public double gesamtBetrag(){
        if (listenanfang!=null) {
            double sum = listenanfang.gesamtBetrag();
            System.out.println("Gesamtbetrag = " + sum);
            return sum;
        }
        return Double.MIN_VALUE;
    }

    /**
     *Druckt den Kassenzettel. Name und Preis weren ausgegeben.
     *
     */

    public void printKassenzettel(){
        listenanfang.printKassenzettelPro();

    }

    /**
     * Die Methode verbuchen verbucht alle enthaltenen Produkte im Warenkorb in die Produktdatenbank
     */

    public void verbuchen(){
        if(!(listenanfang == null)){
            listenanfang.verbuchen();
        }//else wird weggelassen, da eh leer bleiben würde!
    }

}
