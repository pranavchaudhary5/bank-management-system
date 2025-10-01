package model;

import java.time.LocalDateTime;

public class Account {
    private String accountNumber;
    private int customerId;
    private String accountType;
    private double balance;
    private LocalDateTime createdDate;
    private String status;

    public Account() {}

    public Account(String accountNumber, int customerId, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
        this.createdDate = LocalDateTime.now();
        this.status = "ACTIVE";
    }

    // Getters and Setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Account[Number: %s, Type: %s, Balance: %.2f, Status: %s]",
                accountNumber, accountType, balance, status);
    }
}