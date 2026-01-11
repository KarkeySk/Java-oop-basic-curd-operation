////package model;
////
////public class Account {
////    private int accountNumber;
////    private Customer customer;
////    private double balance;
////
////    // Constructor with String parameters to create a new Customer
////    public Account(String firstName, String lastName, String mobile, int accountNumber, double balance) {
////        this.customer = new Customer(firstName, lastName, mobile);
////        this.accountNumber = accountNumber;
////        this.balance = balance;
////    }
////
////    // Constructor accepting an int account number and a Customer object
////    public Account(int accountNumber, Customer customer, double balance) {
////        this.accountNumber = accountNumber; // Initialize accountNumber
////        this.customer = customer; // Initialize customer
////        this.balance = balance; // Initialize balance
////    }
////
////    public Account(String firstName, String lastName, int accountNumber, double balance) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
////    }
////
////    // Bank operations
////    public void deposit(double amount) {
////        balance += amount; 
////    }
////
////    public void withdraw(double amount) {
////        if (amount <= balance) {
////            balance -= amount; 
////        } else {
////            throw new IllegalArgumentException("Insufficient funds");
////        }
////    }
////
////    // Getters
////    public int getAccountNumber() { 
////        return accountNumber; 
////    }
////
////    public Customer getCustomer() { 
////        return customer; 
////    }
////
////    public double getBalance() { 
////        return balance; 
////    }
////
////    public void setBalance(double d) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
////    }
////
////    public void setAccountNumber(int i) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
////    }
////}
//package model;
//
//public class Account {
//    private final String firstName;
//    private final String lastName;
//    private final int accountNumber;
//    private final double balance;
//
//    // Constructor
//    public Account(String firstName, String lastName, int accountNumber, double balance) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.accountNumber = accountNumber;
//        this.balance = balance;
//    }
//
//    public Account(String text, String text0, String string, int i, double balance) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    // Getters
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public int getAccountNumber() {
//        return accountNumber;
//    }
//
//    public double getBalance() {
//        return balance;
//    }
//
//    // Optionally, you can also add setters if needed
//
//    public void setBalance(double d) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public Object getCustomer() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//}
//package model;
//
//public class Account {
//    private int accountNumber;
//    private double balance;
//    private Customer customer;
//
//    // Constructor
//    public Account(String customer, String lastName, int accountNumber, double balance) {
//        this.customer = customer;
//        this.accountNumber = accountNumber;
//        this.balance = balance;
//    }
//
//    public Account(Customer customer, int newAccountNumber, double balance) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    // Getters and Setters
//    public int getAccountNumber() {
//        return accountNumber;
//    }
//
//    public double getBalance() {
//        return balance;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setBalance(double balance) {
//        this.balance = balance;
//    }
//}
package model;

public class Account {
    private Customer customer;
    private int accountNumber;
    private double balance;

    public Account(Customer customer, int accountNumber, double balance) {
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getters
    public Customer getCustomer() { return customer; }
    public int getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }

    // Setters
    public void setBalance(double balance) { this.balance = balance; }
}
