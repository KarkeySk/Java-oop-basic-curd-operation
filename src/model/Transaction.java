package model;

public class Transaction {
    private int fromAccount;
    private int toAccount;
    private double amount;
    private String type; // "DEPOSIT", "WITHDRAW", "TRANSFER"

    public Transaction(int fromAccount, int toAccount, double amount, String type) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.type = type;
    }

    // Getters
    public String getDetails() {
        return String.format("%s: $%.2f from %d to %d", 
               type, amount, fromAccount, toAccount);
    }
    
    // Additional getters for CSV serialization
    public int getFromAccount() { return fromAccount; }
    public int getToAccount() { return toAccount; }
    public double getAmount() { return amount; }
    public String getType() { return type; }
}