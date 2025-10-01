-- Drop the entire database and recreate it
DROP DATABASE IF EXISTS bank_management;
CREATE DATABASE bank_management;
USE bank_management;

-- Customers table
CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    address TEXT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Accounts table
CREATE TABLE accounts (
    account_number VARCHAR(20) PRIMARY KEY,
    customer_id INT,
    account_type ENUM('SAVINGS', 'CURRENT', 'FIXED_DEPOSIT') NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0.00,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ACTIVE', 'INACTIVE', 'SUSPENDED') DEFAULT 'ACTIVE',
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

-- Transactions table
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20),
    transaction_type ENUM('DEPOSIT', 'WITHDRAWAL', 'TRANSFER') NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    FOREIGN KEY (account_number) REFERENCES accounts(account_number) ON DELETE CASCADE
);

-- Insert sample data
INSERT INTO customers (name, email, phone, address) VALUES 
('John Doe', 'john.doe@email.com', '1234567890', '123 Main St, City'),
('Jane Smith', 'jane.smith@email.com', '0987654321', '456 Oak Ave, Town');

INSERT INTO accounts (account_number, customer_id, account_type, balance) VALUES 
('ACC001', 1, 'SAVINGS', 5000.00),
('ACC002', 2, 'CURRENT', 10000.00);

INSERT INTO transactions (account_number, transaction_type, amount, description) VALUES 
('ACC001', 'DEPOSIT', 5000.00, 'Initial deposit'),
('ACC002', 'DEPOSIT', 10000.00, 'Initial deposit');

-- Verify data
SELECT 'Customers:' AS '';
SELECT * FROM customers;

SELECT 'Accounts:' AS '';
SELECT * FROM accounts;

SELECT 'Transactions:' AS '';
SELECT * FROM transactions;