//package readacc;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ReadAcc {
//    private static final String TRANSACTION_CSV = "src/data/transactions.csv";
//
//    public List<String> getTransactionHistory(int accountNumber) {
//        List<String> history = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTION_CSV))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(",");
//                if (data.length >= 4) {  // Check for all required fields
//                    int fromAcc = Integer.parseInt(data[0]);
//                    int toAcc = Integer.parseInt(data[1]);
//                    
//                    if (fromAcc == accountNumber || toAcc == accountNumber) {
//                        history.add(formatTransaction(data));
//                    }
//                }
//            }
//        } catch (IOException | NumberFormatException e) {
//            System.out.println("Error reading transactions: " + e.getMessage());
//        }
//        return history;
//    }
//
//    private String formatTransaction(String[] data) {
//        return String.format("%s: $%.2f from %d to %d",
//            data[3],  // type
//            Double.parseDouble(data[2]),  // amount
//            Integer.parseInt(data[0]),  // from account
//            Integer.parseInt(data[1])   // to account
//        );
//    }
//}
package readacc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAcc {
    private static final String TRANSACTION_CSV = "src/data/transactions.csv";

    public List<String> getTransactionHistory(int accountNumber) {
        List<String> history = new ArrayList<>();

        File file = new File(TRANSACTION_CSV);
        if (!file.exists()) {
            System.out.println("Transaction file not found: " + TRANSACTION_CSV);
            return history;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    int fromAcc = Integer.parseInt(data[0]);
                    int toAcc = Integer.parseInt(data[1]);

                    if (fromAcc == accountNumber || toAcc == accountNumber) {
                        history.add(formatTransaction(data));
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading transactions: " + e.getMessage());
        }

        return history;
    }

    private String formatTransaction(String[] data) {
        try {
            return String.format("%s: $%.2f from %d to %d",
                    data[3],
                    Double.parseDouble(data[2]),
                    Integer.parseInt(data[0]),
                    Integer.parseInt(data[1])
            );
        } catch (NumberFormatException e) {
            return "Invalid transaction data";
        }
    }
}
