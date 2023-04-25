package org.java.com.kpodlaska.encyptionDecryption.production;


public interface CryptoMethod {

    String encrypt(String message, int key);

    String decrypt(String message, int key);
}
