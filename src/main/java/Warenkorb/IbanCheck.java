package Warenkorb;

import java.math.BigInteger;

public class IbanCheck extends Kartenzahlung {

    /**
     * IbanCheck ist Unterklasse von Kartenzahlung und validiert die eingegebene IBAN, funktioniert aktuell nur für deutsche IBANs
     * @param iban IBAN wird von Kartenzahlung geerbt
     * @return Gibt true/false aktuell zurück, ob Prüfung erfolgreich war oder nicht.
     * @author Andreas Göttsberger
     * @version 1.3 (27.07.2020)
     */

    //Konstruktor

    public IbanCheck(String iban){
        super(iban);
    }

    //Methoden

    /**
     * Die Methode checkeLängeIban prüft die Länge der IBAN
     * @param iban IBAN
     * @return Gibt true/false aktuell zurück, ob Prüfung erfolgreich war oder nicht.
     */

    public boolean checkeLaengeIban(String iban) {

        if (iban.length() == 22) {
            return true;    //LängeIban korrekt
        } else {
            return false;   //LängeIban falsch
        }

    }

    /**
     * Die Methode prüfeIban prüft, ob IBAN richtig zusammengestellt ist - funktioniert aktuell nur für deutsche IBANs
     * @param iban IBAN
     * @return Gibt true/false aktuell zurück, ob Prüfung erfolgreich war oder nicht.
     */

    public boolean pruefeIban(String iban){

        boolean vonCheckeLaengeIbanBekommen = checkeLaengeIban(iban);

        String laenderKuerzelUndPruefziffer = iban.substring(0, 3);

        if(vonCheckeLaengeIbanBekommen == true) {

            String bankleitzahlUndKontonummer = iban.substring(4, 22);

            String d = "13";
            String e = "14";
            String pruefziffer2 = "49";

            StringBuilder sb = new StringBuilder();

            sb.append(bankleitzahlUndKontonummer);
            sb.append(d);
            sb.append(e);
            sb.append(pruefziffer2);

            String NeueZeichenkette = sb.toString();

            //BigInteger sb2 = new BigInteger(NeueZeichenkette);

            BigInteger sb2 = wandleFertigZusammengesetzteIbanInIntegerUm(NeueZeichenkette);

            BigInteger sb3 = new BigInteger("97");

            BigInteger ibandivision = sb2.mod(sb3);

            BigInteger sb4 = new BigInteger("1");

            if (ibandivision.equals(sb4)) {
                return true;    //Iban korrekt
            } else {
                return false;   //Iban falsch
            }
        }else{
            return false;
        }
    }

    BigInteger wandleFertigZusammengesetzteIbanInIntegerUm(String NeueZeichenkette){

        try {
            BigInteger sb8 = new BigInteger(NeueZeichenkette);
            return sb8;
        }catch(NumberFormatException e){
            System.out.println("Zeichenkette zu lang!");
            return null;
        }

    }

}
