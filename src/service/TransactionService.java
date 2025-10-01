package service;

import model.Transaction;
import dao.TransactionDAO;
import java.util.List;

public class TransactionService {
    private TransactionDAO transactionDAO;

    public TransactionService() {
        this.transactionDAO = new TransactionDAO();
    }

    public List<Transaction> getAccountStatement(String accountNumber) {
        return transactionDAO.getTransactionsByAccount(accountNumber);
    }

    public void printAccountStatement(String accountNumber) {
        List<Transaction> transactions = getAccountStatement(accountNumber);

        System.out.println("\n=== Account Statement for " + accountNumber + " ===");
        System.out.println("Transaction ID | Type      | Amount   | Date                | Description");
        System.out.println("-------------------------------------------------------------------");

        for (Transaction transaction : transactions) {
            System.out.printf("%-14d | %-9s | %8.2f | %-19s | %s\n",
                    transaction.getTransactionId(),
                    transaction.getTransactionType(),
                    transaction.getAmount(),
                    transaction.getTransactionDate(),
                    transaction.getDescription());
        }
    }
}