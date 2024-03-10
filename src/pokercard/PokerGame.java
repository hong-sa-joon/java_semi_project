package pokercard;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PokerGame {
    private final List<Player> players;
    private final CardDeck cardDeck;

    public PokerGame() {
        players = new ArrayList<>();
        cardDeck = CardDeck.getInstance();
    }

    public void startGame() {
        initializePlayers(); // 플레이어 초기화
        cardDeck.shuffle(); // 카드 섞기

        dealInitialCards(); // 초기 카드 나눠주기

        for (Player player : players) {
            player.playTurn(); // 각 플레이어의 턴 진행
        }

        determineWinner(); // 승자 결정
    }

    private void initializePlayers() {
        Scanner scanner = new Scanner(System.in);

        // 사용자 생성
        System.out.print("사용자의 이름을 입력하세요: ");
        String playerName = scanner.nextLine();
        Player humanPlayer = new HumanPlayer();
        players.add(humanPlayer);

        // 컴퓨터 생성
        Player computerPlayer = new ComputerPlayer("컴퓨터");
        players.add(computerPlayer);
    }

    private void dealInitialCards() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                Card card = cardDeck.drawCard();
                player.receiveCard(card);
            }
        }
    }

    private void dealCards() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                Card drawnCard = cardDeck.drawCard();
                if (drawnCard != null) {
                    player.receiveCard(drawnCard);
                }
            }
        }
    }

    private Player determineWinner() {
        Player winner = null;
        int highestRank = 0;

        for (Player player : players) {
            int playerRank = evaluateHand(player.getHand());

            if (playerRank > highestRank) {
                highestRank = playerRank;
                winner = player;
            }
        }

        return winner;
    }

    private int evaluateHand(List<Card> hand) {
        // 간단한 족보 평가 로직을 추가할 수 있습니다.
        // 높은 족보일수록 높은 값을 반환하도록 구현합니다.
        return 0;
    }
    private void displayResult(Player winner) {
        if (winner != null) {
            System.out.println("승자는 플레이어 " + (players.indexOf(winner) + 1) + "입니다!");
            System.out.println("축하합니다!");
        } else {
            System.out.println("무승부입니다! 이번에는 승자가 없습니다.");
        }
    }
}