package Voratsbestand;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

import Hilfsmodule.Fehlermeldungen;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Diese Klasse hilft bei der Speicherung der Daten in ein JSON-Format.
 * Dazu habe ich eine externe Libary eingebunden, welche dies Managt
 * @author Franz Murner
 * @version 1.4 22-5-2020
 */
public class ProductDBManager {

    String JNormales_Produkt = "Normales_Produkt";
    String JProduktname = "Produktname";
    String JGebinde = "Gebinde";
    String JEinheit = "Einheit";
    String JLagerbestand = "Lagerbestand";
    String JPreis = "Preis";
    String JWaehrung = "Waehrung";
    String JEAN_CODE = "EAN_CODE";
    String JMindestlagerbestand = "Mindestlagerbestand";
    String JKleidung = "Kleidung";
    String JStoff = "Stoff";
    String JFarbe = "Farbe";
    String JMarke = "Marke";
    String JGroesse = "Groesse";



    JSONObject jsonObject = new JSONObject();
    ArrayList<Produkt> produktliste = new ArrayList<Produkt>();

    //Save Product Data
    /**
     * Diese Funktion stößt die Speicherung der Daten an
     * @param kleidung Kleidungsliste
     * @param standartProdukt Normales Produkt Liste
     * @return Gibt eine Fehlermeldung über den Status der Speicherung aus...
     */
    public Fehlermeldungen writeIntoTheDB(ArrayList<Kleidung> kleidung, ArrayList<StandartProdukt> standartProdukt){

        Fehlermeldungen meldung = Fehlermeldungen.ERFOLGREICH;

        jsonObject = new JSONObject();

        meldung = createKleidungsArray(kleidung);
        meldung = createNrmProdArray(standartProdukt);

        meldung = writeJSONFile();

        return meldung;
    }

    /**
     * Mithilfe dieser Methode werden die Daten welche zuvor erstellt wurden in eine Datei geschrieben. (Speicherung)
     */
    private Fehlermeldungen writeJSONFile(){
        try {
            FileOutputStream fileStream = new FileOutputStream(new File("ProductDB.json"));
            OutputStreamWriter writer = new OutputStreamWriter(fileStream, "UTF-8");
            writer.write(jsonObject.toJSONString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return Fehlermeldungen.FEHLER_BEIM_SCHREIBEN_IN_DB;
        }
        return Fehlermeldungen.ERFOLGREICH;
    }

    /**
     * Diese Methode kümmert sich um die Erstellung des JSON Arrays sowie der passenden JSON-Objekte
     * @param produktliste Kleidungsliste übergeben
     */
    private Fehlermeldungen createKleidungsArray(ArrayList<Kleidung> produktliste){
        try{
            JSONArray JSONProductArray = new JSONArray();
            for (Kleidung produkt: produktliste) {
                JSONObject objekt = new JSONObject();
                objekt.put(JProduktname, produkt.getName());
                objekt.put(JGebinde, produkt.getMengeProEinheit());
                objekt.put(JEinheit, produkt.getEinheit());
                objekt.put(JLagerbestand, String.valueOf(produkt.getLagerbestand()));
                objekt.put(JPreis, produkt.getPreis());
                objekt.put(JWaehrung, produkt.getWaerung());
                objekt.put(JEAN_CODE, produkt.getEan());
                objekt.put(JMindestlagerbestand, String.valueOf(produkt.getMinimalMenge()));
                objekt.put(JStoff, produkt.getStoff());
                objekt.put(JFarbe, produkt.getFarbe());
                objekt.put(JMarke, produkt.getMarke());
                objekt.put(JGroesse, produkt.getGroesse());
                //Lagerinfos??
                JSONProductArray.add(objekt);
            }
            jsonObject.put(JKleidung, JSONProductArray);
            return Fehlermeldungen.ERFOLGREICH;
        }catch (Exception e){
            return Fehlermeldungen.FEHLER_BEIM_SCHREIBEN_IN_DB;
        }
    }

    /**
     * Diese Methode kümmert sich um die Erstellung des JSON Arrays sowie der passenden JSON-Objekte
     * @param produktliste NrmProd Liste
     */
    private Fehlermeldungen createNrmProdArray(ArrayList<StandartProdukt> produktliste){
        try{
            JSONArray JSONProductArray = new JSONArray();
            for (StandartProdukt produkt: produktliste) {
                JSONObject objekt = new JSONObject();
                objekt.put(JProduktname, produkt.getName());
                objekt.put(JGebinde, produkt.getMengeProEinheit());
                objekt.put(JEinheit, produkt.getEinheit());
                objekt.put(JLagerbestand, String.valueOf(produkt.getLagerbestand()));
                objekt.put(JPreis, produkt.getPreis());
                objekt.put(JWaehrung, produkt.getWaerung());
                objekt.put(JEAN_CODE, produkt.getEan());
                objekt.put(JMindestlagerbestand, String.valueOf(produkt.getMinimalMenge()));
                //Lagerinfos??
                JSONProductArray.add(objekt);
            }
            jsonObject.put(JNormales_Produkt, JSONProductArray);
        }catch (Exception e){
            return Fehlermeldungen.FEHLER_BEIM_SCHREIBEN_IN_DB;
        }

        return Fehlermeldungen.ERFOLGREICH;
    }


    //Load Product Data
    /**
     * Diese Funktion holt sich die Daten aus der JSON-Datenbank und ordnet diese den jeweiligen Klassen zu
     * @return Gibt die komplette Produktliste mit Objekten zurück, NULL wenn ein Fehler passiert
     */
    public ArrayList<Produkt> loadFromTheDB(){
        jsonObject = new JSONObject();

        Fehlermeldungen meldung = readJSONFile();

        if (meldung == Fehlermeldungen.ERFOLGREICH){
            parseKleidungFromJSONToObject((JSONArray) jsonObject.get(JKleidung));
            parseNrmProdFromJSONToObject((JSONArray) jsonObject.get(JNormales_Produkt));
        }

        return produktliste;
    }

    /**
     * Mit dieser Methode werden die Daten aus der JSON-Datei wieder geladen.
     */
    private Fehlermeldungen readJSONFile(){
        JSONParser parser = new JSONParser();

        try {
            Reader reader = new FileReader("ProductDB.json");
            jsonObject = (JSONObject) parser.parse(reader);
            reader.close();
        } catch (FileNotFoundException e) {
            writeJSONFile();
        } catch (ParseException e) {
            writeJSONFile();
        } catch (IOException e) {
            e.printStackTrace();
            return Fehlermeldungen.FEHLER_BEIM_LADEN_AUS_DB;
        }

        return Fehlermeldungen.ERFOLGREICH;
    }

    private void parseKleidungFromJSONToObject(JSONArray jsonArray){

        if (jsonArray != null) {

            for (int i = 0; i < jsonArray.size(); i++) {

                JSONObject objekt = (JSONObject) jsonArray.get(i);
                Kleidung produkt = new Kleidung();

                produkt.setName(
                        (String) objekt.get(JProduktname)
                );
                produkt.setMengeProEinheit(
                        (Double) objekt.get(JGebinde)
                );
                produkt.setEinheit(
                        (String) objekt.get(JEinheit)
                );
                produkt.setLagerbestand(
                        Integer.parseInt(
                                (String) objekt.get(JLagerbestand)
                        )
                );
                produkt.setPreis(
                        (Double) objekt.get(JPreis)
                );
                produkt.setWaerung(
                        (String) objekt.get(JWaehrung)
                );
                produkt.setEan(
                        (String) objekt.get(JEAN_CODE)
                );
                produkt.setMinimalMenge(
                        Integer.parseInt(
                                (String) objekt.get(JMindestlagerbestand)
                        )
                );
                produkt.setStoff(
                        (String) objekt.get(JStoff)
                );
                produkt.setFarbe(
                        (String) objekt.get(JFarbe)
                );
                produkt.setMarke(
                        (String) objekt.get(JMarke)
                );
                produkt.setGroesse(
                        (String) objekt.get(JGroesse)
                );

                produktliste.add(produkt);
            }
        }
    }

    private void parseNrmProdFromJSONToObject(JSONArray jsonArray){
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {

                JSONObject objekt = (JSONObject) jsonArray.get(i);
                StandartProdukt produkt = new StandartProdukt();

                produkt.setName(
                        (String) objekt.get(JProduktname)
                );
                produkt.setMengeProEinheit(
                        (Double) objekt.get(JGebinde)
                );
                produkt.setEinheit(
                        (String) objekt.get(JEinheit)
                );
                produkt.setLagerbestand(
                        Integer.parseInt(
                                (String) objekt.get(JLagerbestand)
                        )
                );
                produkt.setPreis(
                        (Double) objekt.get(JPreis)
                );
                produkt.setWaerung(
                        (String) objekt.get(JWaehrung)
                );
                produkt.setEan(
                        (String) objekt.get(JEAN_CODE)
                );
                produkt.setMinimalMenge(
                        Integer.parseInt(
                                (String) objekt.get(JMindestlagerbestand)
                        )
                );
                produktliste.add(produkt);
            }
        }
    }
}
