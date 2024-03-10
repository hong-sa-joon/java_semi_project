package BlackJack;

// BetSystem.java
public class BetSystem {
    public int placeBet(Player player, int amount) {
        if (amount > player.getAccountBalance()) {
            System.out.println("베팅 실패: 잔고 부족");
            return 0;
        } else {
            System.out.println(player.getName() + "님이 " + amount + "만큼 베팅하셨습니다.");
            player.setAccountBalance(player.getAccountBalance() - amount);
            return amount;
        }
    }
}