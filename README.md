# Bank Management System - Java

## Setup Instructions:

### 1. Database Setup:
- Open MySQL Workbench
- Run `database/bank_schema.sql` to create database

### 2. Project Setup:
1. Open IntelliJ IDEA
2. Create New Project → Java
3. Copy the `src` folder into your project
4. Mark `src` as Sources Root (right-click → Mark Directory as → Sources Root)

### 3. Add MySQL Connector:
1. Download: https://dev.mysql.com/downloads/connector/j/
2. Choose "Platform Independent" → Download ZIP
3. Extract and get the JAR file
4. In IntelliJ: File → Project Structure → Libraries → + → Java → Select the JAR

### 4. Configure Database:
- Open `src/dao/DatabaseConnection.java`
- Update password: `YOUR_MYSQL_PASSWORD`

### 5. Run:
- Open `src/main/BankManagementSystem.java`
- Click green Run button

## Features:
- Create Customers & Accounts
- Deposit/Withdraw Money  
- Transfer Between Accounts
- View Transactions
- Check Balances
