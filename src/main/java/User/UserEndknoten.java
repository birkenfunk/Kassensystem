package User;

import Hilfsmodule.Fehlermeldungen;

/**
 * Klasse UserEndknoten hängt am Ende der doppelverketteten Liste
 * @author Alexander Asbeck
 * @version 2.1
 */
public class UserEndknoten implements User{


    private User Vorgaenger;

    public UserEndknoten() {

    }

    @Override
    public void UserInit(String[] user, int[] passwort, int[] userID, Rechte[] rechte) {
        for (int i =0;i<user.length;i++)
        {
            User u =new UserKnoten(this,Vorgaenger,user[i],passwort[i],userID[i],rechte[i]);
            Vorgaenger=u;
        }
    }

    @Override
    public UserKnoten SucheUser(String Username) {
        return null;
    }

    @Override
    public Fehlermeldungen UserEinfuegen(String Username, int Passwort) {
        if (Vorgaenger!=null)
        {
            User user = new UserKnoten(this, Vorgaenger, Username, Passwort, Vorgaenger.getUserID() + 1);
            Vorgaenger=user;
        }else {
            User user = new UserKnoten(this, Vorgaenger, Username, Passwort, 1);
            Vorgaenger=user;
        }
        return Fehlermeldungen.ERFOLGREICH;
    }

    @Override
    public Fehlermeldungen EigenesPasswortAendern(int UserID, int Passwort) {
        return Fehlermeldungen.USER_NICHT_GEFUNDEN;
    }

    @Override
    public Fehlermeldungen PasswortAendern(String Username, int Passwort) {
        return Fehlermeldungen.USER_NICHT_GEFUNDEN;
    }

    @Override
    public Fehlermeldungen UserLoeschen(String Username) {
        return Fehlermeldungen.USER_NICHT_GEFUNDEN;
    }

    @Override
    public Fehlermeldungen RechteAendern(String Username, Rechte rechte) {
        return Fehlermeldungen.USER_NICHT_GEFUNDEN;
    }

    @Override
    public int Anzahl() {
        return 0;
    }

    @Override
    public int SucheUserID(String Username) {
        return Integer.MIN_VALUE;
    }

    @Override
    public String[] AllUsername(int nummer) {
        return new String[nummer];
    }

    @Override
    public String[] AllPasswort(int nummer) {
        return new String[nummer];
    }

    @Override
    public String[] AllUserID(int nummer) {
        return new String[nummer];
    }

    @Override
    public String[] AllRechte(int nummer) {
        return new String[nummer];
    }

    @Override
    public int getUserID() {
        return Integer.MIN_VALUE;
    }

    @Override
    public void setVorgaenger(User vorgaenger) {
        Vorgaenger=vorgaenger;
    }

    @Override
    public int getPasswort() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Rechte getRechte() {
        return Rechte.NO_LOGIN_PERMISSION;
    }

    @Override
    public void setNachfolger(User nachfolger) {

    }
}
