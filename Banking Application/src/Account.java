import javax.swing.JOptionPane;

public class Account {

	//declares variables for account balance, account ID and customer ID
	private double balance;
	private String accountID;
	private String customerID;
	
	//constructor with Customer variable
	public Account(String customerID) {
		this.balance = 0;
		this.accountID = (Customer.generateID(9));
		this.customerID = customerID;
	}
	
	public Account(String customerID, String accountID, double balance) {
		this.customerID = customerID;
		this.accountID = accountID;
		this.balance = balance;
	}
	   
	//allows money to be deposited to account
	public void deposit(double amount) {
		balance += amount;
	}
	   
	//checks if money is available in the account, if so, removes that amount from the account.
	public boolean withdraw(double amount) {
		if (balance > amount) { // Checks if the amount can be withdrawn
			balance -= amount; // updates balance
			return true; // and returns true if it is possible
		}
		return false;
	}
	   
	   
	public double getBalance() {
		return this.balance;
	}
	
	public String getAccountID() {
		return this.accountID;
	}
	
	public String getCustomerID() {
		return this.customerID;
	}
	   
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public static void main(String[] args) {
		Customer customer = new Customer ("test,Test2,test3");
		Account account = new Account(customer.getCustomerID());
		
		JOptionPane.showMessageDialog(null, account.getAccountID());
		System.out.println(account.getBalance());
		
		account.deposit(1500.15);
		System.out.println(account.getBalance());
		
		account.withdraw(400.1);
		System.out.println(account.getBalance());
		
		if (!account.withdraw(1200)){
			System.out.println("Could not withdraw");
		}
	}

}
