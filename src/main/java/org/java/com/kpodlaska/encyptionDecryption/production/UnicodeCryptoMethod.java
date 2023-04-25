package org.java.com.kpodlaska.encyptionDecryption.production;

public class UnicodeCryptoMethod implements CryptoMethod {
    @Override
    public String encrypt(String message, int key) {
        char[] result = new char[message.length()];

        for (int i = 0; i < message.length(); i++) {
            result[i] = (char) (message.charAt(i) + key);
        }
        return new String(result);
    }

    @Override
    public String decrypt(String message, int key) {
        return encrypt(message, key * -1);
    }
}
