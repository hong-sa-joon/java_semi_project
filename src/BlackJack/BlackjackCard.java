package BlackJack;

public class BlackjackCard extends Card {
    private int value;

    public BlackjackCard(String suit, String rank, int value) {
        super(suit, rank);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isAce() {
        return getRank().equals("Ace");
    }

    public boolean isFaceCard() {
        String[] faceCards = {"King", "Queen", "Jack"};
        for (String faceCard : faceCards) {
            if (getRank().equals(faceCard)) {
                return true;
            }
        }
        return false;
    }
    public int calculateCardValue() {
        if (isAce()) {
            return 11; // Ace는 11로 계산될 수 있음
        } else if (isFaceCard()) {
            return 10; // King, Queen, Jack은 각각 10으로 계산됨
        } else {
            // 숫자 카드는 그 숫자의 값으로 계산
            return Integer.parseInt(getRank());
        }
    }

}