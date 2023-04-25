package com.kpodlaska.encyptionDecryption.production;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShiftCryptoMethodTest {
    //Decryption methods tests below
    @Test
    public void analyzeShiftDecryption_singleCharKey1_DecodedSingleChar() {
        ShiftCryptoMethod shiftCryptoMethod = new ShiftCryptoMethod();
        String result = shiftCryptoMethod.decrypt("b",1);
        Assertions.assertEquals("a", result);
    }
    @Test
    public void analyzeShiftDecryption_singleACharKey10_DecodedSingleChar() {
        ShiftCryptoMethod shiftCryptoMethod = new ShiftCryptoMethod();
        String result = shiftCryptoMethod.decrypt("A",10);
        Assertions.assertEquals("Q", result);
    }
    @Test
    public void analyzeShiftDecryption_twoUpperCaseWordsKey10_twoDecodedUpperCaseSpaceSeparatedWords() {
        ShiftCryptoMethod shiftCryptoMethod = new ShiftCryptoMethod();
        String result = shiftCryptoMethod.decrypt("ALA KOWALSA",10);
        Assertions.assertEquals("QBQ AEMQBIQ", result);
    }
    @Test
    public void analyzeShiftDecryption_twoLowerCaseWordsAndPunktuationKey10_twoDecodedLowerCaseWordsSamePunktuation() {
        ShiftCryptoMethod shiftCryptoMethod = new ShiftCryptoMethod();
        String result = shiftCryptoMethod.decrypt("karolina_+!?/podlaska",10);
        Assertions.assertEquals("aqhebydq_+!?/fetbqiaq", result);
    }
    @Test
    public void analyzeShiftDecryption_zeroKey_noChange() {
        ShiftCryptoMethod shiftCryptoMethod = new ShiftCryptoMethod();
        String result = shiftCryptoMethod.decrypt("karolina",0);
        Assertions.assertEquals("karolina", result);
    }
    //Encryption methods tests below
    @Test
    public void analyzeShiftEncryption_zeroKey_noChange() {
        ShiftCryptoMethod shiftCryptoMethod = new ShiftCryptoMethod();
        String result = shiftCryptoMethod.encrypt("karolina",0);
        Assertions.assertEquals("karolina", result);

    }
    @Test
    public void analyzeShiftEncryption_oneCharKey5_encryptedChar() {
        ShiftCryptoMethod shiftCryptoMethod = new ShiftCryptoMethod();
        String result = shiftCryptoMethod.encrypt("a",5);
        Assertions.assertEquals("f", result);

    }
    @Test
    public void analyzeShiftEncryption_twoLowerCaseWordsKey1000_encryptedSpaceSeparatedWords() {
        ShiftCryptoMethod shiftCryptoMethod = new ShiftCryptoMethod();
        String result = shiftCryptoMethod.encrypt("karolina podlaska",1000);
        Assertions.assertEquals("wmdaxuzm bapxmewm", result);

    }
}