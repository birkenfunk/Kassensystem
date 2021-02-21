package User;

import java.io.*;

/**
 * Klasse zum lesen und beschreiben der Userdatei
 * @author Alexander Asbeck
 * @version 2.0
 */
public class UserDatenLesenSchreiben {

    String dateiName;

    public UserDatenLesenSchreiben() {
        dateiName = "User.cvs";
    }

    /**
     * Bestimmt die Zeilen in User.cvs
      * @return Zeilen der Datei User.cvs
     */
    private int ZeilenBestimmen()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(dateiName))){
            int zeilen=0;
            while (null!=reader.readLine())
            {
                zeilen++;
            }
            return zeilen;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Zum lesen der in der Datei User.cvs abgespeicherten Daten
     * @return Username, Psswort, UserID, Rechte
     */
    public String[][] lesen()
    {
        String[][] inhalt=new String[ZeilenBestimmen()][4];
        String line ="";
        String splitBy=",";
        try (BufferedReader reader = new BufferedReader(new FileReader(dateiName))) {
            for(int durchlauf=0;durchlauf<inhalt.length;durchlauf++)
            {
                line=reader.readLine();
                String text[] = line.split(splitBy);
                for (int i = 0; i < inhalt[durchlauf].length&&i<text.length; i++) {
                    inhalt[durchlauf][i]=text[i];
                }
            }
            /*for (int i = 0; i<inhalt.length;i++){
                for (int j=0;j<inhalt[i].length;j++)
                {
                    System.out.println(inhalt[i][j]);
                }
            }*/
        }catch (IOException e) {
            e.printStackTrace();
        }
        return inhalt;
    }

    /**
     * Schreiben der in text übergebenen Pararmeter in die User.cvs datei
     * @param text Text der Übertragen werden soll text[0]= username; text[1]=passwort; text[2]=userID; text[3]=rechte;
     */
    public void schreiben(String[][] text)
    {
        String[] username=text[0].clone();
        String[] passwort=text[1].clone();
        String[] userID=text[2].clone();
        String[] rechte=text[3].clone();

        try (BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dateiName)))) {
            String zeichen=",";
            for (int i=0;i<username.length;i++)
            {
                String zwischenspeicher="";
                zwischenspeicher=zwischenspeicher+username[i]+zeichen+passwort[i]+zeichen+userID[i]+zeichen+rechte[i];
                writer.write(zwischenspeicher);
                writer.newLine();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }


}
