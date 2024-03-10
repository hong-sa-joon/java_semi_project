package pokercard;

// PlayingCard 클래스: Card 인터페이스 구현
public class PlayingCard implements Card {
    private String suit;
    private String rank;

    public PlayingCard(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String getSuit() {
        return suit;
    }

    @Override
    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PlayingCard that = (PlayingCard) obj;

        return suit.equals(that.suit) && rank.equals(that.rank);
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }
}