import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AccountList {
	
	private int size;
	private ArrayList<Chequing> cList;
	private ArrayList<Savings> sList;
	
	public AccountList(String fileName) throws NumberFormatException, IOException {
		this.cList = new ArrayList<Chequing>();
		this.sList = new ArrayList<Savings>();
		readFromFile(fileName);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		AccountList list = new AccountList("AccountRecords.txt");
		System.out.println(list.print());
		System.out.println(list.changeC("123123", "3000,3"));
		System.out.println(list.getChequing(0).getBalance());
		System.out.println(list.print());
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getChequing(String customerID) {
		return searchC(customerID);
	}
	
	public int getSavings(String customerID) {
		return searchS(customerID);
	}
	
	public int searchC(String customerID) {
		for (int i = 0; i < cList.size(); i++) {
			if (customerID.equalsIgnoreCase(cList.get(i).getCustomerID())) {
				return i; // found it
			}
		}
		return -1;
	}
	
	public int searchS(String customerID) {
		for (int i = 0; i < sList.size(); i++) {
			if (customerID.equalsIgnoreCase(sList.get(i).getCustomerID())) {
				return i; // found it
			}
		}
		return -1;
	}
	
	public ArrayList<Chequing> getCList() {
		return cList;
	}
	
	public ArrayList<Savings> getSList() {
		return sList;
	}
	
	public String toStringC(Chequing chequing) {
		return chequing.getAccountID() + ","
				+ chequing.getBalance() + ","
				+ chequing.getFee();
	}
	
	public String toStringS(Savings savings) {
		return savings.getAccountID() + ","
				+ savings.getBalance() + ","
				+ savings.getFee();
	}
	
	public String print() {
		String str = cList.size() + "\n";
		
		for (int i = 0; i < cList.size(); i++) {
			Chequing chequing = cList.get(i);
			Savings savings = sList.get(i);

			String customerID = chequing.getCustomerID();
			str += customerID + ";" + toStringC(chequing) + ";" + toStringS(savings) + "\n";
		}
		
		return str;
	}
	
	public void insertC(Chequing chequing) {
		cList.add(chequing);
		//setSize(getSize() + 1);
	}
	
	public void insertS(Savings savings) {
		sList.add(savings);
	}
	
	public boolean changeC(String accountID, String info) {
		int index = searchAccountIDC(accountID);
		String data[] = info.split(",");
		
		if (index > -1) {
			Chequing chequing = getChequing(index);
			chequing.setBalance(Double.parseDouble(data[0]));
			chequing.setFee(Double.parseDouble(data[1]));
			return true;
		}
		
		return false;
	}
	
	public boolean changeS(String accountID, String info) {
		int index = searchAccountIDS(accountID);
		String data[] = info.split(",");
		
		if (index > -1) {
			Savings savings = getSavings(index);
			savings.setBalance(Double.parseDouble(data[0]));
			savings.setFee(Double.parseDouble(data[1]));
			return true;
		}
		
		return false;
	}
	
	public boolean deleteC(String accountID) {
		int index = searchAccountIDC(accountID);
		
		if (index > -1) {
			cList.remove(index);
			return true;
		}
		
		return false;
	}
	
	public boolean deleteS(String accountID) {
		int index = searchAccountIDS(accountID);
		
		if (index > -1) {
			sList.remove(index);
			return true;
		}
		
		return false;
	}
	
	public ArrayList<Chequing> getListC() {
		return this.cList;
	}
	
	public ArrayList<Savings> getListS() {
		return this.sList;
	}
	
	public Chequing getChequing(int index) {
		return this.cList.get(index);
	}
	
	public Savings getSavings(int index) {
		return this.sList.get(index);
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void readFromFile(String fileName) throws NumberFormatException, IOException {
		// get file name to read from
		/* String fileName = JOptionPane.showInputDialog(
				"Enter file name to read from:",
				"AccountRecords.txt"); */
		
		// set up file to read from
		FileReader file = new FileReader(fileName);
		BufferedReader input = new BufferedReader(file);
		
		this.size = Integer.parseInt(input.readLine());
		
		for (int i = 0; i < size; i++) {
			// get and process record
			String info = input.readLine();
			String[] infoArray = info.split(";");
			
			String customerID = infoArray[0];
			String[] cArray = infoArray[1].split(",");
			String[] sArray = infoArray[2].split(",");
			
			String cAccountID = cArray[0];
			double cBalance = Double.parseDouble(cArray[1]);
			double cFee = Double.parseDouble(cArray[2]);
			
			String sAccountID = sArray[0];
			double sBalance = Double.parseDouble(sArray[1]);
			double sFee = Double.parseDouble(sArray[2]);
			
			Chequing chequing = new Chequing(customerID, cAccountID, cBalance, cFee);
			insertC(chequing);

			Savings savings = new Savings(customerID, sAccountID, sBalance, sFee);
			insertS(savings);
		}
		
		input.close(); // close input stream
	}
	
	public int searchAccountIDC(String accountID) {
		for (int i = 0; i < cList.size(); i++) {
			if (accountID.equalsIgnoreCase(cList.get(i).getAccountID())) {
				return i; // found it
			}
		}
		return -1;
	}
	
	public int searchAccountIDS(String accountID) {
		for (int i = 0; i < sList.size(); i++) {
			if (accountID.equalsIgnoreCase(sList.get(i).getAccountID())) {
				return i; // found it
			}
		}
		return -1;
	}

}
