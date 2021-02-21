package User;

import Hilfsmodule.Fehlermeldungen;

/**
 * Klasse UserKnoten Element der doppeltverketteten Liste
 * @author Alexander Asbeck
 * @version 2.0
 */
public class UserKnoten implements User{
    ;

    private User Nachfolger;
    private User Vorgaenger;
    private String Username;
    private int Passwort;
    private int UserID;
    private Rechte rechte;

    public UserKnoten(User nachfolger, User vorgaenger, String username, int passwort, int userid) {
        Nachfolger = nachfolger;
        Vorgaenger = vorgaenger;
        Username = username;
        Passwort = passwort;
        UserID = userid;
        rechte = Rechte.VERKAEUFER;
        vorgaenger.setNachfolger(this);
        if (Vorgaenger==null)
        {
            UserManager.getUserManager().setListenanfang(this);
        }
    }

    public UserKnoten(User nachfolger, User vorgaenger, String username, int passwort, int userid, Rechte rechte) {
        Nachfolger = nachfolger;
        Vorgaenger = vorgaenger;
        Username = username;
        Passwort = passwort;
        UserID = userid;
        this.rechte = rechte;
        if (Vorgaenger==null)
        {
            UserManager.getUserManager().setListenanfang(this);
        }else{
            Vorgaenger.setNachfolger(this);
        }
    }

    @Override
    public void UserInit(String[] user, int[] passwort, int[] userID, Rechte[] rechte) {
        Nachfolger.UserInit(user,passwort,userID,rechte);
    }

    @Override
    public UserKnoten SucheUser(String Username) {
        if(this.Username.equalsIgnoreCase(Username))
        {
            return this;
        }else {
            return Nachfolger.SucheUser(Username);
        }
    }

    @Override
    public Fehlermeldungen UserEinfuegen(String Username, int Passwort) {
        if(!(this.Username.equalsIgnoreCase(Username)))
        {
            return Nachfolger.UserEinfuegen(Username, Passwort);
        }else {
            return Fehlermeldungen.USER_EXISTIERT_BEREITS;
        }
    }

    public Fehlermeldungen EigenesPasswortAendern(int UserID, int Passwort)
    {
        if (this.UserID==UserID)
        {
            this.Passwort=Passwort;
            return Fehlermeldungen.ERFOLGREICH;
        }else {
            return Nachfolger.EigenesPasswortAendern(UserID,Passwort);
        }
    }

    @Override
    public Fehlermeldungen PasswortAendern(String Username, int Passwort) {
        if(this.Username.equalsIgnoreCase(Username))
        {
            if (rechte!=Rechte.SUPER_ADMIN)
            {
                this.Passwort=Passwort;
                return Fehlermeldungen.ERFOLGREICH;
            }else {
                return Fehlermeldungen.KEINE_RECHTE;
            }

        }else{
            return Nachfolger.PasswortAendern(Username,Passwort);
        }

    }

    @Override
    public Fehlermeldungen UserLoeschen(String Username) {
        if (this.Username.equalsIgnoreCase(Username))
        {
            if (rechte!=Rechte.SUPER_ADMIN)
            {
                Vorgaenger.setNachfolger(Nachfolger);
                Nachfolger.setVorgaenger(Vorgaenger);
                return Fehlermeldungen.ERFOLGREICH;
            }else {
                return Fehlermeldungen.KEINE_RECHTE;
            }
        }else {
           return Nachfolger.UserLoeschen(Username);
        }
    }

    @Override
    public Fehlermeldungen RechteAendern(String Username, Rechte rechte) {
        if (this.Username.equalsIgnoreCase(Username))
        {
            if(this.rechte!=Rechte.SUPER_ADMIN&&rechte!=Rechte.SUPER_ADMIN)
            {
                this.rechte = rechte;
                return Fehlermeldungen.ERFOLGREICH;
            }else {
                return Fehlermeldungen.KEINE_RECHTE;
            }
        }
        else {
            return Nachfolger.RechteAendern(Username,rechte);
        }
    }

    @Override
    public int Anzahl() {
        return Nachfolger.Anzahl()+1;
    }

    @Override
    public int SucheUserID(String Username) {
        if(this.Username.equalsIgnoreCase(Username))
        {
            return UserID;
        }else {
            return Nachfolger.SucheUserID(Username);
        }
    }

    @Override
    public String[] AllUsername(int nummer) {
        String[] inhalt =Nachfolger.AllUsername(nummer+1);
        inhalt[nummer]=Username;
        return inhalt;
    }

    @Override
    public String[] AllPasswort(int nummer) {
        String[] inhalt =Nachfolger.AllPasswort(nummer+1);
        inhalt[nummer]=String.valueOf(Passwort);
        return inhalt;
    }

    @Override
    public String[] AllUserID(int nummer) {
        String[] inhalt =Nachfolger.AllUserID(nummer+1);
        inhalt[nummer]=String.valueOf(UserID);
        return inhalt;
    }

    @Override
    public String[] AllRechte(int nummer) {
        String[] inhalt =Nachfolger.AllRechte(nummer+1);
        inhalt[nummer]=rechte.toString();
        return inhalt;
    }


    public int getUserID() {
        return UserID;
    }

    public void setNachfolger(User nachfolger) {
        Nachfolger = nachfolger;
    }

    public void setVorgaenger(User vorgaenger) {
        Vorgaenger = vorgaenger;
    }

    public int getPasswort() {
        return Passwort;
    }

    public Rechte getRechte() {
        return rechte;
    }
}
