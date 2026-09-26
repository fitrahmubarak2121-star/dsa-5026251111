package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactionsList = new LinkedList<>();
        LinkedList<String[]> customersList = new LinkedList<>();

        try {
            File file = new File("src/lw02/prelab/transactions.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                transactionsList.add(parts);

                boolean customerExists = false;
                for (String[] customer : customersList) {
                    if (customer[0].equals(parts[0])) {
                        customerExists = true;
                        break;
                    }
                }
                
                if (!customerExists) {
                    customersList.add(new String[]{parts[0], "0"});
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt tidak ditemukan. Periksa path file.");
            return;
        }

        Queue<String[]> transactionQueue = new LinkedList<>(transactionsList);
        
        Stack<String[]> failedTransactions = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] currentTx = transactionQueue.poll();
            String name = currentTx[0];
            String type = currentTx[1];
            int amount = Integer.parseInt(currentTx[2]);

            for (String[] customer : customersList) {
                if (customer[0].equals(name)) {
                    int currentBalance = Integer.parseInt(customer[1]);
                    
                    if (type.equals("DEPOSIT")) {
                        customer[1] = String.valueOf(currentBalance + amount);
                    } else if (type.equals("WITHDRAW")) {
                        if (currentBalance >= amount) {
                            customer[1] = String.valueOf(currentBalance - amount);
                        } else {
                            failedTransactions.push(currentTx);
                        }
                    }
                    break; 
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customersList) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println();

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}