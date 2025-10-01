
## 🎯 Layer Responsibilities

### **Model Layer** (`src/model/`)
- **Purpose**: Data representation
- **Files**:
  - `Account.java` - Bank account details
  - `Customer.java` - Customer information
  - `Transaction.java` - Transaction records
  - `Bank.java` - Bank entity

### **DAO Layer** (`src/dao/`) - Data Access Object
- **Purpose**: Database operations
- **Files**:
  - `DatabaseConnection.java` - MySQL connection setup
  - `AccountDAO.java` - Account database operations
  - `CustomerDAO.java` - Customer database operations
  - `TransactionDAO.java` - Transaction database operations

### **Service Layer** (`src/service/`)
- **Purpose**: Business logic and rules
- **Files**:
  - `BankService.java` - Core banking operations
  - `TransactionService.java` - Transaction handling

### **Main Layer** (`src/main/`)
- **Purpose**: User interface and program entry
- **Files**:
  - `BankManagementSystem.java` - Main class with menu system
