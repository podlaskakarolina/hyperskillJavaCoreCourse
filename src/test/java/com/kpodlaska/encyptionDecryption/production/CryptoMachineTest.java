package com.kpodlaska.encyptionDecryption.production;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CryptoMachineTest {


    @Test
    void setMessage() {
        //given
        CryptoMachine cryptoMachine = new CryptoMachine();
        String string = "Hello";
        //when
        cryptoMachine.setMessage(string);
        String result = cryptoMachine.getMessage();
        //then
        assertEquals("Hello", result);
    }

    @Test
    void setKey() {
        //given
        int secretKey = 13;
        CryptoMachine cryptoMachine = new CryptoMachine();
        //when
        cryptoMachine.setKey(secretKey);
        int result = cryptoMachine.getKey();
        //then
        assertEquals(13, result);

    }

    @Test
    void setMode() {
        //given
        CryptoMachine cryptoMachine = new CryptoMachine();
        String chosenMode = "enc";

        //when
        cryptoMachine.setMode(chosenMode);
        String result = cryptoMachine.getMode();
        //then
        assertEquals("enc", result);

    }

    @Test
    void setOutputPath() {
        //given
        CryptoMachine cryptoMachine = new CryptoMachine();
        String givenPath = "Desktop/kpodlaska/";
        //when
        cryptoMachine.setOutputPath(givenPath);
        String result = cryptoMachine.getOutputPath();
        //then
        assertEquals("Desktop/kpodlaska/", result);
    }

    @Test
    void readMessageFile_emptyMessageAndCorrectPath_fileContent() {
        CryptoMachine machine = new CryptoMachine();
        Path path = Paths.get("src/test/resources/test.txt");

        machine.readMessageFile(path.toString());

        assertEquals(machine.getMessage(), """
                Poland
                Warsaw
                0
                Kot
                wlochaty dran
                0
                """);
    }

    @Test
    void readMessageFile_wrongPath_throwIOException() {
        CryptoMachine machine = new CryptoMachine();

        Path path = Paths.get("this/path/dont/exists/test.txt");

        assertThrows(RuntimeException.class, () -> machine.readMessageFile(path.toString()));
    }

    @Test
    void setCipherMethod_shift_shiftCryptoMethod() {
        CryptoMachine machine = new CryptoMachine();

        machine.setCipherMethod("shift");

        assertEquals(machine.getCryptoMethod().getClass().getSimpleName(),
                "ShiftCryptoMethod");
    }

    @Test
    void setCipherMethod_unicode_unicodeCryptoMethod() {
        CryptoMachine machine = new CryptoMachine();

        machine.setCipherMethod("unicode");

        assertEquals(machine.getCryptoMethod().getClass().getSimpleName(),
                "UnicodeCryptoMethod");
    }
    @Test
    void encrypt_singleWordMessageAndKey5() {
        CryptoMachine machine = new CryptoMachine();
        String result = machine.encrypt("hello",5);
        assertEquals("mjqqt",result);
    }
    @Test
    void decrypt_singleWordMessageAndKey10() {
        CryptoMachine machine = new CryptoMachine();
        String result = machine.decrypt("hello",10);
        assertEquals("xubbe",result);
    }
/*
    public String encrypt(String message, int key) {
        return this.cryptoMethod.encrypt(message, key);
    }*/
}


