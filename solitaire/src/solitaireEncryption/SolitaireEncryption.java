package solitaireEncryption;

import java.util.Random;

/**
 * This class is a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 *
 * @author Benjamin Blouin
 */
class SolitaireEncryption {

    /**
     * Circular linked list, the deck of cards for encryption
     */
    cardNode cardDeckRearPtr;

    /**
     * Makes a shuffled deck of cards for encryption. The deck is stored in circular
     * linked list, whose last node is pointed to by the field deckRear
     */
    public void makeDeck() {
        // start with an array of 1-28
        int[] cardValues = new int[28];
        // assign values from 1 to 28
        for (int i=0; i < cardValues.length; i++) {
            cardValues[i] = i + 1;
        }

        // shuffle cards
        Random r = new Random();
        for (int i = 0; i < cardValues.length; i++) {
            int other = r.nextInt(28);
            int temp = cardValues[i];
            cardValues[i] = cardValues[other];
            cardValues[other] = temp;
        }

        // create a circular linked list from this deck and make deckRear point to its last node
        cardNode p = new cardNode();
        p.cardValue = cardValues[0];
        p.next = p;
        cardDeckRearPtr = p;
        for (int i=1; i < cardValues.length; i++) {
            p = new cardNode();
            p.cardValue = cardValues[i];
            p.next = cardDeckRearPtr.next;
            cardDeckRearPtr.next = p;
            cardDeckRearPtr = p;
        }
    }

    /**
     * Implements Step 1 - Joker A - on the deck.
     */
    void jokerA() {
        int target = 27;
        cardNode finder = cardDeckRearPtr.next;
        cardNode prev = cardDeckRearPtr;
        do{
            if(finder.cardValue != target){
                prev = finder;
                finder=  finder.next;

            }
            else{
                finder.cardValue = finder.next.cardValue;
                finder.next.cardValue = target;
                prev = finder.next;
                finder = finder.next.next;
            }
        }while(prev != cardDeckRearPtr);
    }

    /**
     * Implements Step 2 - Joker B - on the deck.
     */
    void jokerB() {
        int target = 28;
        cardNode finder = cardDeckRearPtr.next, prev = cardDeckRearPtr;
        do{
            if(finder.cardValue != target){
                prev = finder;
                finder =finder.next;
            }
            else{
                int temp1 = finder.next.cardValue;
                int temp2 = finder.next.next.cardValue;
                finder.cardValue = temp1;
                finder.next.cardValue = temp2;
                finder.next.next.cardValue = target;
                prev = finder.next.next;
                finder = finder.next.next.next;
            }
        }while(prev != cardDeckRearPtr);
    }

    /**
     * Implements Step 3 - Triple Cut - on the deck.
     */
    void tripleCut() {
        int t1 = 27, t2 = 28;
        cardNode prev = cardDeckRearPtr, curr = cardDeckRearPtr.next;
        cardNode head1 = cardDeckRearPtr.next, head2 = null;
        cardNode tail1 = null, tail2 = cardDeckRearPtr, target1 = null, target2 = null;
        do{
            if(curr.cardValue != t1 && curr.cardValue != t2){
                prev = curr;
                curr = curr.next;
            }
            else{
                if(tail1 == null){
                    tail1 = prev;
                    target1 = curr;
                    prev = curr;
                    curr = curr.next;
                }
                else{
                    target2 = curr;
                    head2 = curr.next;
                    prev = curr;
                    curr = curr.next;
                }
            }
        }while(prev != cardDeckRearPtr);
        if(cardDeckRearPtr.next == target1){
            cardDeckRearPtr = target2;
        }
        else if(cardDeckRearPtr == target2){
            cardDeckRearPtr = tail1;
        }
        else{
            cardDeckRearPtr = tail1;
            tail1.next = head2;
            target2.next = head1;
            tail2.next = target1;
        }
    }

    /**
     * Implements Step 4 - Count Cut - on the deck.
     */
    void countCut() {
        int count = cardDeckRearPtr.cardValue;
        cardNode a = cardDeckRearPtr.next, b, c, d;
        cardNode prev = cardDeckRearPtr, curr = cardDeckRearPtr.next;
        for(int i = 0; i<count; i++){
            prev = curr;
            curr = curr.next;
        }
        d = prev;
        b = curr;
        while(curr != cardDeckRearPtr){
            prev = curr;
            curr = curr.next;
        }
        c = prev;
        if(count == 27 || count == 28){
            return;
        }
        else{
            d.next= cardDeckRearPtr;
            cardDeckRearPtr.next=b;
            c.next=a;
        }
    }

    /**
     * Gets a key. 
     * Calls the four step encryption process:
     * jokerA, jokerB, tripleCut, countCut
     * then counts down based on the value of the first card,
     * and extracts the next card value as key. 
     * But, if that value is 27 or 28, encryption repeats the whole process (Joker A through Count Cut)
     * on the latest (current) deck, 
     * until a value less than or equal to 26 is found, 
     * which is then returned.
     *
     * @return Key between 1 and 26
     */
    public int getKey() {
        int countVal = cardDeckRearPtr.next.cardValue;
        int key;
        if(countVal == 27 || countVal == 28){
            key = cardDeckRearPtr.cardValue;
        }
        else{
            cardNode prev = cardDeckRearPtr, curr = cardDeckRearPtr.next;
            for(int i = 0; i<countVal; i++){
                prev = curr;
                curr = curr.next;
            }
            key = curr.cardValue;
        }
        System.out.println("cardValue: "+ cardDeckRearPtr.cardValue+" key: "+ key);
        return key;
    }

    /**
     * Utility method that prints a circular linked list, given its rear pointer
     *
     * @param rear Rear pointer
     */
    static void printList(cardNode rear) {
        if (rear == null) {
            return;
        }
        System.out.print(rear.next.cardValue);
        cardNode ptr = rear.next;
        do {
            ptr = ptr.next;
            System.out.print("," + ptr.cardValue);
        } while (ptr != rear);
        System.out.println("\n");
    }

    /**
     * Encrypts a message, ignores all characters except upper case letters
     *
     * @param message Message to be encrypted
     * @return Encrypted message, a sequence of upper case letters only
     */

    String encrypt(String message) {
        StringBuilder encrypt = new StringBuilder();
        for(int i = 0; i < message.length(); i++){
            char x = message.charAt(i);
            if(Character.isLetter(x)){
                int pos = x-'A'+1;
                this.jokerA();
                this.jokerB();
                this.tripleCut();
                this.countCut();
                int key = this.getKey();
                int sum = key+pos>26 ? (key+pos)-26 : key+pos;
                char y = (char)(sum-1+'A');
                encrypt.append(y);
            }
        }
        return encrypt.toString();
    }

    /**
     * Decrypts a message, which consists of upper case letters only
     *
     * @param message Message to be decrypted
     * @return Decrypted message, a sequence of upper case letters only
     */

    String decrypt(String message) {
        StringBuilder decrypt= new StringBuilder();
        for(int i = 0; i < message.length(); i++){
            char a = message.charAt(i);
            int code = a-'A'+1;
            this.jokerA();
            this.jokerB();
            this.tripleCut();
            this.countCut();
            int key = this.getKey();
            int diff=code>key ? code-key : (code+26)-key;
            char b = (char)(diff-1+'A');
            decrypt.append(b);
        }
        return decrypt.toString();
    }
}
