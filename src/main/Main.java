//package main;
//
//import dao.AccountDaoImpl;
//import ui.BankDashboard;
//
//public class Main {
//    public static void main(String[] args) {
//        // Create sample accounts if file doesn't exist
//        AccountDaoImpl accountDao = new AccountDaoImpl();
//        
//        // Start the UI
//        new BankDashboard(accountDao).setVisible(true);
//    }
//}
//package main;
//
//import java.util.List;
//import ui.BankDashboard;
//
//public class Main {
//    public static void main(String[] args) {
//        // Initialize sample data if files don't exist
//        if (!new java.io.File("data/accounts.csv").exists()) {
//            new dao.AccountDaoImpl().saveAll(List.of(
//                new model.Account("Swornim", "Karki", "5551112233", 1001, 500000.00),
//                new model.Account("Rishav", "Patel", "5554445566", 1002, 30000.00)
//            ));
//        }
//        
//        // Start application
//        new BankDashboard().setVisible(true);
//    }
//}
//package main;
//
//import dao.AccountDaoImpl;  // Handles saving/loading accounts
//import model.Account;       // Represents a bank account
//import ui.BankDashboard;    // The main user interface
//import java.io.File;        // For working with files
//import java.util.List;      // For working with lists of accounts
//import javax.swing.SwingUtilities;  // For safe GUI operations
//
//public class Main {
//
//    public static void main(String[] args) {
//        // 1. First, create a folder to store our data if it doesn't exist
//        createDataFolder();
//        
//        // 2. Check if we need to create sample accounts
//        createSampleAccountsIfNeeded();
//        
//        // 3. Start the bank application
//        startBankApplication();
//    }
//    
//    // Helper method to create the data folder
//    private static void createDataFolder() {
//        File dataFolder = new File("data");
//        if (!dataFolder.exists()) {
//            boolean created = dataFolder.mkdir();
//            if (created) {
//                System.out.println("Created 'data' folder for storing accounts");
//            }
//        }
//    }
//    
//    // Helper method to create sample accounts
//    private static void createSampleAccountsIfNeeded() {
//        File accountsFile = new File("src/data/accounts.csv");
//        
//        // Only create samples if the file doesn't exist
//        if (!accountsFile.exists()) {
//            System.out.println("Creating sample accounts...");
//            
//            // Create two sample accounts
//            Account account1 = new Account("Swornim", "Karki", "5551112233", 1001, 5000.00);
//            Account account2 = new Account("Rishav", "Patel", "5554445566", 1002, 3000.00);
//            
//            // Save them using our account manager
//            AccountDaoImpl accountManager = new AccountDaoImpl();
//            accountManager.saveAll(List.of(account1, account2));
//            
//            System.out.println("Created 2 sample accounts");
//        }
//    }
//    
//    // Helper method to start the application
//    private static void startBankApplication() {
//        // This ensures the GUI runs properly
//        SwingUtilities.invokeLater(() -> {
//            BankDashboard dashboard = new BankDashboard();
//            dashboard.setVisible(true);
//            System.out.println("Bank application started successfully!");
//        });
//    }
//}
//package main;
//
//import dao.AccountDaoImpl;
//import model.Account;
//import ui.BankDashboard;
//
//import java.io.IOException;
//import java.nio.file.*;
//import java.util.List;
//import javax.swing.SwingUtilities;
//
//public class Main {
//    public static void main(String[] args) {
//        // Define the path to the 'data' directory
//        Path dataDir = Paths.get("data");
//
//        try {
//            // Create the 'data' directory if it doesn't exist
//            Files.createDirectories(dataDir);
//        } catch (IOException e) {
//            System.err.println("Failed to create 'data' directory: " + e.getMessage());
//            return; // Exit if directory creation fails
//        }
//
//        // Define the path to the 'accounts.csv' file
//        Path accountsFile = dataDir.resolve("accounts.csv");
//
//        // Check if 'accounts.csv' exists; if not, create sample accounts
//        if (Files.notExists(accountsFile)) {
//            try {
//                AccountDaoImpl accountDao = new AccountDaoImpl();
//                accountDao.saveAll(List.of(
//                    new Account("Swornim", "Karki", 1001, 500000.00),
//                    new Account("Rishav", "Patel", 1002, 30000.00)
//                ));
//                System.out.println("Sample accounts created successfully.");
//            } catch (Exception e) {
//                System.err.println("Error creating sample accounts: " + e.getMessage());
//                e.printStackTrace();
//            }
//        }
//
//        // Launch the Bank Dashboard GUI on the Event Dispatch Thread
//        SwingUtilities.invokeLater(() -> {
//            BankDashboard dashboard = new BankDashboard();
//            dashboard.setVisible(true);
//        });
//    }
//}
//package main;
//
//import dao.AccountDaoImpl;
//import model.Account;
//import model.Customer;
//import ui.BankDashboard;
//
//import javax.swing.*;
//import java.io.IOException;
//import java.nio.file.*;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        // Define the path to the 'data' directory
//        Path dataDir = Paths.get("data");
//
//        try {
//            // Create the 'data' directory if it doesn't exist
//            Files.createDirectories(dataDir);
//        } catch (IOException e) {
//            System.err.println("Failed to create 'data' directory: " + e.getMessage());
//            return; // Exit if directory creation fails
//        }
//
//        // Initialize DAO and sample data
//        AccountDaoImpl accountDao = new AccountDaoImpl();
//
//        // Load from file or create sample if empty
//        if (accountDao.findAll().isEmpty()) {
//            try {
//                accountDao.addAccount(new Account(
//                    new Customer("Swornim", "Karki", "9800000001"), 1001, 500000.00));
//                accountDao.addAccount(new Account(
//                    new Customer("Samina", "Shrestha", "9800000002"), 1002, 300000000.00));
//                System.out.println("Sample accounts created successfully.");
//            } catch (Exception e) {
//                System.err.println("Error creating sample accounts: " + e.getMessage());
//                e.printStackTrace();
//            }
//        }
//
//        // Launch the GUI on the Event Dispatch Thread
//        SwingUtilities.invokeLater(() -> {
//            BankDashboard dashboard = new BankDashboard();
//            dashboard.setVisible(true);
//        });
//    }
//}
package main;

import dao.AccountDaoImpl;
import model.Account;
import model.Customer;
import ui.BankDashboard;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        // Ensure 'src/data' directory exists
        Path dataDir = Paths.get("src", "data");

        try {
            Files.createDirectories(dataDir);
        } catch (IOException e) {
            System.err.println("Failed to create 'src/data' directory: " + e.getMessage());
            return; // Exit if directory creation fails
        }

        // Initialize DAO AFTER directory is ensured
        AccountDaoImpl accountDao = new AccountDaoImpl();

        // Load from file or create sample if empty
        if (accountDao.findAll().isEmpty()) {
            try {
                accountDao.addAccount(new Account(
                    new Customer("Swornim", "Karki", "9800000001"), 1001, 500000.00));
                accountDao.addAccount(new Account(
                    new Customer("Samina", "Shrestha", "9800000002"), 1002, 300000000.00));
                System.out.println("Sample accounts created and saved.");
            } catch (Exception e) {
                System.err.println("Error creating sample accounts: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Launch the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            BankDashboard dashboard = new BankDashboard();
            dashboard.setVisible(true);
        });
    }
}
