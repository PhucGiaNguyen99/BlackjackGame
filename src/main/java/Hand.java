import java.util.ArrayList;

public class Hand {
    public static final int BLACKJACK_VALUE = 9999;
    public static final int BUSTED_VALUE = -1;

    // Using an arrayList to hold the card hand
    private ArrayList<Card> cardHand;


    // If the player demand the hand is empty, create a deck with no card, otherwise, create a full standard deck

    public Hand() {
        cardHand = new ArrayList<>();
    }

    // Check the status of the player, whether the player is busted, black jack or nothing special so return the total point of the player's card hand
    public int getStatus() {
        if (isBusted()) {
            return BUSTED_VALUE;
        } else if (isBlackJack()) {
            return BLACKJACK_VALUE;
        } else {
            return this.calculateTotalPoint();
        }
    }


    // Check if the player is busted
    public boolean isBusted() {
        return this.calculateTotalPoint() > 21;
    }

    // Check if the player have black jack
    public boolean isBlackJack() {
        return (cardHand.size() == 2 && this.calculateTotalPoint() == 21);
    }


    // Calculate the total point of the player's card hand
    public int calculateTotalPoint() {

        // Separate the sum of the card hand except the aces and the sum of the aces as well as the number of aces in the card hand
        int totalPoint = 0;
        int nonAcesSum = 0;
        int acesSum = 0;
        int numOfAces = 0;


        for (Card card : cardHand) {
            if (card.getCardValue() == 1) {
                numOfAces++;
            } else {
                nonAcesSum += card.getCardValue();
            }
        }

        // There cannot be the case where more than 1 Ace are counted as 11 because at least 22 means the player is busted already.
        // Therefore, initially we assign 1 Ace the value of 11, all remaining Aces are counted as 1 only. Then we calculate the total, if it is busted,
        // the value of all every single Ace would be counted as 1 only.
        if (numOfAces > 0) {
            acesSum = 11;
        }

        // All remaining Aces except the first one which is already counted 11 would be counted as 1 only
        for (int countAce = 0; countAce < numOfAces - 1; countAce++) {
            acesSum += 1;
        }

        // If the player is busted, then every Ace are counted as 1 only
        if (acesSum + nonAcesSum > 21) {
            acesSum = numOfAces;
        }

        totalPoint = acesSum + nonAcesSum;

        return totalPoint;
    }

    // Present card hand of the player
    public void presentCardHand() {
        // System.out.println("Card hand of the player: ");
        for (int cardIndex = 0; cardIndex < cardHand.size(); cardIndex++) {
            System.out.println(cardHand.get(cardIndex).printCard());
        }
    }

    // Add new card to the player's hand
    public void addCardToHand(Card card) {
        cardHand.add(card);
    }


    // Compare the status of the player and the opponent covering the case one has blackjack and one is busted
    public int compareTo(Hand otherHand) {
        return this.getStatus() == otherHand.getStatus() ? 0 : this.getStatus() > otherHand.getStatus() ? 1 : -1;
    }

    // Check whether card hand including any ace
    public boolean containAce() {
        for (Card card : cardHand) {
            if (card.getCardValue() == 1) {
                return true;
            }
        }
        return false;
    }


}
