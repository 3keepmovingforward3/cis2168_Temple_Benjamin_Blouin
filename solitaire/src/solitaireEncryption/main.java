package solitaireEncryption;

import java.io.IOException;

public class main {
    public static void main(String[] args)
            throws IOException {

        SolitaireEncryption ss = new SolitaireEncryption();
        ss.makeDeck();

        String message = "Hello";
        System.out.println("You entered: " + message);
        System.out.println("Encrypted message: " + ss.encrypt(message));
        //System.out.println("Decrypted message: " + ss.decrypt(message));
        System.out.println(ss.getKey());
    }
}
