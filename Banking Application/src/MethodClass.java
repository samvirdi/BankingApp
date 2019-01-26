import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

//Author: Samandeep Singh Virdi
//Date: Dec,2016
//Description: This is a class that contains methods for the user to use!

//METHOD LIST
//public MethodClass() 
//public static void main(String[] args)
//public void printReceipt (String customerName, String customerID,double savBal, double savFee
//	,double cheqBal, double cheqFee,double amount,boolean depOrWd,boolean cheqOrSav)
//
//public boolean yesOrNoDialogBox (String question, String title)
//public void writeToFile (String fileName, String information){
public class MethodClass {
	public MethodClass() {
		// TODO Auto-generated constructor stub
	}

	//SELF TESTING MAIN
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create the obect
		MethodClass methods = new MethodClass();
		if (methods.yesOrNoDialogBox("Do You Want To Test This Class?", "TESTING")){
			//print receipt tests writeToFile !! 
			//preset values!
			methods.printReceipt("Samandeep","123456",15000.0,2.5,10000.0,1.25,500.0,true,true);
		}
	}

	public void printReceipt (String customerName, String customerID,double savBal, double savFee
			,double cheqBal, double cheqFee,double amount,boolean depOrWd,boolean cheqOrSav){
		//Create and initialize variables for the data and file chooser
		Date timeStamp = new java.util.Date();
		JFileChooser chooser = new JFileChooser();
		//set the file name as the customer name 
		chooser.setSelectedFile(new File (customerName + "'s Transaction.txt"));
		//int for response 
		int response = chooser.showSaveDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			//if user presses yes then creates the file 
			//string to save the data
			String data = "";
			//format the information into the data String
			data = "Transaction Date: " + timeStamp + "\n" + 
					"Customer ID: " + customerID+ "\n" + 
					"Customer Name: " + customerName+ "\n"; 
			//if depOrWd is true then it is deposited
			if (depOrWd == true){
				if (cheqOrSav == true){
					//if cheqOrSav is true then its deposit into chequings
					data +="The Amount Deposited Into Chequings: $" + String.format("%.2f",amount) + "\n";
				}
				else if (cheqOrSav == false){
					//if cheqOrSav is false then its deposit into savings
					data+="The Amount Deposited Into Savings: $" + String.format("%.2f",amount)+ "\n";
				}
			}
			//if depOrWd is false then its withdraw
			else if (depOrWd == false){
				if (cheqOrSav == true){
					//if cheqOrSav is true then its withdraw from chequings
					data +="The Amount Withdrawn From Chequings: $" + String.format("%.2f",amount)+ "\n";
				}
				else if (cheqOrSav == false){
					//if cheqOrSav is true then its withdraw from savings
					data+="The Amount Withdrawn From Savings: $" + String.format("%.2f",amount) + "\n";
				}
			}	
			//format data again
			data += "The Balance Of Your Accounts Are:\n" + "Savings: $" + String.format("%.2f",savBal) + "\nChequings: $" +String.format("%.2f",cheqBal) +
					"\nSavings Fee $" + savFee 
					+ "\nChequings Fee: $" + cheqFee;
			//call write to file to create the receipt
			writeToFile(chooser.getSelectedFile()+".txt", data);
			JOptionPane.showMessageDialog(null, "Receipt Created");
		}

	}
	public boolean yesOrNoDialogBox (String question, String title){
		//Asks user for a yes or no question 
		String[] yesOrNo = new String[] {"Yes", "No"};
		int response = JOptionPane.showOptionDialog(null, question, title,
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, yesOrNo, yesOrNo[0]);
		if (response == 0){
			//if yes return true
			return true;
		}
		else {
			//if no return false
			return false;
		}
	}
	public void writeToFile (String fileName, String information){
		try{
			//create the printwriter as fw and the file it writes to is fileName
			PrintWriter fw = new PrintWriter (new FileWriter (fileName));
			//writes information and closes the file 
			fw.println (information);
			fw.close();
			//catch for error
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ERROR:FILE NOT FOUND");
		}
	}
}
