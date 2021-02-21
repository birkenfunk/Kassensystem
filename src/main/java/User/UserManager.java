/**
 * Verwaltung der UserListe
 * @author Alexander Asbeck
 * @version 2.4
 */

package User;

import Hilfsmodule.Fehlermeldungen;

public class UserManager {

    private static UserManager userManager;
    private User Listenanfang;
    private User AktuellerUser;
    private UserDatenLesenSchreiben daten;

    /**
     * Privater Konstruktor für UserManager
     */
    private UserManager() {
        Listenanfang=new UserEndknoten();
        daten = new UserDatenLesenSchreiben();
    }

    /**
     * Für den Login eines Angemeldeten Users
     * @param Username Username des Users
     * @param Passwort Passwort des Users
     * @return Rechte des Benutzters
     */
    public Rechte Login(String Username, String Passwort)
    {
        if(Username==null||Username==""||Passwort==null||Passwort=="")
        {
            throw new IllegalArgumentException("No Username/ Password was handed over");
        }
        User user= Listenanfang.SucheUser(Username);
        if (user!=null&&user.getPasswort()==Passwort.hashCode())
        {
            AktuellerUser = user;
            return user.getRechte();
        }
        return Rechte.NO_LOGIN_PERMISSION;
    }

    /**
     * Für den Logout eines Users
     * @return Fehlermeldungen
     */
    public Fehlermeldungen Logout()
    {
        AktuellerUser=null;
        schreiben();
        return Fehlermeldungen.ERFOLGREICH;
    }

    /**
     * Zum erstellen eines neuen Benutzers
     * @param Username Username des neuen Users
     * @param Passwort Passwort des neuen Users
     * @return Fehlermeldungen
     */
    public Fehlermeldungen UserEinfuegen(String Username, String Passwort)
    {
        if(Username==null||Username==""||Passwort==null||Passwort=="")
        {
            throw new IllegalArgumentException("No Username/ Password was handed over");
        }
        if (AktuellerUser!=null&&(AktuellerUser.getRechte()==Rechte.ADMIN||AktuellerUser.getRechte()==Rechte.SUPER_ADMIN))
        {

            Fehlermeldungen fehlermeldungen = Listenanfang.UserEinfuegen(Username, Passwort.hashCode());
            schreiben();
            return fehlermeldungen;
        }
        return Fehlermeldungen.KEINE_RECHTE;
    }

    /**
     * Löschen eines Users
     * @param UserName Username des Users
     * @return Fehlermeldungen
     */
    public Fehlermeldungen UserLoeschen(String UserName)
    {
        if(UserName==null||UserName=="")
        {
            throw new IllegalArgumentException("No Username was handed over");
        }
        if (AktuellerUser!=null&&(AktuellerUser.getRechte()==Rechte.ADMIN||AktuellerUser.getRechte()==Rechte.SUPER_ADMIN))
        {
            Fehlermeldungen fehlermeldungen = Listenanfang.UserLoeschen(UserName);
            schreiben();
            return fehlermeldungen;
        }
        return Fehlermeldungen.KEINE_RECHTE;
    }

    /**
     * Zum Rechteändern des Users
     * @param Username Username des Users
     * @param rechte Neue Rechte des Users
     * @return Fehlermeldungen
     */
    public Fehlermeldungen RechteAendern(String Username,Rechte rechte)
    {
        if(Username==null||Username=="")
        {
            throw new IllegalArgumentException("No Username was handed over");
        }
        if (AktuellerUser!=null&&(AktuellerUser.getRechte()==Rechte.ADMIN||AktuellerUser.getRechte()==Rechte.SUPER_ADMIN))
        {
            Fehlermeldungen fehlermeldungen = Listenanfang.RechteAendern(Username,rechte);
            schreiben();
            return fehlermeldungen;
        }
        return Fehlermeldungen.KEINE_RECHTE;
    }

    /**
     * Zum Ändern des Eigenen Passworts
     * @param AltesPasswort Altes Passwort
     * @param NeuesPasswort Neues Passwort
     * @return Fehlermeldungen
     */
    public Fehlermeldungen EigenesPasswortAendern(String AltesPasswort, String NeuesPasswort)
    {
        if(NeuesPasswort==null||NeuesPasswort=="")
        {
            throw new IllegalArgumentException("No new Password handed over");
        }
        if (AktuellerUser!=null&&AltesPasswort.hashCode()==AktuellerUser.getPasswort())
        {
            Fehlermeldungen fehlermeldungen = AktuellerUser.EigenesPasswortAendern(AktuellerUser.getUserID(),NeuesPasswort.hashCode());
            schreiben();
            return fehlermeldungen;
        }
        return Fehlermeldungen.PASSWORT_FALSCH;
    }

    /**
     * Zum Ändern eines Anders Passworts eines Anders Users
     * @param Username Username des Users
     * @param NeuesPasswort Neues Passwort des Users
     * @return Fehlermeldungen
     */
    public Fehlermeldungen AnderesPasswortAendern(String Username,String NeuesPasswort)
    {
        if(Username==null||Username==""||NeuesPasswort==null||NeuesPasswort=="")
        {
            throw new IllegalArgumentException("No Username/ Password was handed over");
        }
        if (AktuellerUser!=null&&(AktuellerUser.getRechte()==Rechte.ADMIN||AktuellerUser.getRechte()==Rechte.SUPER_ADMIN))
        {
            Fehlermeldungen fehlermeldungen = AktuellerUser.PasswortAendern(Username,NeuesPasswort.hashCode());
            schreiben();
            return fehlermeldungen;
        }
        return Fehlermeldungen.KEINE_RECHTE;
    }

    /**
     * Zur Suche eines Users nach UserID
     * @param UserName UserName der gesucht werden soll
     * @return UserID
     */
    public int SucheUserID(String UserName)
    {
        if(UserName==null||UserName=="")
        {
            throw new IllegalArgumentException("No Username was handed over");
        }
        return Listenanfang.SucheUserID(UserName);
    }

    /**
     * Schickt den Neuen inhalt der Liste an {@link UserDatenLesenSchreiben}
     */
    public void schreiben()
    {
        String[][] inhalt = new String[4][0];
        inhalt[0]=Listenanfang.AllUsername(0).clone();
        inhalt[1]=Listenanfang.AllPasswort(0).clone();
        inhalt[2]=Listenanfang.AllUserID(0).clone();
        inhalt[3]=Listenanfang.AllRechte(0).clone();
        daten.schreiben(inhalt);
    }

    /**
     * Füllt die Liste mit den Einträgen aus User.cvs
     * !!Nur einmal in der Klasse ausfüren lassen!!
     */
    private void fuellen()
    {
        String[][]inhalt=daten.lesen();
        String [] user = new String[inhalt.length];
        int [] passwort = new int[inhalt.length];
        int [] userID = new int[inhalt.length];
        Rechte [] rechte = new Rechte[inhalt.length];
        for(int i = 0; i<inhalt.length;i++)
        {
            user[i]=inhalt[i][0];
            passwort[i] = Integer.parseInt(inhalt[i][1]);
            userID[i]=Integer.parseInt(inhalt[i][2]);
            switch (inhalt[i][3])
            {
                case "VERKAEUFER":
                    rechte[i]=Rechte.VERKAEUFER;
                    break;
                case "EINKAEUFER":
                    rechte[i]=Rechte.EINKAEUFER;
                    break;
                case "ADMIN":
                    rechte[i]=Rechte.ADMIN;
                    break;
                case"SUPER_ADMIN":
                    rechte[i]=Rechte.SUPER_ADMIN;
                    break;
                default:
                    break;
            }
        }
        Listenanfang.UserInit(user,passwort,userID,rechte);
        int i=1+1;
        i++;
    }

    /**
     * Zum bekommen des UserMannagers
     * @return Usermannager
     */
    public static UserManager getUserManager(){
        if (userManager==null)
        {
            userManager=new UserManager();
            userManager.fuellen();
        }
        return userManager;
    }


    /**
     * Gibt Anzahl der Listen Elemente zurück
     * @return Anzahl Listenelemente
     */
    public int AnzahlListenElemente()
    {
        return Listenanfang.Anzahl();
    }

    /**
     * Setzt Listenanfang (Nur für Unterklassen von {@link User})
     * @param listenanfang Auf welchen Parameter soll Listenanfang gesetzt werden
     */
    void setListenanfang(User listenanfang) {
        Listenanfang = listenanfang;
    }
}
