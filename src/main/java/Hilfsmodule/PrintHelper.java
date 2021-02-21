package Hilfsmodule;

/**
 * Diese Klasse vereinfacht die Ausgabe von bestimmten Aufgaben auf die Konsole
 * @author Franz Murner
 * @version 04.June.2020
 */
public class PrintHelper {

    /**
     * Ausgabe von x Strings auf der Konsole ohne Zeilenumbruch
     * @param string String der Ausgegeben werden soll
     * @param times Anzahl der Ausgaben...
     */
    public static void printString(String string, int times){
        for(int i = 0; i < times; i++){
            System.out.printf("%s", string);
        }
    }

    /**
     * Ausgabe von x Strings auf der Konsole mit Zeilenumbruch
     * @param string String der Ausgegeben werden soll
     * @param times Anzahl der Ausgaben...
     */
    public static void printlnString(String string, int times){
        for(int i = 0; i < times; i++){
            System.out.printf("%s", string);
        }
        System.out.println();
    }

    /**
     * Zentrierte Ausgabe eines Textes welcher links und rechts von Zeichen umgeben ist
     * @param text Gewünschter Zentrierter Text
     * @param character Gewünschtes Zeichen links und rechts
     * @param characterTimes Gesamte Menge aller Zeichen (Mit Text) in der Überschrift
     */
    public static void printCenteredTextString(String text, String character, int characterTimes){
        int printChars = characterTimes - text.length();
        printString(String.valueOf(character), printChars / 2);
        System.out.printf("%s", text);
        printString(String.valueOf(character), printChars / 2);
    }

    public static void printlnCenteredTextString(String text, String character, int characterTimes){
        int printChars = characterTimes - text.length();
        printString(character, printChars / 2);
        System.out.printf("%s", text);
        printString(character, printChars / 2);
        System.out.println();
    }

    public static void newLine(){
        System.out.println();
    }


}
