package base.leetcode.scratch;

public class AccountManagement {

    private int balance;

    public AccountManagement(int balance) {
        // this is the balance check
        this.balance = balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalanceCheck() {
        return balance;
    }
}
