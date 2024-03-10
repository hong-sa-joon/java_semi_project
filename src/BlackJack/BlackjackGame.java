package BlackJack;

import java.util.Scanner;

public class BlackjackGame {
    private static final int INITIAL_BALANCE = 1000; // 플레이어의 초기 잔고
    private static final int WINNING_MULTIPLIER = 2; // 배팅 금액의 몇 배로 이길지 설정

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int playerBalance = INITIAL_BALANCE;

        while (playerBalance > 0) {
            System.out.println("현재 잔고: " + playerBalance);
            System.out.println("얼마를 배팅하시겠습니까?");
            int betAmount = getBetAmount(scanner, playerBalance);

            Deck deck = new Deck();
            BlackjackCard card1 = (BlackjackCard) deck.drawCard();
            BlackjackCard card2 = (BlackjackCard) deck.drawCard();

            // 게임 플레이
            int playerScore = playGame(scanner, deck, card1, card2);

            // 게임 결과에 따라 잔고 조정
            playerBalance += determineBetResult(betAmount, playerScore);

            // 계속해서 게임을 진행할지 묻기
            System.out.println("계속해서 게임을 진행하시겠습니까? (Y/N)");
            String continueGame = scanner.nextLine();

            if (!"Y".equalsIgnoreCase(continueGame)) {
                System.out.println("게임을 종료합니다. 최종 잔고: " + playerBalance);
                break;
            }
        }
    }

    private static int getBetAmount(Scanner scanner, int playerBalance) {
        int betAmount;

        while (true) {
            try {
                betAmount = Integer.parseInt(scanner.nextLine());
                if (betAmount <= 0 || betAmount > playerBalance) {
                    throw new IllegalArgumentException("유효하지 않은 배팅 금액입니다. 다시 입력하세요.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("숫자를 입력하세요. (1 이상, 현재 잔고 이하)");
            }
        }

        return betAmount;
    }

    private static int playGame(Scanner scanner, Deck deck, BlackjackCard card1, BlackjackCard card2) {
        System.out.println("플레이어의 초기 카드:");
        displayCard(card1);
        displayCard(card2);
        // 플레이어의 초기 점수 계산
        int playerScore = calculateTotalScore(card1, card2);
        System.out.println("플레이어의 초기 점수: " + playerScore);

        // 플레이어에게 카드 받기
        while (true) {
            System.out.println("카드를 더 받으시겠습니까? (Y/N)");
            String choice = scanner.nextLine();

            if ("Y".equalsIgnoreCase(choice)) {

                BlackjackCard newCard = (BlackjackCard) deck.drawCard();
                displayCard(newCard);
                playerScore += newCard.calculateCardValue();
                System.out.println("플레이어의 업데이트된 점수: " + playerScore);

                if (playerScore > 21) {
                    System.out.println("버스트! 플레이어 패배.");
                    determineWinner(playerScore, 0);
                    break;
                }
            } else if ("N".equalsIgnoreCase(choice)) {
                // 딜러의 초기 카드
                BlackjackCard dealerCard1 = (BlackjackCard) deck.drawCard();
                BlackjackCard dealerCard2 = (BlackjackCard) deck.drawCard();

                // 딜러의 초기 점수 계산
                int dealerScore = calculateTotalScore(dealerCard1, dealerCard2);
                System.out.println("딜러의 초기 점수: " + dealerScore);

                // 딜러의 두 번째 카드 보여주기
                System.out.println("딜러의 오픈된 카드:");
                displayCard(dealerCard2);

                // 딜러의 룰에 따라 카드를 계속 뽑기
                while (dealerScore < 17) {
                    BlackjackCard newDealerCard = (BlackjackCard) deck.drawCard();
                    dealerScore += newDealerCard.calculateCardValue();
                }

                // 딜러의 최종 점수 출력
                System.out.println("딜러의 최종 점수: " + dealerScore);

                // 결과 확인
                determineWinner(playerScore, dealerScore);
                //잔고고정
                 determineWinner(playerScore, dealerScore);

                break;
            } else {
                System.out.println("유효하지 않은 선택입니다. 'Y' 또는 'N'을 입력하세요.");
            }
        }

        return playerScore;
    }

    private static void displayCard(BlackjackCard card) {
        System.out.println(card);
    }

    private static int calculateTotalScore(BlackjackCard card1, BlackjackCard card2) {
        return card1.calculateCardValue() + card2.calculateCardValue();
    }

    private static void determineWinner(int playerScore, int dealerScore) {
        System.out.println("플레이어의 최종 점수: " + playerScore);
        System.out.println("딜러의 최종 점수: " + dealerScore);

        if (playerScore > 21 || (dealerScore <= 21 && dealerScore > playerScore)) {
            System.out.println("딜러 승리! 배팅 금액을 잃습니다.");
        } else if (dealerScore > 21 || playerScore > dealerScore) {
            int winnings = WINNING_MULTIPLIER * playerScore;
            System.out.println("플레이어 승리! 배팅 금액의 " + WINNING_MULTIPLIER + "배를 얻습니다.");
        } else {
            System.out.println("무승부! 배팅 금액을 반환합니다.");
        }
    }

    private static int determineBetResult(int betAmount, int playerScore) {
        if (playerScore > 21) {
            System.out.println("플레이어는 배팅 금액을 잃습니다.");
            return -betAmount;
        } else {
            return betAmount;
        }
    }
}