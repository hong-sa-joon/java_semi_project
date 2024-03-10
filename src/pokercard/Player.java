package pokercard;

import java.util.ArrayList;
import java.util.List;

abstract class Player {
    protected List<Card> hand;

    public Player() {
        this.hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return hand;
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public abstract void playTurn();
}