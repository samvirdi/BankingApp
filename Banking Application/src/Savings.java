import javax.swing.JOptionPane;

public class Savings extends Account {

	private double fee;
	
	public Savings(String customerID) {
		super(customerID);
		this.fee = 2.5;
	}
	
	public Savings(String customerID, String accountID, double balance, double fee) {
		super(customerID, accountID, balance);
		this.fee = fee;
	}
	
	public boolean withdraw(double amount) {
		double balance = getBalance();
		double newBalance = balance - amount;

		if (newBalance < 0) {
			return false; // user tries to take out more money than they have
		}
		else if (newBalance < 2000) {
			//if the amount user is trying to take out is less than $2000, deduct a fee if $2.50
			setBalance(newBalance - this.fee);
			 //showing the total amount of balance after the transaction
			return true;
		}
		else {
			setBalance (newBalance);
			return true;
		}
	}
	
	public double getFee() {
		return this.fee;
	}
	
	public void setFee(double fee) {
		this.fee = fee;
	}

	public static void main(String[] args) {
		String id = "543234";
		Savings sav = new Savings(id);
		
		sav.deposit(2500);
		System.out.println("your balance in the sav account is: " + sav.getBalance());
		
		double amt = Double.parseDouble(JOptionPane.showInputDialog(null,
				"How Much Do You want to Withrawal"));
		sav.withdraw(amt);
		System.out.print("Your remaining amount is: " + sav.getBalance());
	}

}
