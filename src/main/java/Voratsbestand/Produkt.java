package Voratsbestand;

import Hilfsmodule.EANCheck;
import Hilfsmodule.Fehlermeldungen;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Dieses ist die Abstrakte Klasse welche ein allgemeines Produnkt beschreibt
 * @author Franz Murner
 * @version 6.July.2020
 */

abstract public class Produkt {

    // Variablen
    protected String produktart = "Produkt";
    private String ean = "0000000000000";
    private String name = "N/A";
    private int lagerbestand = 0;
    private double mengeProEinheit = 0.0; // Angabe des Gewichtes oder der Gebindegröße.
    private String einheit = "N/A";

    private double preis = 0;
    private String waerung = Waerungen.EURO.toString();
    private int minimalMenge = 0; //Angabe der Minimalen Produktmenge bevor eine Warnung ausgegeben wird

    //Setup Array List für Print Darstellungstabelle
    protected ArrayList <ColoumDiameterMap> prodeigenschaftHeader = new ArrayList<ColoumDiameterMap>(){
        {
            add(new ColoumDiameterMap("Produktname", 20));
            add(new ColoumDiameterMap("Gebinde", 8));
            add(new ColoumDiameterMap("Einh.", 5));
            add(new ColoumDiameterMap("Lager", 6));
            add(new ColoumDiameterMap("Preis", 10));
            add(new ColoumDiameterMap("W", 10));
            add(new ColoumDiameterMap("EAN-Code", 15));
            add(new ColoumDiameterMap("Min-Lager", 10));
        }
    };

    /**
     * Prüfen ob bestimmte Produkte verfügbar sind, um sie auf dem Interface dementspreched anzuzeigen.
     * @return Es wird zurückgegeben ob das Produkt Verfügbar, Knapp oder Aus ist.
     */
    public Fehlermeldungen checkAvailability(){

        if (lagerbestand <= 0){
            return Fehlermeldungen.PROD_NICHT_VERF;
        }else if (lagerbestand <= minimalMenge){
            return Fehlermeldungen.PROD_KNAPP;
        }

        return Fehlermeldungen.PROD_VORHANDEN;
    }

    /**
     * Prüft ab ob die gewünschte Produktmenge verfügbar ist
     * @param stueckzahl Gewünschte Menge
     * @return True für vorhanden; False für nicht vorhanden
     */
    public boolean produktmengeVerfuegbar(int stueckzahl){
        if (lagerbestand < stueckzahl){
            return false;
        }

        return true;
    }

    /**
     * Diese Methode wird aufgerufen wenn etwas Verkauft wird
     * @param stuekzahl Es kann hier auch dementsprechend die Menge angegeben werden, die Verkauft wird
     * @return Wenn die Menge noch im Lager vorhanden ist wird diese abgezogen, vermeidung eines <0 Lagerbestandes
     */
    public Fehlermeldungen verkauf(int stuekzahl) {
        if (produktmengeVerfuegbar(stuekzahl)){
            this.lagerbestand -= stuekzahl;
            return Fehlermeldungen.KEINFEHLER;
        }else{
            return Fehlermeldungen.PROD_MENGE_NICHT_VORHANDEN;
        }
    }

    /**
     * Diese Methode wird beim Füllen des Lagerbestandes aufgerufen.
     * @param stuekzahl Hinzuzufügende Produktmenge
     */
    public void zukauf(int stuekzahl){
        this.lagerbestand += stuekzahl;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("%-" + prodeigenschaftHeader.get(0).getZeilenbreite() +"s| ",name));
        stringBuilder.append(String.format("%-" + prodeigenschaftHeader.get(1).getZeilenbreite() +".3f| ",mengeProEinheit));
        stringBuilder.append(String.format("%-" + prodeigenschaftHeader.get(2).getZeilenbreite() +"s| ",einheit));
        stringBuilder.append(String.format("%-" + prodeigenschaftHeader.get(3).getZeilenbreite() +"d| ",lagerbestand));
        stringBuilder.append(String.format("%-" + prodeigenschaftHeader.get(4).getZeilenbreite() +".2f| ",preis));
        stringBuilder.append(String.format("%-" + prodeigenschaftHeader.get(5).getZeilenbreite() +"s| ",waerung));
        stringBuilder.append(String.format("%-" + prodeigenschaftHeader.get(6).getZeilenbreite() +"s| ",ean));
        stringBuilder.append(String.format("%-" + prodeigenschaftHeader.get(7).getZeilenbreite() +"s| ",minimalMenge));

        return stringBuilder.toString();
    }

    /**
     * @return Rückgabe der Head Zeile als String
     */
    public String toStringHead(){
        StringBuilder stringBuilder = new StringBuilder();

        for (ColoumDiameterMap header: prodeigenschaftHeader) {
            stringBuilder.append(String.format("%-" +header.getZeilenbreite() + "s| ", header.getEigenschaft()));
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produkt produkt = (Produkt) o;
        return ean.equals(produkt.ean);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produktart + ean + name);
    }

    //Setter
    /**
     * Damit kann man Festlegen welche größe die Produkte haben (l, kg, cm,...)
     * @param mengeProEinheit Die Gebindegröße der Einheit. BSP: O.75
     * @param einheit Einheitsbezeichnung. BSP: l
     */
    public void setPackageSize(double mengeProEinheit, String einheit){
        this.mengeProEinheit = mengeProEinheit;
        this.einheit = einheit;
    }

    /**
     * Diese Funktion setzt die EAN-Nummer für das Produkt und Prueft dessen Gueltigkeit
     * @param ean EAN mit 13 Stellen und der richtigen Prüfziffer (PZ kann zu Testzwecken in Klasse deaktiviert werden)
     * @return Eine Meldung welche die möglichkeit gibt ggf. darauf entsprechend zu reagieren
     */
    public Fehlermeldungen setEan(String ean) {
        EANCheck eanCheck = new EANCheck();
        Fehlermeldungen meldung = eanCheck.checkEANCode(ean);

        if (meldung.equals(Fehlermeldungen.EAN_CHECK_ERFOLGREICH)){
            this.ean = ean;
        }
        return meldung;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLagerbestand(int lagerbestand) {
        this.lagerbestand = lagerbestand;
    }

    public void setPreis(double preis, String waerung) {
        this.preis = preis;
        this.waerung = waerung;
    }


    /**
     * Hier wird Angegeben ab welchen Grenzwert das Programm ein Warnung ausgeben soll
     * @param minimalMenge Ganzzahlige Menge
     */
    public void setMinimalMenge(int minimalMenge) {
        this.minimalMenge = minimalMenge;
    }

    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public void setMengeProEinheit(double mengeProEinheit) {
        this.mengeProEinheit = mengeProEinheit;
    }

    public void setWaerung(String waerung) {
        this.waerung = waerung;
    }

    //Getter

    public String getName() {
        return name;
    }

    public String getEan() {
        return ean;
    }

    public double getPreis() {
        return preis;
    }

    public String getWaerung() {
        return waerung;
    }

    public int getLagerbestand() {
        return lagerbestand;
    }

    public int getMinimalMenge() {
        return minimalMenge;
    }

    public double getMengeProEinheit() {
        return mengeProEinheit;
    }

    public String getEinheit() {
        return einheit;
    }

    public String getProduktart() {
        return produktart;
    }

    public ArrayList<ColoumDiameterMap> getProdeigenschaftHeader() {
        return prodeigenschaftHeader;
    }
}
