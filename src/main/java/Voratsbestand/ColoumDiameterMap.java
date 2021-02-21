package Voratsbestand;

/**
 * Diese Klasse dient dazu für die Ausgabe der Prdukteigenschafen die richtige Menge an Platzhalter zu reservieren.
 * Zusätzlich hilft sie bei der Abspeicherung der Daten mit Variablen zu Arbeiten, damit Fehler durch Rechtschreibfehler ausgemerzt werden
 * @author Franz Murner
 * @version 1.1
 */
public class ColoumDiameterMap {
    private String key;
    private int value;

    public ColoumDiameterMap(String eigenschaft, int zeilenbreite){
        this.key = eigenschaft;
        this.value = zeilenbreite;
    }

    public String getEigenschaft() {
        return key;
    }

    public int getZeilenbreite() {
        return value;
    }
}
