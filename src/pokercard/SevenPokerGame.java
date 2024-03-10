package pokercard;

import java.util.*;
import java.util.stream.Collectors;

// SevenPokerGame 클래스
public class SevenPokerGame {
    private List<Player> players;
    private CardDeck cardDeck;
    private Scanner scanner;

    private Player humanPlayer;
    private Player computerPlayer;
    public SevenPokerGame(List<Player> players) {
        this.players = players;
        this.cardDeck = new CardDeck();  // CardDeck 초기화 추가
        this.humanPlayer = new HumanPlayer();  // HumanPlayer 초기화 추가
        this.computerPlayer = new ComputerPlayer("컴퓨터");  // ComputerPlayer 초기화 추가
        this.scanner = new Scanner(System.in);

    }


    public SevenPokerGame() {
        cardDeck = CardDeck.getInstance();
        humanPlayer = new HumanPlayer();
        computerPlayer = new ComputerPlayer("컴퓨터");
    }

    public void playGame() {
        // 게임 초기화
        cardDeck.shuffle();
        dealInitialCards();

        for (Player player : players) {
            player.playTurn();  // 각 플레이어의 턴 진행
        }

        // 게임 종료 및 승자 결정
        Player winner = determineWinner();
        displayResult(winner);
    }

    private void dealInitialCards() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                humanPlayer.receiveCard(cardDeck.drawCard());
                computerPlayer.receiveCard(cardDeck.drawCard());
            }
        }
    }

    Player determineWinner() {
        Player winner = null;
        int highestRank = 0;

        for (Player player : players) {
            int playerRank = evaluateHand(player.getHand());

            if (playerRank > highestRank) {
                highestRank = playerRank;
                winner = player;
            }



        }
        determineWinner();  // 승자 발표


        // 예시로 가장 높은 숫자를 가진 플레이어를 승자로 설정
        if (evaluateHand(humanPlayer.getHand()) > evaluateHand(computerPlayer.getHand())) {
            return humanPlayer;
        } else {
            return computerPlayer;
        }
    }
    private int evaluateHighCard(List<Card> hand) {
        // 탑(하이 카드) 평가
        return Collections.max(hand.stream()
                .map(card -> getRankValue(card.getRank()))
                .collect(Collectors.toList()));
    }
    private int evaluateOnePair(List<Card> hand) {
        // 원 페어 평가
        Map<String, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        long pairCount = rankCounts.values().stream()
                .filter(count -> count == 2)
                .count();

        return pairCount == 1 ? 1 : 0;
    }
    private int evaluateTwoPair(List<Card> hand) {
        // 투 페어 평가
        Map<String, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        long pairCount = rankCounts.values().stream()
                .filter(count -> count == 2)
                .count();

        return pairCount == 2 ? 2 : 0;
    }
    private int evaluateTriple(List<Card> hand) {
        // 트리플 평가
        Map<String, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        long tripleCount = rankCounts.values().stream()
                .filter(count -> count == 3)
                .count();

        return tripleCount == 1 ? 3 : 0;
    }

    private int evaluateStraight(List<Card> hand) {
        // 스트레이트 평가
        Collections.sort(hand, (card1, card2) -> getRankValue(card1.getRank()) - getRankValue(card2.getRank()));

        for (int i = 0; i < hand.size() - 1; i++) {
            if (getRankValue(hand.get(i + 1).getRank()) - getRankValue(hand.get(i).getRank()) != 1) {
                return 0;
            }
        }

        return 4; // 스트레이트의 순위
    }
    private int evaluateFlush(List<Card> hand) {
        // 플러시 평가
        Map<String, Long> suitCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()));

        long flushCount = suitCounts.values().stream()
                .filter(count -> count == 5)
                .count();

        return flushCount == 1 ? 5 : 0;
    }
    private int evaluateFullHouse(List<Card> hand) {
        // 풀하우스 평가
        Map<String, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        boolean hasThreeOfAKind = rankCounts.values().stream()
                .anyMatch(count -> count == 3);

        boolean hasOnePair = rankCounts.values().stream()
                .anyMatch(count -> count == 2);

        return hasThreeOfAKind && hasOnePair ? 6 : 0;
    }

    private int evaluateFourOfAKind(List<Card> hand) {
        // 포카드 평가
        Map<String, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        long fourOfAKindCount = rankCounts.values().stream()
                .filter(count -> count == 4)
                .count();

        return fourOfAKindCount == 1 ? 7 : 0;
    }
    private int evaluateStraightFlush(List<Card> hand) {
        // 스트레이트 플러시 평가
        return evaluateStraight(hand) == 4 && evaluateFlush(hand) == 5 ? 8 : 0;
    }

    private int evaluateRoyalFlush(List<Card> hand) {
        // 로얄 스트레이트 플러시 평가
        List<String> royalRanks = List.of("10", "J", "Q", "K", "A");

        boolean isRoyalFlush = new HashSet<>(hand.stream()
                .map(Card::getRank)
                .collect(Collectors.toList()))
                .containsAll(royalRanks) && evaluateFlush(hand) == 5;

        return isRoyalFlush ? 9 : 0;
    }

    private int evaluateHand(List<Card> hand) {
        int highestRank = 0;

        int straightFlushRank = evaluateStraightFlush(hand);
        highestRank = Math.max(highestRank, straightFlushRank);

        int royalFlushRank = evaluateRoyalFlush(hand);
        highestRank = Math.max(highestRank, royalFlushRank);

        int fourOfAKindRank = evaluateFourOfAKind(hand);
        highestRank = Math.max(highestRank, fourOfAKindRank);

        int fullHouseRank = evaluateFullHouse(hand);
        highestRank = Math.max(highestRank, fullHouseRank);

        int flushRank = evaluateFlush(hand);
        highestRank = Math.max(highestRank, flushRank);

        int straightRank = evaluateStraight(hand);
        highestRank = Math.max(highestRank, straightRank);

        int tripleRank = evaluateTriple(hand);
        highestRank = Math.max(highestRank, tripleRank);

        int twoPairRank = evaluateTwoPair(hand);
        highestRank = Math.max(highestRank, twoPairRank);

        int onePairRank = evaluateOnePair(hand);
        highestRank = Math.max(highestRank, onePairRank);

        int highCardRank = evaluateHighCard(hand);
        highestRank = Math.max(highestRank, highCardRank);

        return highestRank;
    }

    private int getRankValue(String rank) {
        // 카드의 랭크를 숫자로 변환
        // ...
        return 0;
    }

    void displayResult(Player winner) {
        if (winner != null) {
            System.out.println("승자는 플레이어 " + (players.indexOf(winner) + 1) + "입니다!");
            System.out.println("축하합니다!");
        } else {
            System.out.println("무승부입니다! 이번에는 승자가 없습니다.");
        }
    }

    public static void main(String[] args) {
        // 게임을 초기화하고 플레이어를 추가
        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer());
        players.add(new ComputerPlayer("컴퓨터"));

        SevenPokerGame game = new SevenPokerGame(players);

        // 게임을 시작하고 결과를 표시
        game.playGame();
        Player winner = game.determineWinner();
        game.displayResult(winner);
    }

}