package main;

import service.BankService;
import service.TransactionService;
import dao.CustomerDAO;
import model.Customer;
import java.util.Scanner;
import java.util.List;

public class BankManagementSystem {
    private static BankService bankService = new BankService();
    private static TransactionService transactionService = new TransactionService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to Bank Management System ===");

        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    transfer();
                    break;
                case 6:
                    checkBalance();
                    break;
                case 7:
                    viewStatement();
                    break;
                case 8:
                    viewAllCustomers();
                    break;
                case 9:
                    System.out.println("Thank you for using Bank Management System!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create New Customer");
        System.out.println("2. Create New Account");
        System.out.println("3. Deposit Money");
        System.out.println("4. Withdraw Money");
        System.out.println("5. Transfer Money");
        System.out.println("6. Check Balance");
        System.out.println("7. View Account Statement");
        System.out.println("8. View All Customers");
        System.out.println("9. Exit");
    }

    private static void createCustomer() {
        System.out.println("\n=== Create New Customer ===");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        if (bankService.createCustomer(name, email, phone, address)) {
            System.out.println("Customer created successfully!");
        } else {
            System.out.println("Failed to create customer!");
        }
    }

    private static void createAccount() {
        System.out.println("\n=== Create New Account ===");

        viewAllCustomers();

        System.out.print("\nEnter account number: ");
        String accountNumber = scanner.nextLine();

        int customerId = getIntInput("Enter customer ID (from the list above): ");
        System.out.print("Enter account type (SAVINGS/CURRENT/FIXED_DEPOSIT): ");
        String accountType = scanner.nextLine();
        double initialDeposit = getDoubleInput("Enter initial deposit: ");

        if (bankService.createAccount(accountNumber, customerId, accountType, initialDeposit)) {
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Failed to create account! Please check if customer ID exists.");
        }
    }

    private static void deposit() {
        System.out.println("\n=== Deposit Money ===");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        double amount = getDoubleInput("Enter amount to deposit: ");

        if (bankService.deposit(accountNumber, amount)) {
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Deposit failed!");
        }
    }

    private static void withdraw() {
        System.out.println("\n=== Withdraw Money ===");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        double amount = getDoubleInput("Enter amount to withdraw: ");

        if (bankService.withdraw(accountNumber, amount)) {
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Withdrawal failed! Check balance or account number.");
        }
    }

    private static void transfer() {
        System.out.println("\n=== Transfer Money ===");
        System.out.print("Enter source account number: ");
        String fromAccount = scanner.nextLine();
        System.out.print("Enter target account number: ");
        String toAccount = scanner.nextLine();
        double amount = getDoubleInput("Enter amount to transfer: ");

        if (bankService.transfer(fromAccount, toAccount, amount)) {
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Transfer failed! Check balances or account numbers.");
        }
    }

    private static void checkBalance() {
        System.out.println("\n=== Check Balance ===");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        double balance = bankService.getBalance(accountNumber);
        if (balance >= 0) {
            System.out.printf("Current balance: %.2f\n", balance);
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void viewStatement() {
        System.out.println("\n=== View Account Statement ===");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        transactionService.printAccountStatement(accountNumber);
    }

    private static void viewAllCustomers() {
        System.out.println("\n=== All Customers ===");
        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> customers = customerDAO.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found!");
            return;
        }

        System.out.println("ID  | Name           | Email           | Phone       ");
        System.out.println("----|----------------|-----------------|-------------");
        for (Customer customer : customers) {
            System.out.printf("%-3d | %-14s | %-15s | %-11s\n",
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getEmail(),
                    customer.getPhone());
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input! Please enter a number: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input! Please enter a number: ");
            scanner.next();
        }
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }
}