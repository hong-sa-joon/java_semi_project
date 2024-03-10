package BlackJack;

public class GameResult {
    public static void displayResult(Player player, int betAmount, boolean playerWins) {
        if (playerWins) {
            int winnings = betAmount * 2;
            player.setAccountBalance(player.getAccountBalance() + winnings);
            System.out.println(player.getName() + "님이 " + winnings + "를 획득하셨습니다!");
        } else {
            System.out.println(player.getName() + "님이 베팅한 " + betAmount + "를 잃으셨습니다.");
        }

        System.out.println("현재 잔고: " + player.getAccountBalance());
    }
}