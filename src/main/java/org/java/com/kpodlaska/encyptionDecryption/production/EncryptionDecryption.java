package org.java.com.kpodlaska.encyptionDecryption.production;



public class EncryptionDecryption {
    public static void main(String[] args) {
        // Initialize CipherMachine with default options
        CryptoMachine cryptoMachine = new CryptoMachine();

        // Parse args
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-mode":
                    cryptoMachine.setMode(args[i + 1]);
                    break;
                case "-key":
                    cryptoMachine.setKey(Integer.parseInt(args[i + 1]));
                    break;
                case "-data":
                    cryptoMachine.setMessage(args[i + 1]);
                    break;
                case "-in":
                    cryptoMachine.readMessageFile(args[i + 1]);
                    break;
                case "-out":
                    cryptoMachine.setOutputPath(args[i + 1]);
                    break;
                case "-alg":
                    cryptoMachine.setCipherMethod(args[i + 1]);
                    break;
            }
        }

        // Start the magic
        cryptoMachine.run();
        cryptoMachine.outputResult();
    }
}
