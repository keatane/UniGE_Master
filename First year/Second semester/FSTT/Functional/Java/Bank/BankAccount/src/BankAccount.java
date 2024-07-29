// create a class BankAccount
public class BankAccount {
    // create a field for the account number
    private String accountNumber;
    // create a field for the balance
    private double balance;

    public BankAccount(String accountNumber, double balance, String customerName, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }


    // create a method to deposit funds
    public void deposit(double depositAmount) {
        // add the deposit amount to the balance
        this.balance += depositAmount;
        // print the deposit amount and the new balance
        System.out.println("Deposit of " + depositAmount + " made. New balance is " + this.balance);
    }

    public double getBalance() {
        return balance;
    }
    
    // create a method to withdraw funds
    public void withdraw(double withdrawalAmount) {
        // check if the balance is greater than the withdrawal amount
        if (this.balance - withdrawalAmount < 0) {
            // print a message if the balance is not enough
            System.out.println("Only " + this.balance + " available. Withdrawal not processed");
        } else {
            // subtract the withdrawal amount from the balance
            this.balance -= withdrawalAmount;
            // print the withdrawal amount and the new balance
            System.out.println("Withdrawal of " + withdrawalAmount + " processed. Remaining balance = " + this.balance);
        }
    }

    // create a method to transfer something
    public void transfer(double transferAmount) {
        // check if the balance is greater than the transfer amount
        if (this.balance - transferAmount < 0) {
            // print a message if the balance is not enough
            System.out.println("Only " + this.balance + " available. Transfer not processed");
        } else {
            // subtract the transfer amount from the balance
            this.balance -= transferAmount;
            // print the transfer amount and the new balance
            System.out.println("Transfer of " + transferAmount + " processed. Remaining balance = " + this.balance);
        }
    }

    // create a method to set the account number
    public void setAccountNumber(String accountNumber) {
        // set the account number
        this.accountNumber = accountNumber;
    }

    // create a method to get the account number
    public String getAccountNumber() {
        // return the account number
        return this.accountNumber;
    }
}