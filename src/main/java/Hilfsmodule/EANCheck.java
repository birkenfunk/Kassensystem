package Hilfsmodule;

/**
 * Diese Klasse dient zur Feststellung von Fehlern in einem EAN
 * Kann dazu verwendet werden um Fehler bei der Eingabe eines neuen Produktes zu vermeiden oder aber auch Fehler bei der Eingabe zu erkennen.
 * @author Franz Murner
 * @version 1.0
 */
public class EANCheck {

    static private boolean PZCheckEnabled = true;


    /**
     * Prüfalgorithmus welcher sich um das Prüfen der Artikelnummer kümmert.
     * @param EAN Die Produktnummer des Artikels
     * @return Hier werden verschiedene Arten von Fehlern durchgegeben, damit darauf reagiert werden kann.
     */
    public Fehlermeldungen checkEANCode(String EAN){
        Fehlermeldungen returnValue;

        int length = EAN.length();

        if (length < 13){
            returnValue = Fehlermeldungen.EAN_ZU_KURZ;
        }else if (length > 13){
            returnValue = Fehlermeldungen.EAN_ZU_LANG;
        }else{
            returnValue = pruefvorgang(EAN);
        }

        return returnValue;
    }

    /**
     * Hier findet der eigentliche Prüfvorgang (Berechnung) statt
     * @param EAN
     * @return Erfolgsmeldung oder Fehlermeldung
     */
    private Fehlermeldungen pruefvorgang(String EAN){

        int[] ziffern = new int[13];

        for (int i = 0; i < 13; i++) {
            try{
                String zeichen = String.valueOf(EAN.charAt(i));
                ziffern[i] = Integer.parseInt(zeichen);

            }catch (Exception e){
                return Fehlermeldungen.EAN_MIT_ZEICHEN;
            }
        }

        int summeZwoelfZiffern = 0;

        /**
         * Jeweils wird eine Ziffer mit eins multipliziert und eine Ziffer mit drei Multipliziert
         */
        for (int i = 0; i < 12; i++) {
            if (i % 2 == 1){
                summeZwoelfZiffern += (ziffern[i] * 3);
            }else{
                summeZwoelfZiffern += (ziffern[i]);
            }
        }

        /**
         * Die Differenz auf das nächste vielfache von zehn wird hier ermittelt. Bei zehn selber ist die PZ 0!
         */
        int sollPruefziffer = 10 - (summeZwoelfZiffern % 10) == 10 ? 0 : 10 - (summeZwoelfZiffern % 10);

        if (PZCheckEnabled == false){
            return Fehlermeldungen.EAN_CHECK_ERFOLGREICH;
        }

        if (sollPruefziffer == ziffern[12])
        {
            return Fehlermeldungen.EAN_CHECK_ERFOLGREICH;
        }else{
            return Fehlermeldungen.EAN_PZ_FALSCH;
        }
    }

    public static void setPZCheckEnabled(boolean PZCheckEnabled) {
        EANCheck.PZCheckEnabled = PZCheckEnabled;
    }
}
