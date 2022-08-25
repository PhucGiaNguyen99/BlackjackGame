public class Dealer {
    // Create a card hand for the dealer
    Hand dealerHand;

    public Dealer() {
        this.dealerHand = new Hand();
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public void setDealerHand(Hand dealerHand) {
        this.dealerHand = dealerHand;
    }

    public boolean isDealerBusted() {
        return dealerHand.calculateTotalPoint() > 21;
    }

    // Add one card to the dealer hand
    public void addFront(Card card) {
        dealerHand.addCardToHand(card);
    }

    public int calculateTotalDealerHand() {
        return dealerHand.calculateTotalPoint();
    }

    public void printHand() {
        dealerHand.presentCardHand();
    }

    // Add one card to the player hand
    public void dealCardForDealer(Card card) {
        this.getDealerHand().addCardToHand(card);
    }

    public boolean isBusted() {
        return dealerHand.isBusted();
    }

    public boolean isBlackJack() {
        return dealerHand.isBlackJack();
    }

    // Check if there is any Ace in the dealer hand
    public boolean containAce(){
        return dealerHand.containAce();
    }
}
