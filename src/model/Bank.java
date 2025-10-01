package model;

public class Bank {
    private String bankName;
    private String bankCode;
    private String address;

    public Bank(String bankName, String bankCode, String address) {
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.address = address;
    }

    // Getters and Setters
    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getBankCode() { return bankCode; }
    public void setBankCode(String bankCode) { this.bankCode = bankCode; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}