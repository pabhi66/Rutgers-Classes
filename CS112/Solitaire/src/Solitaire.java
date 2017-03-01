import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.NoSuchElementException;

/**
* This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
*
* @author RU NB CS112
*/
public class Solitaire {

	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;

	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}

		// shuffle the cards
		Random randgen = new Random();
	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }

	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}

	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner)
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}

	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
        if(deckRear == null){
            return;
        }
        //**************************************
        CardNode cc = deckRear.next;
        do{
            System.out.print(cc.cardValue + "-->");
            cc = cc.next;
        }while(cc != deckRear.next);
        System.out.print("\n");
        //****************************************
        CardNode front = deckRear.next;
        CardNode prev = deckRear;
        do{
            //check if the last card is 27
            if(front.next == deckRear && front.next.cardValue == 27){
                CardNode rear = deckRear;
                front.next = deckRear.next;
                deckRear = front.next;
                CardNode frontNext = deckRear.next;
                deckRear.next = rear;
                rear.next = frontNext;

                //**************************************
                cc = deckRear.next;
                do{
                    System.out.print(cc.cardValue + "-->");
                    cc = cc.next;
                }while(cc != deckRear.next);
                //****************************************

                return;
            }
            //check if the second last card is 27
            else if(front.next == deckRear && front.cardValue == 27){
                CardNode n = deckRear.next;
                prev.next = front.next;
                deckRear.next = front;
                deckRear = front;
                front.next = n;
                //**************************************
                cc = deckRear.next;
                do{
                    System.out.print(cc.cardValue + "-->");
                    cc = cc.next;
                }while(cc != deckRear.next);
                //****************************************
                return;
            }
            //if any other card is 27
            else if(front.cardValue == 27){
                prev.next = front.next;
                front.next = prev.next.next;
                prev.next.next = front;
                //**************************************
                cc = deckRear.next;
                do{
                    System.out.print(cc.cardValue + "-->");
                    cc = cc.next;
                }while(cc != deckRear.next);
                //****************************************
                return;
            }
            front = front.next;
            prev = prev.next;
        }while(front != deckRear.next);
	}

	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
        if(deckRear == null){
            return;
        }
        //**************************************
        CardNode cc = deckRear.next;
        do{
            System.out.print(cc.cardValue + "-->");
            cc = cc.next;
        }while(cc != deckRear.next);
        System.out.print("\n");
        //****************************************
        CardNode temp = deckRear.next;
        CardNode prev = deckRear;
        CardNode first = deckRear.next;
        CardNode z = null;
        while(temp != deckRear){
            if(prev.next.next.next == deckRear){
                z = prev;
            }
            temp = temp.next;
            prev = prev.next;
        }

        //if second last card is 28
        if(prev.cardValue == 28){
            deckRear = prev;
            temp = deckRear.next;
            prev = deckRear;
            first = deckRear.next.next;
            while(temp != deckRear){
                temp = temp.next;
                prev = prev.next;
            }
            prev.next = temp.next;
            temp.next = first.next;
            first.next = temp;
            deckRear = first;
            //**************************************
            cc = deckRear.next;
            do{
                System.out.print(cc.cardValue + "-->");
                cc = cc.next;
            }while(cc != deckRear.next);
            //****************************************
            return;
        }
        //if third from last card is 28
        else if(z.next.cardValue == 28){
            CardNode zz = z.next;
            z.next = prev;
            zz.next = deckRear.next;
            deckRear.next = zz;
            deckRear = zz;
            //**************************************
            cc = deckRear.next;
            do{
                System.out.print(cc.cardValue + "-->");
                cc = cc.next;
            }while(cc != deckRear.next);
            //****************************************
            return;
        }
        //if last card is 28
        else if(temp.cardValue == 28){
            temp = deckRear.next;
            prev = deckRear;
            first = deckRear.next;
            while(temp != deckRear){
                temp = temp.next;
                prev = prev.next;
            }
            prev.next = first;
            temp.next = first.next.next;
            first.next.next = temp;
            deckRear = first;
            //**************************************
            cc = deckRear.next;
            do{
                System.out.print(cc.cardValue + "-->");
                cc = cc.next;
            }while(cc != deckRear.next);
            //****************************************
            return;
        }
        //if any other card is 28
        else{
            while(temp.cardValue != 28){
                prev = prev.next;
                temp = temp.next;
            }
            prev.next = temp.next;
            temp.next = temp.next.next.next;
            prev.next.next.next = temp;
            //**************************************
            cc = deckRear.next;
            do{
                System.out.print(cc.cardValue + "-->");
                cc = cc.next;
            }while(cc != deckRear.next);
            //****************************************
            return;
        }

	}

	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
        if(deckRear == null){
            return;
        }
        //**************************************
        CardNode cc = deckRear.next;
        do{
            System.out.print(cc.cardValue + "-->");
            cc = cc.next;
        }while(cc != deckRear.next);
        System.out.print("\n");
        //****************************************
        //if first card is 27 or 28
        if(deckRear.next.cardValue == 27 || deckRear.next.cardValue == 28){
            if(deckRear.cardValue == 27 || deckRear.cardValue == 28){
                //**************************************
                cc = deckRear.next;
                do{
                    System.out.print(cc.cardValue + "-->");
                    cc = cc.next;
                }while(cc != deckRear.next);
                //****************************************
                return;
            }
            CardNode n = deckRear.next.next;
            while(n != deckRear){
                if(n.cardValue == 27 || n.cardValue == 28){
                    break;
                }
                n = n.next;
            }
            deckRear = n;
            deckRear.next = n.next;
            //**************************************
            cc = deckRear.next;
            do{
                System.out.print(cc.cardValue + "-->");
                cc = cc.next;
            }while(cc != deckRear.next);
            //****************************************
            return;
        }
        //if last card is 27 or 28
        else if(deckRear.cardValue == 27 || deckRear.cardValue == 28){
            if(deckRear.next.cardValue == 27 || deckRear.next.cardValue == 28){
                return;
            }
            CardNode n = deckRear.next;
            while(n != deckRear){
                if(n.next.cardValue == 27 || n.next.cardValue == 28){
                    //**************************************
                    cc = deckRear.next;
                    do{
                        System.out.print(cc.cardValue + "-->");
                        cc = cc.next;
                    }while(cc != deckRear.next);
                    //****************************************
                    break;
                }
                n = n.next;
            }
            deckRear = n;
            deckRear.next = n.next;
            //**************************************
            cc = deckRear.next;
            do{
                System.out.print(cc.cardValue + "-->");
                cc = cc.next;
            }while(cc != deckRear.next);
            //****************************************
            return;
        }
        //search for 27 or 28 in the middle
        else{
            CardNode j1Prev = deckRear.next;
            CardNode j2 = deckRear.next;
            //looks for the first 27 or 28
            while(j1Prev != deckRear){
                if(j1Prev.next.cardValue == 27 || j1Prev.next.cardValue == 28){
                    break;
                }
                j1Prev = j1Prev.next;
            }
            j2 = j1Prev.next.next;
            //looks for the second 27 or 28
            while(j2 != deckRear){
                if(j2.cardValue == 27 || j2.cardValue == 28){
                    break;
                }
                j2 = j2.next;
            }
            CardNode first = deckRear.next;
            deckRear.next = j1Prev.next;
            j1Prev.next = j2.next;
            j2.next = first;
            deckRear = j1Prev;
            deckRear.next = j1Prev.next;
            //**************************************
            cc = deckRear.next;
            do{
                System.out.print(cc.cardValue + "-->");
                cc = cc.next;
            }while(cc != deckRear.next);
            //****************************************
            return;
        }
    }

	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {
        //set the counter = last card val
        if(deckRear == null){
            return;
        }
        //**************************************
        CardNode cc = deckRear.next;
        do{
            System.out.print(cc.cardValue + "-->");
            cc = cc.next;
        }while(cc != deckRear.next);
        System.out.print("\n");
        //****************************************
        int count = deckRear.cardValue;
        if(count == 28){
            count = 27;
        }
        CardNode front = deckRear.next;
        CardNode prev = deckRear;
        //to iterate through the number of cards/counts
        while (count != 1){
            front = front.next;
            count--;
        }
        prev = front;
        while(prev.next != deckRear){
            prev = prev.next;
        }
        prev.next = deckRear.next;
        deckRear.next = front.next;
        front.next = deckRear;
        //**************************************
        cc = deckRear.next;
        do{
            System.out.print(cc.cardValue + "-->");
            cc = cc.next;
        }while(cc != deckRear.next);
        //****************************************
	}

	/**
	 * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
	 * counts down based on the value of the first card and extracts the next card value
	 * as key. But if that value is 27 or 28, repeats the whole process (Joker A through Count Cut)
	 * on the latest (current) deck, until a value less than or equal to 26 is found, which is then returned.
	 *
	 * @return Key between 1 and 26
	 */
	int getKey() {
		// COMPLETE THIS METHOD
        if(deckRear == null){
            throw new NoSuchElementException();
        }
        CardNode temp;
        int key = 0;
        do {
            jokerA();
            jokerB();
            tripleCut();
            countCut();
            temp = deckRear;
            int count = temp.next.cardValue;
            if (count == 28) {
                count = 27;
            }
            while (count != 0) {
                temp = temp.next;
                count--;
            }
            key = temp.next.cardValue;
        }while (key >=27);
	    return key;
	}

	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 *
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) {
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
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
	public String encrypt(String message) {
        String ans = "" ;
        for(int i=0; i<message.length(); i++){
            Character ch = message.charAt(i);
            if(!(ch >= 'A' && ch <= 'Z')){//Character.isLetter(ch) && Character.isUpperCase(ch))){
                continue;
            }
            int alpha = ch - 'A'+ 1;
            int key = getKey() ;
            int total = alpha + key ;
            if( total > 26){
                total -= 26 ;
            }
            char CH = (char) (total + 64);

            ans += CH + "";
        }
	    return ans;
	}

	/**
	 * Decrypts a message, which consists of upper case letters only
	 *
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
        String ans = "";
        for(int i=0; i<message.length(); i++){
            Character ch = message.charAt(i);
            int alpha = ch - 'A' + 1;
            int key = getKey();
            if(alpha <= key){
                alpha += 26;
            }
            int total = alpha - key;
            char CH = (char) (total + 64);
            ans += CH + "";
        }
	    return ans;
	}
}
