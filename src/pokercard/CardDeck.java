package pokercard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// CardDeck 클래스: 인터페이스와 싱글톤 패턴 사용
class CardDeck {
    private static CardDeck instance;
    private List<Card> cards;

    CardDeck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    public static CardDeck getInstance() {
        if (instance == null) {
            instance = new CardDeck();
        }
        return instance;
    }

    private void initializeDeck() {
        // 각 슈트와 랭크의 모든 조합을 생성하여 cards 리스트에 추가
        String[] suits = {Define.SUIT_HEARTS, Define.SUIT_DIAMONDS, Define.SUIT_CLUBS, Define.SUIT_SPADES};
        String[] ranks = {Define.RANK_ACE, Define.RANK_TWO, Define.RANK_THREE, Define.RANK_FOUR,Define.RANK_FIVE,
                Define.RANK_SIX,Define.RANK_SEVEN,Define.RANK_EIGHT,Define.RANK_NINE,Define.RANK_TEN,
                Define.RANK_JACK,Define.RANK_QUEEN,Define.RANK_KING};

        for (String suit : suits) {
            for (String rank : ranks) {
                Card card = new PlayingCard(suit, rank);
                cards.add(card);
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        int n = cards.size();
        Random random = new Random();

        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        } else {
            return null;
        }
    }

    public static void setInstance(CardDeck instance) {
        CardDeck.instance = instance;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}