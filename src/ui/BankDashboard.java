//package ui;
//
//import dao.AccountDaoImpl;
//import model.Account;
//import model.Customer;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.List;
//
//public class BankDashboard extends JFrame {
//    private AccountDaoImpl accountDao = new AccountDaoImpl();
//    private JTable accountTable;
//    private DefaultTableModel tableModel;
//    private JTextField searchField;
//
//    public BankDashboard() {
//        setupUI();
//        loadAccounts();
//    }
//
//    private void setupUI() {
//        setTitle("Simple Bank Manager");
//        setSize(1000, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout(10, 10));
//
//        // Create main panel with padding
//        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        // Search panel at top
//        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
//        searchField = new JTextField();
//        JButton searchButton = new JButton("Search");
//        searchButton.addActionListener(e -> searchAccounts());
//        
//        JPanel searchInputPanel = new JPanel(new BorderLayout(5, 5));
//        searchInputPanel.add(new JLabel("Search by Name or Account #:"), BorderLayout.WEST);
//        searchInputPanel.add(searchField, BorderLayout.CENTER);
//        searchInputPanel.add(searchButton, BorderLayout.EAST);
//        
//        searchPanel.add(searchInputPanel, BorderLayout.NORTH);
//        mainPanel.add(searchPanel, BorderLayout.NORTH);
//
//        // Table setup
//        String[] columnNames = {"Account #", "Name", "Balance (NPR)"};  // Added NPR to column header
//        tableModel = new DefaultTableModel(columnNames, 0) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false; // Make table non-editable
//            }
//        };
//        accountTable = new JTable(tableModel);
//        accountTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        accountTable.getTableHeader().setReorderingAllowed(false);
//        
//        // Style the table
//        accountTable.setRowHeight(25);
//        accountTable.setShowGrid(true);
//        accountTable.setGridColor(Color.LIGHT_GRAY);
//        accountTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
//        
//        JScrollPane scrollPane = new JScrollPane(accountTable);
//        scrollPane.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createEmptyBorder(5, 0, 5, 0),
//            BorderFactory.createLineBorder(Color.GRAY)
//        ));
//        mainPanel.add(scrollPane, BorderLayout.CENTER);
//
//        // Buttons panel
//        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
//
//        // Create buttons without colors
//        JButton addButton = new JButton("Add Account");
//        JButton deleteButton = new JButton("Delete Account");
//        JButton depositButton = new JButton("Deposit");
//        JButton withdrawButton = new JButton("Withdraw");
//        JButton transferButton = new JButton("Transfer");
//
//        // Style buttons
//        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);
//        addButton.setFont(buttonFont);
//        deleteButton.setFont(buttonFont);
//        depositButton.setFont(buttonFont);
//        withdrawButton.setFont(buttonFont);
//        transferButton.setFont(buttonFont);
//
//        // Action listeners
//        addButton.addActionListener(e -> addAccount());
//        deleteButton.addActionListener(e -> deleteAccount());
//        depositButton.addActionListener(e -> deposit());
//        withdrawButton.addActionListener(e -> withdraw());
//        transferButton.addActionListener(e -> transfer());
//
//        // Add buttons to panel
//        buttonPanel.add(addButton);
//        buttonPanel.add(deleteButton);
//        buttonPanel.add(depositButton);
//        buttonPanel.add(withdrawButton);
//        buttonPanel.add(transferButton);
//
//        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
//        add(mainPanel);
//    }
//
//    private void loadAccounts() {
//        tableModel.setRowCount(0);
//        List<Account> accounts = accountDao.findAll();
//
//        for (Account account : accounts) {
//            Object[] rowData = {
//                account.getAccountNumber(),
//                account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName(),
//                String.format("%.2f", account.getBalance())  // Removed "Npr" prefix
//            };
//            tableModel.addRow(rowData);
//        }
//    }
//
//    private void searchAccounts() {
//        String searchText = searchField.getText().trim().toLowerCase();
//        if (searchText.isEmpty()) {
//            loadAccounts();
//            return;
//        }
//
//        tableModel.setRowCount(0);
//        List<Account> accounts = accountDao.findAll();
//
//        for (Account account : accounts) {
//            String accountNumber = String.valueOf(account.getAccountNumber());
//            String fullName = (account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName()).toLowerCase();
//            
//            if (accountNumber.contains(searchText) || fullName.contains(searchText)) {
//                Object[] rowData = {
//                    account.getAccountNumber(),
//                    account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName(),
//                    String.format("%.2f", account.getBalance())  // Removed "Npr" prefix
//                };
//                tableModel.addRow(rowData);
//            }
//        }
//    }
//
//    private void addAccount() {
//        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        JTextField firstNameField = new JTextField();
//        JTextField lastNameField = new JTextField();
//        JTextField mobileField = new JTextField();
//        JTextField balanceField = new JTextField();
//
//        panel.add(new JLabel("First Name:"));
//        panel.add(firstNameField);
//        panel.add(new JLabel("Last Name:"));
//        panel.add(lastNameField);
//        panel.add(new JLabel("Mobile:"));
//        panel.add(mobileField);
//        panel.add(new JLabel("Initial Balance (NPR):"));  // Added NPR here
//        panel.add(balanceField);
//
//        int result = JOptionPane.showConfirmDialog(
//            this, panel, "Add New Account",
//            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//
//        if (result == JOptionPane.OK_OPTION) {
//            try {
//                double balance = Double.parseDouble(balanceField.getText());
//                Customer customer = new Customer(firstNameField.getText(), lastNameField.getText(), mobileField.getText());
//                int accountNumber = accountDao.findAll().size() + 1;
//                Account newAccount = new Account(customer, accountNumber, balance);
//                accountDao.addAccount(newAccount);
//                loadAccounts();
//                showMessage("Account added successfully!");
//            } catch (Exception e) {
//                showError("Error: " + e.getMessage());
//            }
//        }
//    }
//
//    private void deleteAccount() {
//        int selectedRow = accountTable.getSelectedRow();
//        if (selectedRow >= 0) {
//            int confirm = JOptionPane.showConfirmDialog(
//                this, 
//                "Are you sure you want to delete this account?",
//                "Confirm Delete",
//                JOptionPane.YES_NO_OPTION);
//            
//            if (confirm == JOptionPane.YES_OPTION) {
//                int accountNumber = (int) tableModel.getValueAt(selectedRow, 0);
//                accountDao.deleteAccount(accountNumber);
//                loadAccounts();
//                showMessage("Account deleted successfully!");
//            }
//        } else {
//            showError("Please select an account to delete.");
//        }
//    }
//
//    private void deposit() {
//        int selectedRow = accountTable.getSelectedRow();
//        if (selectedRow >= 0) {
//            int accountNumber = (int) tableModel.getValueAt(selectedRow, 0);
//            String amountStr = JOptionPane.showInputDialog(
//                this, 
//                "Enter deposit amount (NPR):",  // Added NPR here
//                "Deposit", 
//                JOptionPane.PLAIN_MESSAGE);
//            
//            if (amountStr != null && !amountStr.trim().isEmpty()) {
//                try {
//                    double amount = Double.parseDouble(amountStr);
//                    if (amount <= 0) {
//                        showError("Amount must be positive.");
//                        return;
//                    }
//                    
//                    Account account = accountDao.findByAccountNumber(accountNumber);
//                    account.setBalance(account.getBalance() + amount);
//                    loadAccounts();
//                    showMessage(String.format("Deposited %.2f NPR successfully!", amount));
//                } catch (NumberFormatException e) {
//                    showError("Invalid amount format.");
//                }
//            }
//        } else {
//            showError("Please select an account to deposit into.");
//        }
//    }
//
//    private void withdraw() {
//        int selectedRow = accountTable.getSelectedRow();
//        if (selectedRow >= 0) {
//            int accountNumber = (int) tableModel.getValueAt(selectedRow, 0);
//            String amountStr = JOptionPane.showInputDialog(
//                this, 
//                "Enter withdrawal amount (NPR):",  // Added NPR here
//                "Withdraw", 
//                JOptionPane.PLAIN_MESSAGE);
//            
//            if (amountStr != null && !amountStr.trim().isEmpty()) {
//                try {
//                    double amount = Double.parseDouble(amountStr);
//                    if (amount <= 0) {
//                        showError("Amount must be positive.");
//                        return;
//                    }
//                    
//                    Account account = accountDao.findByAccountNumber(accountNumber);
//                    if (account.getBalance() >= amount) {
//                        account.setBalance(account.getBalance() - amount);
//                        loadAccounts();
//                        showMessage(String.format("Withdrew %.2f NPR successfully!", amount));
//                    } else {
//                        showError("Insufficient funds.");
//                    }
//                } catch (NumberFormatException e) {
//                    showError("Invalid amount format.");
//                }
//            }
//        } else {
//            showError("Please select an account to withdraw from.");
//        }
//    }
//
//    private void transfer() {
//        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        JTextField fromAccountField = new JTextField();
//        JTextField toAccountField = new JTextField();
//        JTextField amountField = new JTextField();
//
//        panel.add(new JLabel("From Account #:"));
//        panel.add(fromAccountField);
//        panel.add(new JLabel("To Account #:"));
//        panel.add(toAccountField);
//        panel.add(new JLabel("Amount (NPR):"));  // Added NPR here
//        panel.add(amountField);
//
//        int result = JOptionPane.showConfirmDialog(
//            this, panel, "Transfer Funds",
//            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//
//        if (result == JOptionPane.OK_OPTION) {
//            try {
//                int fromAccountNumber = Integer.parseInt(fromAccountField.getText());
//                int toAccountNumber = Integer.parseInt(toAccountField.getText());
//                double amount = Double.parseDouble(amountField.getText());
//
//                if (amount <= 0) {
//                    showError("Amount must be positive.");
//                    return;
//                }
//
//                Account fromAccount = accountDao.findByAccountNumber(fromAccountNumber);
//                Account toAccount = accountDao.findByAccountNumber(toAccountNumber);
//
//                if (fromAccount != null && toAccount != null) {
//                    if (fromAccount.getBalance() >= amount) {
//                        fromAccount.setBalance(fromAccount.getBalance() - amount);
//                        toAccount.setBalance(toAccount.getBalance() + amount);
//                        loadAccounts();
//                        showMessage(String.format("Transferred %.2f NPR successfully!", amount));
//                    } else {
//                        showError("Insufficient funds in source account.");
//                    }
//                } else {
//                    showError("One or both account numbers are invalid.");
//                }
//            } catch (NumberFormatException e) {
//                showError("Please enter valid numbers for account numbers and amount.");
//            }
//        }
//    }
//
//    private void showError(String message) {
//        JOptionPane.showMessageDialog(
//            this, 
//            message, 
//            "Error", 
//            JOptionPane.ERROR_MESSAGE);
//    }
//
//    private void showMessage(String message) {
//        JOptionPane.showMessageDialog(
//            this, 
//            message, 
//            "Success", 
//            JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            try {
//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            
//            BankDashboard dashboard = new BankDashboard();
//            dashboard.setVisible(true);
//            dashboard.setLocationRelativeTo(null); // Center the window
//        });
//    }
//}
package ui;

import dao.AccountDaoImpl;
import model.Account;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BankDashboard extends JFrame {
    private AccountDaoImpl accountDao = new AccountDaoImpl();
    private JTable accountTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    // Color scheme
    private final Color BACKGROUND_COLOR = new Color(240, 245, 250); // Light blue-gray background
    private final Color BUTTON_COLOR = new Color(70, 130, 180); // Steel blue buttons
    private final Color BUTTON_TEXT_COLOR = Color.WHITE;

    public BankDashboard() {
        setupUI();
        loadAccounts();
    }

    private void setupUI() {
        setTitle("Simple Bank Manager");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout(10, 10));

        // Create main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);

        // Search panel at top
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.setBackground(BACKGROUND_COLOR);
        searchField = new JTextField();
        JButton searchButton = createStyledButton("Search");
        searchButton.addActionListener(e -> searchAccounts());
        
        JPanel searchInputPanel = new JPanel(new BorderLayout(5, 5));
        searchInputPanel.setBackground(BACKGROUND_COLOR);
        searchInputPanel.add(new JLabel("Search by Name or Account #:"), BorderLayout.WEST);
        searchInputPanel.add(searchField, BorderLayout.CENTER);
        searchInputPanel.add(searchButton, BorderLayout.EAST);
        
        searchPanel.add(searchInputPanel, BorderLayout.NORTH);
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"Account #", "Name", "Balance (NPR)"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        accountTable = new JTable(tableModel);
        accountTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        accountTable.getTableHeader().setReorderingAllowed(false);
        
        // Style the table
        accountTable.setRowHeight(25);
        accountTable.setShowGrid(true);
        accountTable.setGridColor(Color.LIGHT_GRAY);
        accountTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        accountTable.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(accountTable);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 0, 5, 0),
            BorderFactory.createLineBorder(Color.GRAY)
        ));
        scrollPane.setBackground(BACKGROUND_COLOR);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        // Create buttons with consistent color
        JButton addButton = createStyledButton("Add Account");
        JButton deleteButton = createStyledButton("Delete Account");
        JButton depositButton = createStyledButton("Deposit");
        JButton withdrawButton = createStyledButton("Withdraw");
        JButton transferButton = createStyledButton("Transfer");

        // Action listeners
        addButton.addActionListener(e -> addAccount());
        deleteButton.addActionListener(e -> deleteAccount());
        depositButton.addActionListener(e -> deposit());
        withdrawButton.addActionListener(e -> withdraw());
        transferButton.addActionListener(e -> transfer());

        // Add buttons to panel
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(transferButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BUTTON_COLOR.darker()),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return button;
    }

    private void loadAccounts() {
        tableModel.setRowCount(0);
        List<Account> accounts = accountDao.findAll();

        for (Account account : accounts) {
            Object[] rowData = {
                account.getAccountNumber(),
                account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName(),
                String.format("%.2f", account.getBalance())
            };
            tableModel.addRow(rowData);
        }
    }

    private void searchAccounts() {
        String searchText = searchField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            loadAccounts();
            return;
        }

        tableModel.setRowCount(0);
        List<Account> accounts = accountDao.findAll();

        for (Account account : accounts) {
            String accountNumber = String.valueOf(account.getAccountNumber());
            String fullName = (account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName()).toLowerCase();
            
            if (accountNumber.contains(searchText) || fullName.contains(searchText)) {
                Object[] rowData = {
                    account.getAccountNumber(),
                    account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName(),
                    String.format("%.2f", account.getBalance())
                };
                tableModel.addRow(rowData);
            }
        }
    }

    private void addAccount() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(BACKGROUND_COLOR);

        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField mobileField = new JTextField();
        JTextField balanceField = new JTextField();

        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Mobile:"));
        panel.add(mobileField);
        panel.add(new JLabel("Initial Balance (NPR):"));
        panel.add(balanceField);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Add New Account",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                double balance = Double.parseDouble(balanceField.getText());
                Customer customer = new Customer(firstNameField.getText(), lastNameField.getText(), mobileField.getText());
                int accountNumber = accountDao.findAll().size() + 1;
                Account newAccount = new Account(customer, accountNumber, balance);
                accountDao.addAccount(newAccount);
                loadAccounts();
                showMessage("Account added successfully!");
            } catch (Exception e) {
                showError("Error: " + e.getMessage());
            }
        }
    }

    private void deleteAccount() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to delete this account?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                int accountNumber = (int) tableModel.getValueAt(selectedRow, 0);
                accountDao.deleteAccount(accountNumber);
                loadAccounts();
                showMessage("Account deleted successfully!");
            }
        } else {
            showError("Please select an account to delete.");
        }
    }

    private void deposit() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow >= 0) {
            int accountNumber = (int) tableModel.getValueAt(selectedRow, 0);
            String amountStr = JOptionPane.showInputDialog(
                this, 
                "Enter deposit amount (NPR):", 
                "Deposit", 
                JOptionPane.PLAIN_MESSAGE);
            
            if (amountStr != null && !amountStr.trim().isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (amount <= 0) {
                        showError("Amount must be positive.");
                        return;
                    }
                    
                    Account account = accountDao.findByAccountNumber(accountNumber);
                    account.setBalance(account.getBalance() + amount);
                    loadAccounts();
                    showMessage(String.format("Deposited %.2f NPR successfully!", amount));
                } catch (NumberFormatException e) {
                    showError("Invalid amount format.");
                }
            }
        } else {
            showError("Please select an account to deposit into.");
        }
    }

    private void withdraw() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow >= 0) {
            int accountNumber = (int) tableModel.getValueAt(selectedRow, 0);
            String amountStr = JOptionPane.showInputDialog(
                this, 
                "Enter withdrawal amount (NPR):", 
                "Withdraw", 
                JOptionPane.PLAIN_MESSAGE);
            
            if (amountStr != null && !amountStr.trim().isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (amount <= 0) {
                        showError("Amount must be positive.");
                        return;
                    }
                    
                    Account account = accountDao.findByAccountNumber(accountNumber);
                    if (account.getBalance() >= amount) {
                        account.setBalance(account.getBalance() - amount);
                        loadAccounts();
                        showMessage(String.format("Withdrew %.2f NPR successfully!", amount));
                    } else {
                        showError("Insufficient funds.");
                    }
                } catch (NumberFormatException e) {
                    showError("Invalid amount format.");
                }
            }
        } else {
            showError("Please select an account to withdraw from.");
        }
    }

    private void transfer() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(BACKGROUND_COLOR);

        JTextField fromAccountField = new JTextField();
        JTextField toAccountField = new JTextField();
        JTextField amountField = new JTextField();

        panel.add(new JLabel("From Account #:"));
        panel.add(fromAccountField);
        panel.add(new JLabel("To Account #:"));
        panel.add(toAccountField);
        panel.add(new JLabel("Amount (NPR):"));
        panel.add(amountField);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Transfer Funds",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int fromAccountNumber = Integer.parseInt(fromAccountField.getText());
                int toAccountNumber = Integer.parseInt(toAccountField.getText());
                double amount = Double.parseDouble(amountField.getText());

                if (amount <= 0) {
                    showError("Amount must be positive.");
                    return;
                }

                Account fromAccount = accountDao.findByAccountNumber(fromAccountNumber);
                Account toAccount = accountDao.findByAccountNumber(toAccountNumber);

                if (fromAccount != null && toAccount != null) {
                    if (fromAccount.getBalance() >= amount) {
                        fromAccount.setBalance(fromAccount.getBalance() - amount);
                        toAccount.setBalance(toAccount.getBalance() + amount);
                        loadAccounts();
                        showMessage(String.format("Transferred %.2f NPR successfully!", amount));
                    } else {
                        showError("Insufficient funds in source account.");
                    }
                } else {
                    showError("One or both account numbers are invalid.");
                }
            } catch (NumberFormatException e) {
                showError("Please enter valid numbers for account numbers and amount.");
            }
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
            this, 
            message, 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(
            this, 
            message, 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            BankDashboard dashboard = new BankDashboard();
            dashboard.setVisible(true);
            dashboard.setLocationRelativeTo(null);
        });
    }
}                                                                                                                                                                                                                                                                                          