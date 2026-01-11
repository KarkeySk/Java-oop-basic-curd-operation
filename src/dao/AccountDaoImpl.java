
package dao;

import model.Account;
import model.Customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl {
    private static final String CSV_FILE = "src/data/accounts.csv";
    private List<Account> accounts = new ArrayList<>();

    public AccountDaoImpl() {
        loadAccountsFromFile();
    }

    public List<Account> findAll() {
        return new ArrayList<>(accounts);
    }

    public void addAccount(Account account) {
        accounts.add(account);
        saveAccountsToFile();
    }

    public void deleteAccount(int accountNumber) {
        accounts.removeIf(acc -> acc.getAccountNumber() == accountNumber);
        saveAccountsToFile();
    }

    public Account findByAccountNumber(int accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null;
    }

    private void loadAccountsFromFile() {
        Path path = Paths.get(CSV_FILE);

        if (!Files.exists(path)) {
            System.out.println("No existing accounts file found at " + CSV_FILE + ". Starting with empty accounts.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 5) {
                    String firstName = tokens[0];
                    String lastName = tokens[1];
                    String mobile = tokens[2];
                    int accNumber = Integer.parseInt(tokens[3]);
                    double balance = Double.parseDouble(tokens[4]);

                    Customer customer = new Customer(firstName, lastName, mobile);
                    Account account = new Account(customer, accNumber, balance);
                    accounts.add(account);
                }
            }
            System.out.println("Loaded " + accounts.size() + " accounts from file.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading accounts file: " + e.getMessage());
        }
    }

    private void saveAccountsToFile() {
        Path path = Paths.get(CSV_FILE);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Account acc : accounts) {
                writer.write(String.format("%s,%s,%s,%d,%.2f",
                        acc.getCustomer().getFirstName(),
                        acc.getCustomer().getLastName(),
                        acc.getCustomer().getMobile(),
                        acc.getAccountNumber(),
                        acc.getBalance()));
                writer.newLine();
            }
            System.out.println("Saved " + accounts.size() + " accounts to file.");
        } catch (IOException e) {
            System.err.println("Error saving accounts file: " + e.getMessage());
        }
    }
}
