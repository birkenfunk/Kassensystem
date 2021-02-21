package Warenkorb;

public class Barzahlung {
    /**
     * In der Klasse Barzahlung wird die Barzahlung durchgeführt
     * @param: Für eine Barzahlung muss ein Kaufbetrag und ein gezahlter Betrag als Parameter übergeben werden
     * @return: Gibt true/false aktuell zurück, ob Barzahlung erfolgreich war oder nicht. Ebenso wird auf der Konsole der Status der Barzahlung sowie der Betrag des Rückgeldes ausgegeben.
     * @author: Andreas Göttsberger
     * @version: 1.2 (27.07.2020)
     */

    /**
     * Die Methode barzahlungTätigen führt die eigentliche Barzahlung durch.
     * @param kaufbetrag
     * @param betragGezahlt
     * @return Gibt true/false aktuell zurück, ob Barzahlung erfolgreich war oder nicht. Ebenso wird auf der Konsole der Status der Barzahlung sowie der Betrag des Rückgeldes ausgegeben.
     */

    public boolean barzahlungTaetigen(double kaufbetrag, double betragGezahlt){

        if(betragGezahlt >= kaufbetrag){
            double rueckgeld = betragGezahlt - kaufbetrag;
            System.out.println("Barzahlung erfolgt!");
            System.out.println("Rückgeld: " + rueckgeld + " Euro");
            return true;
        }else{
            System.out.println("Gezahlter Betrag nicht ausreichend!");
            return false;
        }


    }

}
