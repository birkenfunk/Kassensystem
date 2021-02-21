package Voratsbestand;

/**
 * Die Klasse Kleidung erbt von der Klasse Produkt und erweitert diese um Kleidungsspezifische Inhalte
 * @author Franz Murner
 * @version 1.1
 */
public class Kleidung extends Produkt{
    private String stoff;
    private String farbe;
    private String marke;
    private String groesse;

    //Initialisierung

    public Kleidung(){
        produktart = "Kleidung";
        prodeigenschaftHeader.add(new ColoumDiameterMap("Stoff", 15));
        prodeigenschaftHeader.add(new ColoumDiameterMap("Farbe", 6));
        prodeigenschaftHeader.add(new ColoumDiameterMap("Marke", 15));
        prodeigenschaftHeader.add(new ColoumDiameterMap("Groeße", 6));
    }


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append(String.format("%-" + prodeigenschaftHeader.get(8).getZeilenbreite() +"s| ",stoff));
        stringBuilder.append(String.format("%-" + prodeigenschaftHeader.get(9).getZeilenbreite() +"s| ",farbe));
        stringBuilder.append(String.format("%-" + prodeigenschaftHeader.get(10).getZeilenbreite() +"s| ",marke));
        stringBuilder.append(String.format("%-" + prodeigenschaftHeader.get(11).getZeilenbreite() +"s| ",groesse));


        return super.toString() + stringBuilder.toString();
    }

    // Setter
    public void setStoff(String stoff) {
        this.stoff = stoff;
    }

    public void setFarbe(String farbe){
        this.farbe = farbe;
    }

    public void setMarke(String marke){
        this.marke = marke;
    }

    public void setGroesse(String groesse) {
        this.groesse = groesse;
    }

    //Getter
    public String getStoff() {
        return stoff;
    }

    public String getFarbe() {
        return farbe;
    }

    public String getMarke() {
        return marke;
    }

    public String getGroesse() {
        return groesse;
    }


}
