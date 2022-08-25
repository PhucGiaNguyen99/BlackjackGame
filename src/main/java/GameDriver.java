import java.util.ArrayList;
import java.util.Scanner;

public class GameDriver {
    private ArrayList<Player> playersList;
    private ArrayList<Player> nonBlackJackPlayersList;

    // Index to control turn of players, initialized to 1
    private int turnIndex;

    // Size of players list
    private int numOfPlayers;
    private Deck deck;

    // Create an object for dealer
    private Player dealer = new Player("", "", true);

    private boolean isGameFinished;

    // array list to store list of winners
    private ArrayList<Player> winnersList;


    // DEALER-----------------------------------------------------------------------------------------------------------
    // Checking
    // Deal card for dealer
    public void printDealerTotal() {
        dealer.getPlayerHand().addCardToHand(new Card("7", "H", null));
        dealer.getPlayerHand().addCardToHand(new Card("9", "H", null));
        System.out.println(dealer.calculateTotalPointPlayerHand());
    }

    // Check whether dealer is busted or not
    private boolean isDealerBusted() {
        return dealer.isBusted();
    }


    //TURN--------------------------------------------------------------------------------------------------------------

    // Check whether this player is in correct turn or not by comparing the phone number of this player with the CORRECT TURN PLAYER
    private Player getCorrectTurnPlayer() {
        return playersList.get(turnIndex);
    }

    private boolean isPhoneNumberEqual(String phoneNumber1, String phoneNumber2) {
        return phoneNumber1.equals(phoneNumber2);
    }

    // Getting phone number and then compare to the correct turn player to check whether they are equal
    private boolean isCorrectTurnPlayer(String phoneNumber) {
        return isPhoneNumberEqual(phoneNumber, getCorrectTurnPlayer().getPhoneNumber());
    }

    // Show the player's total point and request move
    private String getMove(Player player) {
        //System.out.println("It's " + player.getName() + "'s turn.");
        System.out.println("Total point: " + player.calculateTotalPointPlayerHand());
        System.out.println("Enter move: ");

        Scanner scanner = new Scanner(System.in);
        String move = scanner.next().toUpperCase();
        while (!move.equals("HIT") && !move.equals("STAND")) {
            System.out.println("Enter move(Hit/Stand): ");
            move = scanner.next().toUpperCase();
        }
        return move;
    }

    // If stand, skip to the next player
    private void processStand() {
        turnIndex++;
    }

    // Check whether player is busted or not.
    // If player is busted, skip to the next player. Otherwise, deal card for player, print out the hand and total point.
    private void processHit(Player player) {
        if (player.isBusted()) {
            System.out.println("You're busted!");
            turnIndex++;
            return;
        } else {
            dealCard(player);
        }
    }

    // Get player's name from phone number
    private String getNameFromPhoneNumber(String phoneNumber) {
        for (Player player : playersList) {
            if (player.getPhoneNumber().equals(phoneNumber)) {
                return player.getName();
            }
        }
        return null;
    }

    // Process move for player by getting phone number and check whether it is the correct in turn player
    private void processMove(String phoneNumber) {
        if (isCorrectTurnPlayer(phoneNumber)) {
            processCorrectTurnPlayer();
        } else {
            System.out.println("It isn't " + getNameFromPhoneNumber(phoneNumber) + "'s turn!");
        }
    }

    // Process move for the correct player in turn
    private void processCorrectTurnPlayer() {
        Player player = playersList.get(turnIndex);
        String move = getMove(player);
        // If player decides to stand, skip to next player in the players list
        if (move.equals("STAND")) {
            processStand();
            return;
        }

        // Otherwise, while player decides to hit,
        if (move.equals("HIT")) {
            processHit(player);

            // Using recursion, process move of the player until he decides to stand.
            processCorrectTurnPlayer();
        }

    }


    // DECK-------------------------------------------------------------------------------------------------------------
    private void shuffleDeck() {
        deck.shuffleDeck();
    }

    private Card removeFirstCard() {
        return deck.removeAtIndex(0);
    }

    // deal one card for player or dealer
    private void dealCard(Player player) {
        player.dealCardForPlayer(removeFirstCard());
    }

    // deal 2 cards for all players including the dealer in the players list
    private void dealStartingCards() {
        for (Player player : playersList) {
            // deal cards for dealer
            if (player.isDealer()) {
                System.out.println("Dealing cards for dealer...");
                dealCard(dealer);
                dealCard(dealer);
            } else {
                System.out.println("Dealing cards for " + player.getName() + "...");
                dealCard(player);
                dealCard(player);
                System.out.println("Total point: " + player.calculateTotalPointPlayerHand());
            }
        }
    }

    //GAME CONTROL------------------------------------------------------------------------------------------------------

    // deck is not empty initially
    private void initDeck() {
        deck = new Deck(false);
    }

    private void initPlayers(ArrayList<Player> playersList) {
        this.playersList = playersList;
        this.winnersList = new ArrayList<>();
    }

    // control the game
    public void initGame(ArrayList<Player> players, int deckCount) {

        // initialize the turn index to 0
        turnIndex = 0;
        numOfPlayers = players.size();
        nonBlackJackPlayersList = new ArrayList<>();

        // set game status to unfinished
        isGameFinished = false;

        while (!isGameFinished) {

        }
    }

    // Firstly, check whether the dealer has BlackJack, then traverse players list if any player also has BlackJack
    // set status to even, otherwise set lose
    private void checkBlackJack() {
        boolean dealerHasBlackJack = dealer.hasBlackJack();
        if (dealerHasBlackJack) {
            System.out.println("Dealer has BlackJack!!!!!\n");
            for (Player player : playersList) {
                if (!player.isDealer()) {

                    // Both dealer and player have BlackJack, so set status to tie
                    if (player.hasBlackJack()) {
                        player.setStatusTie();
                        System.out.println(player.getName() + " has BlackJack!");

                    }
                    // set status to lose
                    else {
                        player.setStatusLose();
                    }
                }
            }
        } else {
            for (Player player : playersList) {
                if (!player.isDealer()) {
                    if (player.hasBlackJack()) {
                        player.setStatusWin();
                        System.out.println(player.getName() + " has BlackJack!");
                    }

                    // if the player does not have BlackJack, store player in the nonBlackJackPlayersList
                    nonBlackJackPlayersList.add(player);
                }
            }

            // add the dealer to the nonBlackJackPlayersList
            nonBlackJackPlayersList.add(dealer);
        }
    }

    // check if game finished yet
    private boolean isGameFinished() {
        return isGameFinished;
    }


    private boolean dealerHasSoft17() {
        return dealer.hasAce() && dealer.calculateTotalPointPlayerHand() == 17;
    }

    /* If the dealer does not have BlackJack, dealer must hit while his hand is under or equal to 16 and check whether he has soft 17 or not
     * */
    private void dealerPlays() {
        while (dealer.calculateTotalPointPlayerHand() <= 16) {
            System.out.println("Dealer's total point: " + dealer.calculateTotalPointPlayerHand());
            System.out.println("Dealer hits.");
            dealCard(dealer);
            System.out.println();
        }

        // Dealer hits if he has soft 17
        // Soft 17 is when dealer has ace and total point is 17
        if (dealerHasSoft17()) {
            System.out.println("Dealer hits.");
            dealCard(dealer);
        }

        // show dealer total points
        System.out.println("Dealer's total points: " + dealer.calculateTotalPointPlayerHand());
    }

    // show final points
    private void showFinalPoints() {
        System.out.println();
        for (Player player : nonBlackJackPlayersList) {
            if (player.isDealer()) {
                System.out.println("Dealer: " + dealer.calculateTotalPointPlayerHand());
            } else {
                System.out.println(player.getName() + ": " + player.calculateTotalPointPlayerHand());
            }

        }
    }
}


