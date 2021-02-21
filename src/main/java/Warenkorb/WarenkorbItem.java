package Warenkorb;

import Voratsbestand.Produkt;

/**
 * Die Klasse WarenkorbItem enthät die Informationen über das Produkt.
 * Next Variable weist auf das nächste Listenobjekt als einfach verkettete Liste hin.
 * Getter und Setter Methoden sind auf Variablen Zugriffe ebenfalls vorhanden.
 * @author Philip Hilspach, Andi Göttsberger
 * @version 1.3 (27.07.2020)
 */

public class WarenkorbItem {

    //Attribute
    String name;
    Produkt produkt;
    int anzahl;
    String ean;
    public WarenkorbItem next;

    //Konstruktor
    public WarenkorbItem(Produkt produkt, int anzahl, WarenkorbItem next){


        this.anzahl = anzahl;
        this.ean = produkt.getEan();
        this.next = next;
        this.produkt = produkt;
    }

    /**
     * Getter und Setter Methoden
     */
    public void setNext(WarenkorbItem next){
        this.next = next;
    }

    public WarenkorbItem getNext(){
        return next;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }




    public int getAnzahl() {
        return anzahl;
    }

    public String getEan() {
        return ean;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public double gesamtBetrag(){
        if(next == null){
            return produkt.getPreis()*anzahl;
        }else{
            return next.gesamtBetrag() + produkt.getPreis()*anzahl;
        }
    }

    public void printKassenzettelPro() {
        System.out.println(produkt.getName() + ": " + produkt.getPreis());
        if (next != null) {
            next.printKassenzettelPro();
        }
    }

    public void verbuchen(){
        if(next != null){
            next.verbuchen();
        }

        produkt.verkauf(anzahl);
    }

}
