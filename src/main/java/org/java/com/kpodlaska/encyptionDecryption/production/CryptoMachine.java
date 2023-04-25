package org.java.com.kpodlaska.encyptionDecryption.production;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CryptoMachine {
    private CryptoMethod cryptoMethod;
    private String message;
    private int key;
    private String mode;
    private String outputPath;

    public int getKey() {
        return key;
    }

    public String getMode() {
        return mode;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getMessage() {
        return message;
    }

    public CryptoMethod getCryptoMethod() {
        return cryptoMethod;
    }

    public CryptoMachine() {
        this.message = "";
        this.mode = "enc";
        this.key = 0;
        this.outputPath = "";
        this.cryptoMethod = new ShiftCryptoMethod();
    }
    public Object setCipherMethod(String cipherMethod) {
        if ("shift".equals(cipherMethod)) {
            this.cryptoMethod = new ShiftCryptoMethod();
        } else if ("unicode".equals(cipherMethod)) {
            this.cryptoMethod = new UnicodeCryptoMethod();
        } else {
            throw new IllegalArgumentException("Invalid -alg parameter provided");
        }
        return null;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public void readMessageFile(String path) {
        if ("".equals(message)) {
            try {
                message = new String(Files.readAllBytes(Paths.get(path)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String encrypt(String message, int key) {
        return this.cryptoMethod.encrypt(message, key);
    }

    public String decrypt(String message, int key) {
        return this.cryptoMethod.decrypt(message, key);
    }

    public void run() {
        if ("enc".equals(this.mode)) {
            this.message = this.encrypt(this.message, this.key);
        } else if ("dec".equals(this.mode)) {
            this.message = this.decrypt(this.message, this.key);
        }
    }

    public void outputResult() {
        if ("".equals(this.outputPath)) {
            System.out.println(message);
        } else {
            try (FileWriter fileWriter = new FileWriter(this.outputPath)) {
                fileWriter.write(this.message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
