import javax.swing.JOptionPane;

public class Chequing extends Account {

	private double fee;

	public Chequing(String customerID) {
		super(customerID);
		this.fee = 1.25;
	}
	
	public Chequing(String customerID, String accountID, double balance, double fee) {
		super(customerID, accountID, balance);
		this.fee = fee;
	}

	public boolean withdraw(double amount) {
		double balance = getBalance();
		double newBalance = balance - amount;

		if (newBalance < 0) {
			JOptionPane.showMessageDialog(null, "Error. You're broke.");
			return false; // user tries to take out more money than they have
		}
		// double check with campos if it should be (balance - amt) or (balance)
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
		Chequing cheq = new Chequing(id);
		
		cheq.deposit(2500);
		System.out.println("your balance in the cheq account is: " + cheq.getBalance());
		
		double amt = Double.parseDouble(JOptionPane.showInputDialog(null,
				"How Much Do You want to Withrawal"));
		cheq.withdraw(amt);
		System.out.print("Your remaining amount is: " + cheq.getBalance());

	}
	
}
