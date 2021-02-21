package User;

import Hilfsmodule.Fehlermeldungen;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {


  @AfterEach
    public void finalize()
    {
        UserManager.getUserManager().Logout();
    }

    @Test
    void login(){
        UserManager u= UserManager.getUserManager();
        assertEquals(Rechte.SUPER_ADMIN,u.Login("root","123"));
        u.Logout();
        assertEquals(Rechte.ADMIN,u.Login("Alexander","123"));
        u.Logout();
        assertEquals(Rechte.VERKAEUFER,u.Login("Man","123"));
        u.Logout();
        assertEquals(Rechte.NO_LOGIN_PERMISSION,u.Login("test","123"));
    }

    @Test
    void logout() {
        UserManager.getUserManager().Login("root","123");
        assertEquals(Fehlermeldungen.ERFOLGREICH,UserManager.getUserManager().Logout());
    }

    @Test
    void userEinfuegen() {
        UserManager u =UserManager.getUserManager();
        u.Login("root","123");
        assertEquals(u.UserEinfuegen("testuser2","123"),Fehlermeldungen.ERFOLGREICH);
        assertEquals(u.UserEinfuegen("testuser2","123"),Fehlermeldungen.USER_EXISTIERT_BEREITS);
        u.UserLoeschen("testuser2");
    }

    @Test
    void userLoeschen() {
        UserManager u=UserManager.getUserManager();
        u.Login("root","123");
        u.UserEinfuegen("testuser2","123");
        assertEquals(u.UserLoeschen("testuser2"),Fehlermeldungen.ERFOLGREICH);
        assertEquals(u.UserLoeschen("testuser2"),Fehlermeldungen.USER_NICHT_GEFUNDEN);
    }

    @Test
    void rechteAendern() {
        UserManager u=UserManager.getUserManager();
        u.Login("root","123");
        u.UserEinfuegen("testuser2","123");
        assertEquals(u.RechteAendern("testuser2",Rechte.EINKAEUFER),Fehlermeldungen.ERFOLGREICH);
        assertEquals(u.RechteAendern("root",Rechte.EINKAEUFER),Fehlermeldungen.KEINE_RECHTE);
        u.UserLoeschen("testuser2");
    }

    @Test
    void eigenesPasswortAendern() {
        UserManager u=UserManager.getUserManager();
        u.Login("testuser","123");
        assertEquals(u.EigenesPasswortAendern("123","456"),Fehlermeldungen.ERFOLGREICH);
        assertEquals(u.EigenesPasswortAendern("123","456"),Fehlermeldungen.PASSWORT_FALSCH);
        u.EigenesPasswortAendern("456","123");
    }

    @Test
    void anderesPasswortAendern() {
        UserManager u = UserManager.getUserManager();
        u.Login("root","123");
        assertEquals( u.AnderesPasswortAendern("Testuser","123"),Fehlermeldungen.ERFOLGREICH);
        assertEquals( u.AnderesPasswortAendern("Testuser2","123"),Fehlermeldungen.USER_NICHT_GEFUNDEN);
        u.Logout();
        u.Login("Man","123");
        assertEquals( u.AnderesPasswortAendern("Testuser","123"),Fehlermeldungen.KEINE_RECHTE);
    }

    @Test
    void sucheUserID() {
        UserManager u = UserManager.getUserManager();
        u.Login("root","123");
        assertEquals(u.SucheUserID("Testuser"),4);
        assertEquals(u.SucheUserID("Testuser2"),Integer.MIN_VALUE);
    }
}