package org.java.com.kpodlaska.contacts.production;

import java.util.Scanner;

class StandardInputHandler {

    private final Scanner in = new Scanner(System.in);

    public String getNextLine() {
        String input = in.nextLine();
        if (input != null) return input;
        else return "";
    }

    public char getNextChar() {
        String input = in.nextLine();
        if (input.length() != 1) return '\u0000';
        else return input.charAt(0);
    }
    public void closeInput() {
        this.in.close();
    }
}