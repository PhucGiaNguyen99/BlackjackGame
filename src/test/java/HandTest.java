import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void getStatus() {
    }

    @Test
    void isBusted() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomNum1 = random.nextInt(14);
            int randomNum2 = random.nextInt(14);
            int randomNum3 = random.nextInt(14);
            int randomNum4 = random.nextInt(14);

            Card card1 = new Card(String.valueOf(randomNum1), "H", null);
            Card card2 = new Card(String.valueOf(randomNum2), "H", null);
            Card card3 = new Card(String.valueOf(randomNum3), "H", null);
            Card card4 = new Card(String.valueOf(randomNum4), "H", null);

            Hand hand1 = new Hand();
            hand1.addCardToHand(card1);
            hand1.addCardToHand(card2);
            hand1.addCardToHand(card3);
            hand1.addCardToHand(card4);

            int sumHand = card1.getCardValue() + card2.getCardValue() + card3.getCardValue() + card4.getCardValue();

            if (sumHand > 21) {
                assertEquals(true, hand1.isBusted());
            } else {
                assertEquals(false, hand1.isBusted());
            }
        }
    }

    @Test
    void isBlackJack() {
        Card card3 = new Card("13", "H", null);
        Card card4 = new Card("1", "H", null);

        Hand hand1 = new Hand();
        hand1.addCardToHand(card3);
        hand1.addCardToHand(card4);
        assertEquals(true, hand1.isBlackJack());
    }

    @Test
    void calculateTotalPointWithNoAce() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomNum1 = random.nextInt(2, 14);
            int randomNum2 = random.nextInt(2, 14);
            int randomNum3 = random.nextInt(2, 14);
            int randomNum4 = random.nextInt(2, 14);

            Card card1 = new Card(String.valueOf(randomNum1), "H", null);
            Card card2 = new Card(String.valueOf(randomNum2), "H", null);
            Card card3 = new Card(String.valueOf(randomNum3), "H", null);
            Card card4 = new Card(String.valueOf(randomNum4), "H", null);

            Hand hand1 = new Hand();
            hand1.addCardToHand(card1);
            hand1.addCardToHand(card2);
            hand1.addCardToHand(card3);
            hand1.addCardToHand(card4);

            int sumHand = card1.getCardValue() + card2.getCardValue() + card3.getCardValue() + card4.getCardValue();

            assertEquals(card1.getCardValue() + card2.getCardValue() + card3.getCardValue() + card4.getCardValue(), hand1.calculateTotalPoint());
        }
    }

    @Test
    void calculateTotalPointWithOneAce() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomNum1 = random.nextInt(2, 14);
            int randomNum2 = random.nextInt(2, 14);
            int randomNum3 = random.nextInt(2, 14);
            //int randomNum4 = random.nextInt(2, 14);

            Card card1 = new Card(String.valueOf(randomNum1), "H", null);
            Card card2 = new Card(String.valueOf(randomNum2), "H", null);
            Card card3 = new Card(String.valueOf(randomNum3), "H", null);
            Card card4 = new Card("1", "H", null);

            Hand hand1 = new Hand();
            hand1.addCardToHand(card1);
            hand1.addCardToHand(card2);
            hand1.addCardToHand(card3);
            hand1.addCardToHand(card4);

            int sumHand = card1.getCardValue() + card2.getCardValue() + card3.getCardValue() + card4.getCardValue();
            System.out.println("Number 1: " + randomNum1);
            System.out.println("Number 2: " + randomNum2);
            System.out.println("Number 3: " + randomNum3);


            if (card1.getCardValue() + card2.getCardValue() + card3.getCardValue() + 11 > 21) {
                assertEquals(card1.getCardValue() + card2.getCardValue() + card3.getCardValue() + 1, hand1.calculateTotalPoint());
            } else {
                assertEquals(card1.getCardValue() + card2.getCardValue() + card3.getCardValue() + 11, hand1.calculateTotalPoint());
            }
        }
    }

    @Test
    void calculateTotalPointWithTwoAce() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomNum1 = random.nextInt(2, 14);
            int randomNum2 = random.nextInt(2, 14);
            //int randomNum3 = random.nextInt(2, 14);
            //int randomNum4 = random.nextInt(2, 14);

            Card card1 = new Card(String.valueOf(randomNum1), "H", null);
            Card card2 = new Card(String.valueOf(randomNum2), "H", null);
            Card card3 = new Card("1", "H", null);
            Card card4 = new Card("1", "H", null);

            Hand hand1 = new Hand();
            hand1.addCardToHand(card1);
            hand1.addCardToHand(card2);
            hand1.addCardToHand(card3);
            hand1.addCardToHand(card4);

            int sumHand = card1.getCardValue() + card2.getCardValue() + card3.getCardValue() + card4.getCardValue();
            System.out.println("Number 1: " + randomNum1);
            System.out.println("Number 2: " + randomNum2);
            //System.out.println("Number 3: " + randomNum3);


            if (card1.getCardValue() + card2.getCardValue() + 11 + 1 > 21) {
                assertEquals(card1.getCardValue() + card2.getCardValue() + 2, hand1.calculateTotalPoint());
            } else {
                assertEquals(card1.getCardValue() + card2.getCardValue() + 12, hand1.calculateTotalPoint());
            }
        }
    }

    @Test
    void presentCardHand() {
    }

    @Test
    void addCardToHand() {
    }

    @Test
    void compareToWhenOneIsBustedOneIsNot() {
        Card card1 = new Card("3", "H", null);
        Card card2 = new Card("5", "H", null);
        Card card3 = new Card("9", "H", null);
        Card card4 = new Card("1", "H", null);

        Hand hand1 = new Hand();
        hand1.addCardToHand(card1);
        hand1.addCardToHand(card2);
        hand1.addCardToHand(card3);
        hand1.addCardToHand(card4);

        Card card5 = new Card("5", "H", null);
        Card card6 = new Card("4", "H", null);
        Card card7 = new Card("7", "H", null);
        Card card8 = new Card("9", "H", null);

        Hand hand2 = new Hand();
        hand2.addCardToHand(card5);
        hand2.addCardToHand(card6);
        hand2.addCardToHand(card7);
        hand2.addCardToHand(card8);
        assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    void compareToWhenBothAreBusted() {

        Card card1 = new Card("2", "H", null);
        Card card2 = new Card("5", "H", null);
        Card card3 = new Card("8", "H", null);
        Card card4 = new Card("9", "H", null);

        Hand hand1 = new Hand();
        hand1.addCardToHand(card1);
        hand1.addCardToHand(card2);
        hand1.addCardToHand(card3);
        hand1.addCardToHand(card4);

        Card card5 = new Card("5", "H", null);
        Card card6 = new Card("4", "H", null);
        Card card7 = new Card("7", "H", null);
        Card card8 = new Card("6", "H", null);

        Hand hand2 = new Hand();
        hand2.addCardToHand(card5);
        hand2.addCardToHand(card6);
        hand2.addCardToHand(card7);
        hand2.addCardToHand(card8);
        assertEquals(0, hand1.compareTo(hand2));
    }

    @Test
    void compareToWhenBothHaveBJ() {
        Hand hand1 = new Hand();
        hand1.addCardToHand(new Card("1", "H", null));
        hand1.addCardToHand(new Card("13", "H", null));

        Hand hand2 = new Hand();
        hand2.addCardToHand(new Card("1", "H", null));
        hand2.addCardToHand(new Card("11", "H", null));
        assertEquals(0, hand1.compareTo(hand2));
    }
    @Test
    void compareToWhenOneHasBJ() {
        Hand hand1 = new Hand();
        hand1.addCardToHand(new Card("1", "H", null));
        hand1.addCardToHand(new Card("13", "H", null));

        Card card5 = new Card("5", "H", null);
        Card card6 = new Card("4", "H", null);
        Card card7 = new Card("7", "H", null);
        Card card8 = new Card("5", "H", null);

        Hand hand2 = new Hand();
        hand2.addCardToHand(card5);
        hand2.addCardToHand(card6);
        hand2.addCardToHand(card7);
        hand2.addCardToHand(card8);
        assertEquals(1, hand1.compareTo(hand2));
    }

    @Test
    void containAce() {
    }

    @Test
    void main() {
    }
}