import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void printCardFrom2To10() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomNumber = random.nextInt(2, 11);
            Card card1 = new Card(String.valueOf(randomNumber), "H", null);
            assertEquals(randomNumber + "- H", card1.printCard());
        }
    }

    @Test
    void printCardAce() {
        Card card1 = new Card("1", "H", null);
        assertEquals("A- H", card1.printCard());
    }

    @Test
    void printCardFrom11to13() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomNumber = random.nextInt(11, 14);
            Card card1 = new Card(String.valueOf(randomNumber), "H", null);
            if (randomNumber == 11) {
                assertEquals("J- H", card1.printCard());
            } else if (randomNumber == 12) {
                assertEquals("Q- H", card1.printCard());
            } else {
                assertEquals("K- H", card1.printCard());
            }
        }
    }

    @Test
    void setCard() {
    }

    @Test
    void getLink() {
    }

    @Test
    void setLink() {
    }

    @Test
    void getCardValueFrom1to10() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomNumber = random.nextInt(1, 11);
            Card card1 = new Card(String.valueOf(randomNumber), "H", null);
            assertEquals(randomNumber, card1.getCardValue());
        }
    }

    @Test
    void getCardValueFrom11To13() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomNumber = random.nextInt(11, 14);
            Card card1 = new Card(String.valueOf(randomNumber), "H", null);
            assertEquals(10, card1.getCardValue());
        }
    }

    @Test
    void compareTo() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomNumber1 = random.nextInt();
            int randomNumber2 = random.nextInt();

            Card card1 = new Card(String.valueOf(randomNumber1), "H", null);
            Card card2 = new Card(String.valueOf(randomNumber2), "H", null);
            if (randomNumber1 < randomNumber2) {
                assertEquals(card1.compareTo(card2), -1);
            } else if (randomNumber1 > randomNumber2) {
                assertEquals(card1.compareTo(card2), 1);
            } else {
                assertEquals(card1.compareTo(card2), 0);
            }
        }
    }
}