package Hilfsmodule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EANCheckTest {

    EANCheck eanCheck;

    @BeforeEach
    void setupEANCheck(){
        eanCheck = new EANCheck();
        EANCheck.setPZCheckEnabled(true); //Diese Eigenschaft schaltet die Prüfziffernprüfung an, welche ich während des Codens nicht brauchen konnte
    }

    @Test
    void testEANChecker(){
        assertEquals(Fehlermeldungen.EAN_CHECK_ERFOLGREICH, eanCheck.checkEANCode("0000000000000")); //Mit 13 Zeichen
        assertEquals(Fehlermeldungen.EAN_ZU_KURZ, eanCheck.checkEANCode("00000000000")); // Mit < 13 Zeichen
        assertEquals(Fehlermeldungen.EAN_ZU_LANG, eanCheck.checkEANCode("00000000000000000000")); //Mit > 13 Zeichen
        assertEquals(Fehlermeldungen.EAN_MIT_ZEICHEN, eanCheck.checkEANCode("000000000000A")); //EAN mit Buchstabe
        assertEquals(Fehlermeldungen.EAN_PZ_FALSCH, eanCheck.checkEANCode("0000000000001")); //Falsche Prüfziffer
    }


}