package org.java.com.kpodlaska.encyptionDecryption.production;

public class ShiftCryptoMethod implements CryptoMethod {
    @Override
    public String encrypt(String message, int key) {
        char[] result = new char[message.length()];

        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            if (currentChar >= 'a' && currentChar <= 'z') {
                int originalAlphabetPosition = currentChar - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + key) % 26;
                result[i] = (char) ('a' + newAlphabetPosition);
            } else if (currentChar >= 'A' && currentChar <= 'Z') {
                int originalAlphabetPosition = currentChar - 'A';
                int newAlphabetPosition = (originalAlphabetPosition + key) % 26;
                result[i] = (char) ('A' + newAlphabetPosition);
            } else {
                result[i] = currentChar;
            }
        }
        return new String(result);
    }

    @Override
    public String decrypt(String message, int key) {
        return encrypt(message, 26 - (key % 26));//using encrypted method with -key switch
    }
}
