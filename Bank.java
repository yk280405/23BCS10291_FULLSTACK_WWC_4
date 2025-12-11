import java.util.*;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    protected double balance;

    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited successfully!");
    }

    public void withdraw(double amount) {
        balance -= amount;
        System.out.println(amount + " withdrawn successfully!");
    }

    public void printDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolder);
        System.out.println("Balance        : " + balance);
    }
}

class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolder, double balance, double interestRate) {
        super(accountNumber, accountHolder, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Withdrawal failed! Not enough balance.");
        } else {
            balance -= amount;
            System.out.println(amount + " withdrawn successfully from Savings Account!");
        }
    }

    public void applyInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        System.out.println("Interest of " + interest + " applied!");
    }

    @Override
    public void printDetails() {
        System.out.println("\n--- Savings Account Details ---");
        super.printDetails();
        System.out.println("Interest Rate  : " + interestRate + "%");
    }
}

class Bank {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("A101", "Yogesh", 50000);
        acc1.deposit(2000);
        acc1.withdraw(3000);
        acc1.printDetails();

        SavingsAccount sAcc = new SavingsAccount("S202", "Raghav", 30000, 5);
        sAcc.deposit(5000);
        sAcc.withdraw(40000);
        sAcc.withdraw(2000);
        sAcc.applyInterest();
        sAcc.printDetails();
    }
}
