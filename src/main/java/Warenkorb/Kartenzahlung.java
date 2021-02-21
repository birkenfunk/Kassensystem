package Warenkorb;

public class Kartenzahlung {

    /**
     * Kartenzahlung ist Oberklasse von IbanCheck und übergibt IbanCheck die IBAN
     * In Kartenzahlung wird die Kartenzahlung durchgeführt, worin der IbanCheck aufgerufen und der Kontobetrag (Startwert/Anfangswert immer: 500 Euro) des Kunden pseudomäßig überprüft wird. Ebenfalls wird nach jeder einzelnen Kartenzahlung der Kontobetrag minimiert und geprüft, ob aktueller Kaufbetrag noch mit aktuellem Kontobetrag übereinstimmt.
     * @param: Für eine Kartenzahlung muss eine IBAN und ein Kaufbetrag als Parameter übergeben
     * @return: Gibt true/false aktuell zurück, ob Kartenzahlung erfolgreich war oder nicht.
     * @author: Andreas Göttsberger
     * @version: 1.3 (27.07.2020)
     */

    //Attribute

    protected String iban;
    private double betrag;
    //Szenario, dass Käufer maximal 500 Euro auf dem Konto hat
    private static double betragAufKonto = 500;

    //Konstruktor

    public Kartenzahlung(double betrag){
        this.betrag = betrag;
    }

    public Kartenzahlung(String iban){
        this.iban = iban;
    }
    public Kartenzahlung(String iban, double betrag){
        this(iban);
        this.betrag = betrag;
    }

    //Methoden

    /**
     * Die Methode kartenzahlungTätigen führt die eigentliche Kartenzahlung durch, d.h. es wird die IBAN mit dem IbanCheck geprüft und der Kontobetrag (Anfangs: 500 Euro) mit dem Kaufbetrag abgeglichen, ob Kaufbetrag höchstens dem Kontobetrag entspricht. Ebenfalls wird der Kontobetrag nach jeder Kartenzahlung angepasst (dem Kaufbetrag entsprechend minimiert).
     * @param iban IBAN
     * @param betrag Kaufbetrag
     * @return Gibt true/false aktuell zurück, ob Kartenzahlung erfolgreich war oder nicht.
     */
    public boolean kartenzahlungTaetigen(String iban, double betrag){

        IbanCheck checkeIban = new IbanCheck(iban);

        boolean wertVonPruefeIbanBekommen = checkeIban.pruefeIban(iban);

        if(wertVonPruefeIbanBekommen == true){

            //this.iban = iban; --> Evtl. erst hier die iban der iban-Variable fest zuweisen?

            boolean pruefeKontoBetragObNochAusreichend = checkeIban.validiereKontobetrag(betrag);

            if(pruefeKontoBetragObNochAusreichend == true){
                return true;
            }else{
                return false;
            }


        }else{
            return false;
        }

    }

    /**
     * Prüft Bankkonto vom Kunden pseudomäßig, ob er noch ausreichend Geld auf dem Konto hat (Anfangswert (Kontobetrag): 500 Euro --> wird nach jeder einzelnen Kartenzahlung minimiert)
     * @param betrag Kaufbetrag wird als Parameter übergeben
     * @return Gibt true aktuell zurück, wenn Kaufbetrag <= Kontobetrag ist; false wird zurückgeliefert, wenn Kaufbetrag negativ ist oder Kaufbetrag > Kontobetrag ist.
     */


    public boolean validiereKontobetrag(double betrag){

        double i = 0;

        if(i <= betrag && betrag <= betragAufKonto){

            double neuerKontostand = betragAufKonto - betrag;

            this.betragAufKonto = neuerKontostand;

            return true;
        }else{
            return false;
        }

    }

    public double getBetragAufKonto() {
        return betragAufKonto;
    }
}
