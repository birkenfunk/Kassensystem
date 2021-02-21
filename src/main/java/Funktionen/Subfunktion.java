package Funktionen;

/**
 * Für kleinere Funktionen die immer wieder gebraucht werden
 * @version 1.0
 */
public class Subfunktion {


    /**
     * Gibt 'c' amount mal auf die Konsole aus
     * @param c Zeichen das Ausgegeben werden soll
     * @param amount Wie oft soll c ausgegeben werden
     */
    public static void printChar(char c, int amount){
        for (int i=0;i<amount;i++)
        {
            System.out.print(c);
        }
    }

    /**
     * Gibt 'c' amount mal auf die Konsole aus und fügt eine Leerzeile Hinzu
     * @param c Zeichen das Ausgegeben werden soll
     * @param amount Wie oft soll c ausgegeben werden
     */
    public static void printCharln(char c, int amount){
        for (int i=0;i<amount;i++)
        {
            System.out.print(c);
        }
        System.out.print('\n');
    }

}
