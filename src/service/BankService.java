package service;

import model.*;
import dao.*;

public class BankService {
    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;

    public BankService() {
        this.customerDAO = new CustomerDAO();
        this.accountDAO = new AccountDAO();
        this.transactionDAO = new TransactionDAO();
    }

    public boolean createCustomer(String name, String email, String phone, String address) {
        Customer customer = new Customer(name, email, phone, address);
        return customerDAO.addCustomer(customer);
    }

    public boolean createAccount(String accountNumber, int customerId, String accountType, double initialDeposit) {
        Customer customer = customerDAO.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer with ID " + customerId + " does not exist!");
            return false;
        }

        Account account = new Account(accountNumber, customerId, accountType, initialDeposit);
        boolean accountCreated = accountDAO.createAccount(account);

        if (accountCreated && initialDeposit > 0) {
            Transaction transaction = new Transaction(accountNumber, "DEPOSIT", initialDeposit, "Initial deposit");
            transactionDAO.addTransaction(transaction);
        }

        return accountCreated;
    }

    public boolean deposit(String accountNumber, double amount) {
        Account account = accountDAO.getAccountByNumber(accountNumber);
        if (account != null) {
            double newBalance = account.getBalance() + amount;
            if (accountDAO.updateAccountBalance(accountNumber, newBalance)) {
                Transaction transaction = new Transaction(accountNumber, "DEPOSIT", amount, "Cash deposit");
                return transactionDAO.addTransaction(transaction);
            }
        }
        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = accountDAO.getAccountByNumber(accountNumber);
        if (account != null && account.getBalance() >= amount) {
            double newBalance = account.getBalance() - amount;
            if (accountDAO.updateAccountBalance(accountNumber, newBalance)) {
                Transaction transaction = new Transaction(accountNumber, "WITHDRAWAL", amount, "Cash withdrawal");
                return transactionDAO.addTransaction(transaction);
            }
        }
        return false;
    }

    public boolean transfer(String fromAccount, String toAccount, double amount) {
        Account sourceAccount = accountDAO.getAccountByNumber(fromAccount);
        Account targetAccount = accountDAO.getAccountByNumber(toAccount);

        if (sourceAccount != null && targetAccount != null && sourceAccount.getBalance() >= amount) {
            double newSourceBalance = sourceAccount.getBalance() - amount;
            double newTargetBalance = targetAccount.getBalance() + amount;

            if (accountDAO.updateAccountBalance(fromAccount, newSourceBalance) &&
                    accountDAO.updateAccountBalance(toAccount, newTargetBalance)) {

                Transaction withdrawal = new Transaction(fromAccount, "WITHDRAWAL", amount,
                        "Transfer to account " + toAccount);
                Transaction deposit = new Transaction(toAccount, "DEPOSIT", amount,
                        "Transfer from account " + fromAccount);

                return transactionDAO.addTransaction(withdrawal) && transactionDAO.addTransaction(deposit);
            }
        }
        return false;
    }

    public double getBalance(String accountNumber) {
        Account account = accountDAO.getAccountByNumber(accountNumber);
        return account != null ? account.getBalance() : -1;
    }
}