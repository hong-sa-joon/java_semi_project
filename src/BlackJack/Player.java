package BlackJack;

public class Player {
    private String name;
    private int accountBalance;

    public Player(String name, int initialBalance) {
        this.name = name;
        this.accountBalance = initialBalance;
    }

    public String getName() {
        return name;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int newBalance) {
        this.accountBalance = newBalance;
    }
}