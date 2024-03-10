package pokercard;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// HumanPlayer 클래스
public class HumanPlayer extends Player {
    private List<Card> hand;
    private Scanner scanner;

    public HumanPlayer() {
        hand = new ArrayList<>();
        scanner = new Scanner(System.in);
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
        System.out.println("당신의 패: " + hand);

        // 사용자에게 어떤 행동을 할 것인지 입력받는 로직 추가
        System.out.println("더 카드를 뽑으시겠습니까? (예/아니오)");
        String input = scanner.nextLine().toLowerCase();

        if (input.equals("예")) {
            Card drawnCard = drawCard();
            receiveCard(drawnCard);
            if (drawnCard != null) {
                receiveCard(drawnCard);
                System.out.println("새로운 카드를 받았습니다: " + drawnCard);
            } else {
                System.out.println("남아있는 카드가 없습니다.");
            }
        }
    }
    private Card drawCard() {

        Random random = new Random();
        String[] suits = {"하트", "다이아몬드", "클로버", "스페이드"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        String randomSuit = suits[random.nextInt(suits.length)];
        String randomRank = ranks[random.nextInt(ranks.length)];

        return new PlayingCard(randomSuit, randomRank);
    }

}