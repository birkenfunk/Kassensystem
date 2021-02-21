package User;

import Hilfsmodule.Fehlermeldungen;

/**
 * Interface User für doppeltverkettere Liste, in der User Gespeichert werden
 * @author Alexander Asbeck
 * @version 2.0
 */
public interface User {

    /**
     * Inizialisiert die liste mit den Pararmetern
     * !!Soll nicht verwendet werden!!
     * @param user User
     * @param passwort Passwort des Users
     * @param userID UserID des Users
     * @param rechte Rechte des Users
     */
    @Deprecated
    void UserInit(String[] user, int[] passwort, int[] userID, Rechte[] rechte);

    /**
     * Sucht User
     * @param Username Benutztername, der Gesucht wird
     * @return Gsuchten User oder null
     */
    UserKnoten SucheUser(String Username);

    /**
     * Fügt User am Ende der Liste ein
     * @param Username Nutzername des Users
     * @param Passwort Passwort des Users
     * @return Fehlermeldungen
     */
    Fehlermeldungen UserEinfuegen(String Username, int Passwort);


    /**
     * Änderung des Eigenes Passwortes
     * @param UserID UserID des Users
     * @param Passwort Neues Passwort des Benutzters
     * @return Fehlermeldungen
     */
    Fehlermeldungen EigenesPasswortAendern(int UserID, int Passwort);

    /**
     * Ändern des Passworts
      * @param Username Id des Users der Geändert werden soll
     * @param Passwort Neues Passwort des Users
     * @return Fehlermeldungen
     */
    Fehlermeldungen PasswortAendern(String Username, int Passwort);

    /**
     * Löschen des Users
     * @param Username User, der Gelöscht werden soll
     * @return Fehlermeldungen
     */
    Fehlermeldungen UserLoeschen(String Username);

    /**
     * Zum Ändern der Rechte eines Users
     * @param Username Welcher User Geändert werden soll
     * @param rechte Welche Rechte der User hat
     * @return Fehlermeldungen
     */
    Fehlermeldungen RechteAendern(String Username,Rechte rechte);

    /**
     * Anzahl aller Aktuellen User
     * @return Anzahl aller Aktuellen User
     */
    int Anzahl();

    /**
     * Sucht UserID nach Namen
     * @param Username Name nach dem Gesucht wird
     * @return UserID
     */
    int SucheUserID(String Username);

    /**
     * Gibt alle Usernamen in der Liste zurück
     * !!Nur zum schreiben in die Datei gedacht!!
     * @param nummer Anzahl der Elemente in der Liste
     * @return String Array mit allen Usernamen
     */
    String[] AllUsername(int nummer);

    /**
     * Gibt alle gehashten Passwörter in der Liste zurück
     * !!Nur zum schreiben in die Datei gedacht!!
     * @param nummer Anzahl der Elemente in der Liste
     * @return String Array mit allen Passwörtern
     */
    String[] AllPasswort(int nummer);

    /**
     * Gibt alle UserIDs in der Liste zurück
     * !!Nur zum schreiben in die Datei gedacht!!
     * @param nummer Anzahl der Elemente in der Liste
     * @return String Array mit allen UserIDs
     */
    String[] AllUserID(int nummer);

    /**
     * Gibt alle Rechte in der Liste zurück
     * !!Nur zum schreiben in die Datei gedacht!!
     * @param nummer Anzahl der Elemente in der Liste
     * @return String Array mit allen Rechten
     */
    String[] AllRechte(int nummer);

    /**
     * Gibt User ID zurück
     * @return User ID
     */
    int getUserID();

    /**
     * Setzt neuen Nachfolger
     * @param nachfolger Neuer Nachfolger
     */
    void setNachfolger(User nachfolger);

    /**
     * Setzt neuen Vorgänger
     * @param vorgaenger Neuer Vorgänger
     */
    void setVorgaenger(User vorgaenger);

    /**
     * Zum erhalt des Passworts
     * @return Passwort
     */
    int getPasswort();

    /**
     * Zum erhalt der Rechte des Users
     * @return Rechte
     */
    Rechte getRechte();
}
