package pokercard;

import java.util.ArrayList;
import java.util.List;

class ComputerPlayer extends Player {
    private List<Card> hand;

    public ComputerPlayer(String 컴퓨터) {
        hand = new ArrayList<>();
    }

    @Override
    public void receiveCard(Card card) {
        hand.add(card);
    }

    @Override
    public List<Card> getHand() {
        return hand;
    }

    @Override
    public void playTurn() {

    }

}
