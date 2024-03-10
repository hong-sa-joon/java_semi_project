package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        initializeDeck();
        shuffleDeck();
    }

    private void initializeDeck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new BlackjackCard(suit, rank, calculateCardValue(rank)));
            }
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱이 비어있습니다.");
        }
        return cards.remove(0);
    }

    private int calculateCardValue(String rank) {
        if (rank.equals("Ace")) {
            return 11; // Ace는 11로 계산될 수 있음
        } else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
            return 10; // King, Queen, Jack은 각각 10으로 계산됨
        } else {
            // 숫자 카드는 그 숫자의 값으로 계산
            return Integer.parseInt(rank);
        }    }
}