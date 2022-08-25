import java.security.SecureRandom;

public class Deck {
    int numOfCards = 0;
    Card deck;
    public static final String[] CARD_VALUES = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
    public static final String[] CARD_SUITS = new String[]{"H", "S", "D", "C"};
    Card head = null;

    // Request whether the player want to create a blank or standard deck
    public Deck(boolean emptyDeck) {
        if (emptyDeck) {
            return;
        } else
            generateStandardDeck();
    }

    private void generateStandardDeck() {
        for (int indexCard = 0; indexCard < CARD_VALUES.length; indexCard++) {
            for (int indexSuit = 0; indexSuit < CARD_SUITS.length; indexSuit++) {
                Card card = new Card(CARD_VALUES[indexCard], CARD_SUITS[indexSuit], null);
                addLast(card);
            }
        }
    }

    // Add card to the front of the deck
    private void addFront(Card newCard) {
        if (head == null) {
            head = newCard;
            System.out.println("1");
        } else {
            newCard.setLink(head);
            head = newCard;
        }
        numOfCards++;
    }

    // Add card ti the end of the deck
    private void addLast(Card newCard) {
        if (head == null) {
            head = newCard;
        } else {
            Card currentNode = head;
            while (currentNode.getLink() != null) {
                currentNode = currentNode.getLink();
            }
            currentNode.setLink(newCard);
        }
        numOfCards++;
    }

    // Remove a card from the deck at given position
    public Card removeAtIndex(int position) {
        if (head == null) {
            return null;
        }
        Card cursor = head;
        if (position == 0) {
            Card temp = head;
            head = head.getLink();
            temp.setLink(null);
            numOfCards--;
            return temp;
        }

        for (int i = 0; cursor != null && i < position - 1; i++) {
            cursor = cursor.getLink();
        }
        if (cursor == null || cursor.getLink() == null) {

            return null;
        }
        Card nextOfNext = cursor.getLink().getLink();
        Card removedNode = cursor.getLink();
        removedNode.setLink(null);
        cursor.setLink(nextOfNext);
        numOfCards--;
        return removedNode;
    }


    // Shuffle the deck by removing a card at random position then add it back to the front of the deck
    public void shuffleDeck() {
        for (int i = 0; i < 1000; i++) {
            SecureRandom secureRandom = new SecureRandom();
            int randomPosition = Math.abs(secureRandom.nextInt()) % numOfCards;

            Card removedCard = removeAtIndex(randomPosition);
            addLast(removedCard);
        }
    }

    public void printDeck() {
        /*CardNode currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.getCard());
            // System.out.println();

            currentNode = currentNode.getLink();
        }*/
        Card cursor = head;
        if (head == null) {
            return;
        }

        while (cursor != null) {

            System.out.println(cursor.printCard());
            cursor = cursor.getLink();
        }
    }

}
