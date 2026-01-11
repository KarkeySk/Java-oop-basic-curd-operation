//package dao;
//
//import model.Transaction;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TransactionDao {
//    private static final String CSV_FILE = "src/data/transactions.csv";
//
//    public void saveTransaction(Transaction t) {
//        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE, true))) {
//            pw.println(String.join(",",
//                String.valueOf(t.getFromAccount()),
//                String.valueOf(t.getToAccount()),
//                String.valueOf(t.getAmount()),
//                t.getType()
//            ));
//        } catch (IOException e) {
//            System.out.println("Error saving transaction: " + e.getMessage());
//        }
//    }
//
//    public List<String> getTransactionHistory(int accountNumber) {
//        List<String> history = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(",");
//                if (data.length >= 3) {  // Check minimum required fields
//                    int fromAcc = Integer.parseInt(data[0]);
//                    int toAcc = Integer.parseInt(data[1]);
//                    
//                    if (fromAcc == accountNumber || toAcc == accountNumber) {
//                        // Format: "TYPE: $AMOUNT from ACC1 to ACC2"
//                        history.add(String.format("%s: $%.2f from %d to %d",
//                            data[3],  // type
//                            Double.parseDouble(data[2]),  // amount
//                            fromAcc,
//                            toAcc
//                        ));
//                    }
//                }
//            }
//        } catch (IOException | NumberFormatException e) {
//            System.out.println("Error reading transactions: " + e.getMessage());
//        }
//        return history;
//    }
//}
package dao;

import model.Transaction;
import java.io.*;

public class TransactionDao {
    private static final String CSV_FILE = "src/data/transactions.csv";

    public void saveTransaction(Transaction t) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE, true))) {
            pw.println(String.join(",",
                String.valueOf(t.getFromAccount()),
                String.valueOf(t.getToAccount()),
                String.valueOf(t.getAmount()),
                t.getType()
            ));
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }
}