import java.awt.BorderLayout;
import java.awt.Color;

import javax.imageio.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
/**
 * Author:Samandeep Singh Virdi
 * Date: Dec 2016
 * Description: This is the User Interface for the banking application
 * There are 4 classes which are different menus. 
 * USERNAME: campos
 * PASSWORD: dev
 */

//METHOD LIST
//public UserInterface (String fileName, String fileName2)
//public static void main(String[] args)
//public static MethodClass getMethods ()
//public static AccountList getAccountList()
//public static CustomerList getList ()
//public static String getAccFileName()
//public static String getCustFileName()
//public static void setList(CustomerList list)
//METHOD THAT STARTS THE APPLICATION!
public class UserInterface extends JFrame{
	// Private Data For All of the classes to use
	private static CustomerList list;
	private static AccountList accountList;
	private static MethodClass methods;
	private static String accFileName,custFileName;
	/**
	 * @param args
	 */  
	//Constructor To Construct the CustomerList,AccountList and MethodClass
	//Objects, Takes in 2 parameters for the files
	public UserInterface (String custfileName, String accfileName){
		try {
			// creating the objects that the other classes are going to use
			list = new CustomerList(custfileName);
			accountList = new AccountList(accfileName);
			methods = new MethodClass();
			//CATCH FOR ERRORS
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ERROR: Number Format Error");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, 
					"ERROR: File(s) Not Found\n Please Check Your File Format\n"
							+ "and The File Name!");
		}
	}
	//MAIN METHOD TO START THE APPLICATION (This tests the object and
	//starts the application)
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ask user for file names 
		custFileName = JOptionPane.showInputDialog(null, "Choose A Text File To Open For Customer Records","CustomerRecords.txt");
		accFileName = JOptionPane.showInputDialog(null, "Choose A Text File To Open For Account Records","AccountRecords.txt");
		//create UserInterface object
		UserInterface ui = new UserInterface(custFileName,accFileName);
		//Create the login menu to start the whole application
		loginMenu loginMenu = new loginMenu();
	}
	//Getter methods for the objects so the 
	//other classes can use them
	public static MethodClass getMethods (){
		return methods;
	}
	public static AccountList getAccountList() {
		return accountList;
	}
	public static CustomerList getCustList (){
		return list;
	}
	public static String getAccFileName(){
		return accFileName;
	}
	public static String getCustFileName(){
		return custFileName;
	}
	//setter method so other classes can set 
	//the list
	public static void setList(CustomerList list){
		UserInterface.list = list;
	}


}
//Author: Samandeep Singh Virdi 
//Date: Dec, 2016
//Description: This is the login page, it asks user
//for their username and password, then allows the 
//user to log in
//USERNAME: campos
//PASSWORD: dev

//METHOD LISTS
//public loginMenu()
//public static void main(String[] args)
//public void actionPerformed(ActionEvent e) 
class loginMenu extends JFrame implements ActionListener{
	//private data for the labels, buttons,text fields and background
	private JLabel lblPassword,lblUserName, lblBackground,lblLogo;     // declare various GUI components
	private JButton btnLogin, btnExit; 
	private JTextField username;
	private JPasswordField password;
	private ImageIcon background, logo;
	//constructor for loginMenu
	public loginMenu(){
		super("Login");  // title for the frame
		getContentPane().setLayout(null); //set content to null
		//intialize the labels
		lblPassword = new JLabel ("Enter Password:");
		lblUserName = new JLabel ("Enter Username:");
		//get the background and logo 
		try {
			background =
					new ImageIcon (ImageIO.read(getClass().getResource("Wallpaper.jpg")));
			logo = new ImageIcon (ImageIO.read(getClass().getResource("Bank.png")));
		} //Catch For file exception
		catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Background Picture and/or Logo Picture\n"
					+ "Not Found!");
		}
		//Initialize all other variables
		lblBackground = new JLabel (background);
		lblLogo = new JLabel (logo);
		password = new JPasswordField ();
		username = new JTextField();
		btnLogin = new JButton ("Login");
		btnExit = new JButton ("Exit");
		//Add button listener to buttons
		btnLogin.addActionListener(this);
		btnExit.addActionListener(this);

		//Set bounds for everything
		lblLogo.setBounds(411, 0, 202, 236);
		lblBackground.setBounds(0, 0, 1000, 1000);
		password.setBounds(389,368,276,38);
		username.setBounds(389,267,276,38);
		lblUserName.setBounds(389, 232, 126, 38);
		lblPassword.setBounds(389,320,126,50);
		btnLogin.setBounds(389,529,107,50);
		btnExit.setBounds(558,529,107,50);
		//add everything to the frame
		getContentPane().add(lblLogo);
		getContentPane().add(username);
		getContentPane().add(password);
		getContentPane().add(lblUserName);
		getContentPane().add(lblPassword);
		getContentPane().add(btnExit);
		getContentPane().add(btnLogin);
		getContentPane().add(lblBackground);
		//set size,action and visability
		setSize(1000,1000);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);


	}
	//SELF TESTING MAIN
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//tests out the login Menu
		loginMenu testMenu = new loginMenu();

	}
	//Method for the buttons
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnExit){
			//if exit button is pressed the program quits
			System.exit(0);
		}
		else if (e.getSource()==btnLogin){
			//if login button is pressed, program checks
			//if the username and password are valid, if 
			//valid the creates the listMenu!
			if (username.getText().equals("campos")&& "dev".equals(new String (password.getPassword()))) {
				try {
					ListMenu menu = new ListMenu();
					//catches for file and format!
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "ERROR: Number Format Error");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, 
							"ERROR: File(s) Not Found\n Please Check Your File Format\n"
									+ "and The File Name!");
				}
				//welcome user and dispose the login menu
				JOptionPane.showMessageDialog(null, "Welcome " + username.getText());
				dispose();
			}
			else {
				//if username and/or password is incorrect 
				//displays a message telling them its incorrect and
				//sets the text fields as empty
				JOptionPane.showMessageDialog(null, 
						"Incorrect Username or Password\nPlease Read Readme File\n to find the admin account");
				username.setText("");
				password.setText("");
			}
		}
	}

}
//Author: Samandeep Singh Virdi
//Date: Dec, 2016
//Description: This is the list menu where 
//all of the customer accounts can be seen 
//and buttons to create customers, edit customers
//and delete customers and edit accounts

//METHOD LISTS
//public ListMenu() throws NumberFormatException, IOException
//public static void main(String[] args) 
//public void actionPerformed(ActionEvent e)
//public void getCustomerID ()
class ListMenu extends JFrame implements ActionListener{
	// Private data for labels, buttons, table, scroll pane, 
	// background and strings 
	private JLabel lblTitle,lblBackground ;     
	private JButton btnLogOut,btnCreateNew,btnDelete,btnEditCust,
	btnEditAcc; 
	private JTable listOfAccounts;
	private JScrollPane scrollPane;
	private String customerID =  "", customerName= "";
	private String [] columnNames= {"Customer ID",
			"Customer Name",
			"Address", "Phone Number"};
	private String [][] customers;
	private ImageIcon background;
	//Constructor for the menu
	public ListMenu() throws NumberFormatException, IOException{
		super("List of Accounts");  // title for the frame
		//get the images
		try {
			background =
					new ImageIcon (ImageIO.read(getClass().getResource("Wallpaper.jpg")));
		} //catch for file exception
		catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Background Picture\n"
					+ "Not Found!");
		}
		//set layout to null
		getContentPane().setLayout(null);
		//initialize all components 
		lblTitle = new JLabel ("Customer Accounts");
		lblTitle.setFont(new Font("ARIAL",Font.ITALIC, 30));

		//use method from customerList to get the 
		//customers as a 2D array by using a method
		customers = UserInterface.getCustList().printToList();
		//create the jtable 
		listOfAccounts = new JTable(customers,columnNames);
		listOfAccounts.setRowSelectionAllowed(true);
		
		//CODE TAKEN FROM: http://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
		//Making a DefaultTableModel which takes in the customers and the columnNames 
		DefaultTableModel tableModel = new DefaultTableModel(customers, columnNames) {
			//overiding the isCellEditable method from DefaultTableModel
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false therefore they are not editable
		       return false;
		    }
		};
		//set the model for the JTable as the tableModel
		listOfAccounts.setModel(tableModel);
		//set the scroll pane as the jTable
		scrollPane = new JScrollPane(listOfAccounts);
		//initialize all other components 
		lblBackground = new JLabel (background);
		btnEditAcc= new JButton ("Edit Account");
		btnEditCust= new JButton ("Edit Customer");
		btnCreateNew = new JButton ("Create New Account");
		btnLogOut = new JButton ("Log Out");
		btnDelete = new JButton ("Delete Customer");
		//add action listeners to all buttons
		btnCreateNew.addActionListener(this);
		btnLogOut.addActionListener(this);
		btnDelete.addActionListener(this);
		btnEditCust.addActionListener(this);
		btnEditAcc.addActionListener(this);
		//set bounds for all components
		btnEditAcc.setBounds(855, 557, 100, 36);
		btnEditCust.setBounds(304,557,129,36);
		btnDelete.setBounds(595,557,129,36);
		btnCreateNew.setBounds(45, 557, 157, 36);
		btnLogOut.setBounds(6,716,64,36);
		lblTitle.setBounds(376, 0, 263, 50);
		scrollPane.setBounds(47, 109, 908, 436);
		lblBackground.setBounds(0, 0, 1000, 1000);

		//add all the components to the frame
		getContentPane().add(btnEditAcc);
		getContentPane().add(btnDelete);
		getContentPane().add(btnEditCust);
		getContentPane().add(btnCreateNew);
		getContentPane().add(scrollPane);
		getContentPane().add(btnLogOut);
		getContentPane().add(lblTitle);
		getContentPane().add(lblBackground);
		//set size, visability and action when closed
		setSize(1000,1000);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	//SELF TESTING MAIN
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Test the ListMenu Object
		try {
			ListMenu testMenu = new ListMenu();
			//Catch for errors
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ERROR: Number Format Error");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, 
					"ERROR: File(s) Not Found\n Please Check Your File Format\n"
							+ "and The File Name!");
		}

	}
	//method to add actions to buttons
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnLogOut){
			//if logout button is pressed as user if they 
			//are sure if they want to logout 
			if (UserInterface.getMethods().yesOrNoDialogBox( "Are You Sure You Want To Log Out?", "Log Out")){
				loginMenu login = new loginMenu();
				dispose();
			}
		}
		else if (e.getSource() == btnCreateNew){
			//if create new button is pressed then 
			//disposes the window and creates a 
			//createNew object
			dispose();
			createNew create = new createNew();
		}
		else if (e.getSource() == btnDelete){
			//if delete button is pressed asks user if they are sure
			if (UserInterface.getMethods().yesOrNoDialogBox("Are You Sure You Want To Delete This Customer\n and Their Accounts? ?", "Delete")){
				//if yes
				//get the customer iD 
				getCustomerID();
				//call method from customerList to delete
				if (UserInterface.getCustList().delete(customerID)){
					//if true it save the updated list as a string
					String str = UserInterface.getCustList().print();
					//get the customers account via the chequings and savings ID
					int chequingIndex = UserInterface.getAccountList().getChequing(customerID);
					int savingsIndex = UserInterface.getAccountList().getSavings(customerID);
					Chequing chequing = UserInterface.getAccountList().getChequing(chequingIndex);
					Savings savings = UserInterface.getAccountList().getSavings(savingsIndex);
					//Delete the account 
					UserInterface.getAccountList().deleteC(chequing.getAccountID());
					UserInterface.getAccountList().deleteS(savings.getAccountID());
					//save the updated account list as string
					String str2 = UserInterface.getAccountList().print();
					//write to the same files with updated records
					UserInterface.getMethods().writeToFile(UserInterface.getCustFileName(), str);
					UserInterface.getMethods().writeToFile(UserInterface.getAccFileName(), str2);
					//dispose and create new listMenu object to update the list
					dispose();
					try {
						ListMenu updatedList = new ListMenu();
						//Catch for errors
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "ERROR: Number Format Error");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, 
								"ERROR: File(s) Not Found\n Please Check Your File Format\n"
										+ "and The File Name!");
					}
				}
				else {
					//if delete failed
					JOptionPane.showMessageDialog(null, "Failed To Delete Customer");
				}
			}
		}
		else if (e.getSource() == btnEditCust){
			//if Edit Customer Button is pressed 
			//get the Customer ID
			getCustomerID();
			//create a string array to save seperate data
			String data[]= new String [3];
			//get the row selected by the user
			int row = listOfAccounts.getSelectedRow();
			//populate the array with the name, address and number
			data[0] = (String) listOfAccounts.getModel().getValueAt(row, 1);
			data[1] = (String) listOfAccounts.getModel().getValueAt(row, 2);
			data[2] = (String) listOfAccounts.getModel().getValueAt(row, 3);
			//string holders for the name, address and number
			String name = data[0];
			String address = data[1];
			String number = data[2];
			//create the createNew object with different parameters for 
			//for editMenu
			createNew editorMenu = new createNew(name,address,number,customerID);
			//dispose the current window
			dispose();
		}
		else if (e.getSource()==btnEditAcc){
			//if edit account is pressed 
			//get the customer ID, dispose the current window and create a 
			//accountEditor object
			getCustomerID();
			dispose();
			accountEditor editor = new accountEditor(customerID,customerName);
		}
	}
	//Method that returns the customerID
	public void getCustomerID (){
		//gets the selected row 
		int selectedRowIndex = listOfAccounts.getSelectedRow();
		//gets the value of the first element of the row which is the 
		//customerID
		customerID = (String) listOfAccounts.getModel().getValueAt(selectedRowIndex, 0);
		customerName = (String) listOfAccounts.getModel().getValueAt(selectedRowIndex, 1);
	}
}
//Author: Samandeep Singh Virdi
//Date: Dec,2016
//Description: Frame to create a new customer or edit 
//a current customer

//METHOD LISTS
//public createNew()
//public createNew(String name,String address,String phoneNum,String customerID)
//public static void main(String[] args) 
//public void actionPerformed(ActionEvent e) 
class createNew extends JFrame implements ActionListener{
	//Pricate data for the labels, background, buttons, text fields,
	//strings and a holder CreateNew object
	private JLabel lblTitle,lblName,lblLastName,lblPhoneNumber,
	lblAddress,lblMiddle, lblBackground;     // declare various GUI components
	private JButton btnBack,btnCreate,btnChange;; 
	private JTextField txtName = new JTextField(),txtLastName= new JTextField()
			,txtPhoneNumber= new JTextField(),
			txtAddress= new JTextField(),txtMiddle= new JTextField();
	private String customerId;
	private createNew holder;
	private ImageIcon background;
	//Constructor For Create New Customer
	public createNew(){
		super("Create");  // title for the frame
		//layout as null
		getContentPane().setLayout(null);
		//get the background image
		try {
			background =
					new ImageIcon (ImageIO.read(getClass().getResource("Wallpaper.jpg")));
		}//Catch for file error
		catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, 
					"ERROR: File(s) Not Found\n Please Check Your File Format\n"
							+ "and The File Name!");
		}
		//Initialize all of the compenents  
		lblBackground = new JLabel (background);
		lblTitle = new JLabel ("Create Menu");
		lblTitle.setFont(new Font("ARIAL",Font.ITALIC, 30));
		lblName = new JLabel("Please Enter Your First Name.");
		lblLastName= new JLabel ("Please Enter Your Last Name.");
		lblPhoneNumber = new JLabel ("Please Enter Your Phone Number.");
		lblAddress = new JLabel ("Please Enter Your Address.");
		lblMiddle = new JLabel ("Please Enter Your Middle Name (Optional).");
		btnCreate = new JButton ("Create");
		btnBack = new JButton ("Back");
		//set the font
		lblName.setFont(new Font("Times New Roman",Font.BOLD, 18));
		lblLastName.setFont(new Font("Times New Roman",Font.BOLD, 18));
		lblPhoneNumber.setFont(new Font("Times New Roman",Font.BOLD, 18));
		lblAddress.setFont(new Font("Times New Roman",Font.BOLD, 18));
		lblMiddle.setFont(new Font("Times New Roman",Font.BOLD, 18));

		//add action listener
		btnCreate.addActionListener(this);
		btnBack.addActionListener(this);
		//set bounds for all the components
		btnCreate.setBounds(393, 594, 220, 36);
		txtMiddle.setBounds(333, 249, 342, 36);
		txtName.setBounds(333, 158, 342, 36);
		txtLastName.setBounds(333, 339, 342, 36);
		txtPhoneNumber.setBounds(333, 429, 342, 36);
		txtAddress.setBounds(333, 511, 342, 36);
		btnBack.setBounds(6,716,64,36);
		lblTitle.setBounds(417, 0, 182, 50);
		lblMiddle.setBounds(333, 201, 342, 36);
		lblName.setBounds(387, 110, 239, 36);
		lblLastName.setBounds(387, 297, 239, 36);
		lblPhoneNumber.setBounds(369, 387, 269, 36);
		lblAddress.setBounds(393, 477, 215, 30);
		btnBack.setBounds(6,716,64,36);
		lblTitle.setBounds(417, 0, 182, 50);
		lblBackground.setBounds(0, 0, 1000, 1000);
		//add components to frame
		getContentPane().add(btnCreate);
		getContentPane().add(txtMiddle);
		getContentPane().add(txtLastName);
		getContentPane().add(txtAddress);
		getContentPane().add(txtPhoneNumber);
		getContentPane().add(txtName);
		getContentPane().add(lblMiddle);
		getContentPane().add(lblLastName);
		getContentPane().add(lblAddress);
		getContentPane().add(lblPhoneNumber);
		getContentPane().add(lblName);
		getContentPane().add(btnBack);
		getContentPane().add(lblTitle);
		getContentPane().add(lblBackground);
		//set size, visible and action for close
		setSize(1000,1000);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	//Second Constructor for the editor!
	//Another private background variable because we need to reprint the background
	//button wasn't visible with original background!!
	private JLabel lblBackground2;
	private ImageIcon background2;
	public createNew(String name,String address,String phoneNum,String customerID){
		//create editor object
		createNew editor = new createNew();
		//get the background again
		try {
			background2 =
					new ImageIcon (ImageIO.read(getClass().getResource("Wallpaper.jpg")));
		}//Catch for file error
		catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, 
					"ERROR: File(s) Not Found\n Please Check Your File Format\n"
							+ "and The File Name!");
		}
		//initialize the components 
		lblBackground2 = new JLabel (background2);
		btnChange = new JButton ("Change");
		//split the data so the first name, middle name and 
		//last name is filled
		String nameInSec[] = name.split(" ");
		editor.txtName.setText(nameInSec[0]);
		editor.txtMiddle.setText(nameInSec[1]);
		editor.txtLastName.setText(nameInSec[2]);
		//make the text fields equal the parameters 
		editor.txtPhoneNumber.setText(phoneNum);
		editor.txtAddress.setText(address);
		//remove the create button and the 
		//background
		editor.btnCreate.setEnabled(false);
		editor.btnCreate.setVisible(false);
		editor.lblBackground.setVisible(false);
		//add action listener
		btnChange.addActionListener(this);
		//set bounds for components 
		lblBackground2.setBounds(0, 0, 1000, 1000);
		btnChange.setBounds(393, 594, 220, 36);
		//add to the editor
		editor.add(btnChange);
		editor.add(lblBackground2);
		//make the customerID parameter equal the global customerId
		customerId = customerID;
		//holder so the action listener can get the data from that text 
		//field
		holder = editor;
	}
	//SELF TESTING MAIN
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			createNew testMenu = new createNew();
			//Catch for errors
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ERROR: Number Format Error");
		}
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnBack){
			//if user presses back, ask user if they are sure
			if (UserInterface.getMethods().yesOrNoDialogBox("Are You Sure You Want To Go Back??", "Return")){
				//if true then create listMenu and dispose current window
				try {
					ListMenu list = new ListMenu();
					//Catch for errors
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "ERROR: Number Format Error");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, 
							"ERROR: File(s) Not Found\n Please Check Your File Format\n"
									+ "and The File Name!");
				}
				dispose();
			}			
		}
		else if (e.getSource()==btnCreate){
			//if user presses the create button it checks if the required fields are filled if not 
			// an error is displayed
			if (txtName.getText().equalsIgnoreCase("")||txtLastName.getText().equalsIgnoreCase("")
					||txtAddress.getText().equalsIgnoreCase("")||txtPhoneNumber.getText().equalsIgnoreCase("")){
				JOptionPane.showMessageDialog(null, "ERROR: Please Fill All Of The Fields!");
			}
			else {
				//if user presses create and all required fields are full then
				//create strings for the data and get the text from the text fields
				String str, str2;
				String name = txtName.getText() + " " + txtMiddle.getText() + " " + txtLastName.getText();
				String address = txtAddress.getText();
				String phone = txtPhoneNumber.getText();
				//create a new customer 
				Customer customer = new Customer (name,address,phone);
				//insert the customer into the customerList
				UserInterface.getCustList().insert(customer);
				//make str get the updated customerList
				str = UserInterface.getCustList().print();
				//create a new chequings and savings account using the new customer ID
				Chequing chequing = new Chequing(customer.getCustomerID());
				Savings savings = new Savings(customer.getCustomerID());
				//insert the accounts into accountList
				UserInterface.getAccountList().insertC(chequing);
				UserInterface.getAccountList().insertS(savings);
				//get updated accountList
				str2 = UserInterface.getAccountList().print();
				//write to the files 
				UserInterface.getMethods().writeToFile(UserInterface.getCustFileName(), str);
				UserInterface.getMethods().writeToFile(UserInterface.getAccFileName(), str2);
				//when created go back to the list menu and then dispose current window
				try {
					ListMenu list = new ListMenu();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "ERROR: Number Format Error");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, 
							"ERROR: File(s) Not Found\n Please Check Your File Format\n"
									+ "and The File Name!");
				}
				dispose();
				JOptionPane.showMessageDialog(null, "Customer Created!");
			}
		}
		else if (e.getSource()==btnChange){
			//if button change is pressed get the data from the fields using the holder variable
			String str;
			String name = holder.txtName.getText() + " " + holder.txtMiddle.getText() + " " + holder.txtLastName.getText();
			String address = holder.txtAddress.getText();
			String phone = holder.txtPhoneNumber.getText();
			//create a string that combines the data via a comma
			String customerInfo = name + "," + address + "," + phone; 
			//call the change method from the customerList
			if (UserInterface.getCustList().change(customerId, customerInfo)){
				//if true and the list is changed 
				//get the updated customerList
				str = UserInterface.getCustList().print();
				//write to the customerRecords file
				UserInterface.getMethods().writeToFile(UserInterface.getCustFileName(), str);
				//when created go back to the list menu and then dispose current window
				try {
					ListMenu list = new ListMenu();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "ERROR: Number Format Error");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, 
							"ERROR: File(s) Not Found\n Please Check Your File Format\n"
									+ "and The File Name!");
				}
				holder.dispose();
				JOptionPane.showMessageDialog(null, "Customer Edited!");
			}
			else {
				//if false
				JOptionPane.showMessageDialog(null, "Failed! Couldn't Change The Account");
			}
		}
	}
}
//Author: Samandeep Singh Virdi 
//Data: Dec, 2016
//Description: account editor to withdraw and deposit into savings or 
// chequings accounts 

//METHOD LISTS 
//public accountEditor(String customerID)
//public static void main(String[] args) 
//public void actionPerformed(ActionEvent e) 
class accountEditor extends JFrame implements ActionListener{
	// declare various GUI components
	private JLabel lblTitle,lblSavings,lblCheq,lblCustomerName,lblBackground;    
	private JButton btnBack,btnDepositSav,btnWdSav,btnDepositCheq,btnWdCheq; 
	private JLabel cheqBalDet, savBalDet,cheqFeeDet,savFeeDet;
	private String customer;
	private String globalCustomerID, globalCustomerName;
	private int chequingIndex,savingsIndex;
	private double cheqBal,cheqFee,savBal,savFee;
	private Chequing chequing;
	private Savings savings;
	private ImageIcon background;
	public accountEditor(String customerID,String customerName){
		super("Create");  // title for the frame

		//set the global customer ID as customer ID
		globalCustomerID = customerID;
		globalCustomerName = customerName;
		//set layout as null
		getContentPane().setLayout(null);
		//get the background file for the background
		try {
			background =
					new ImageIcon (ImageIO.read(getClass().getResource("Wallpaper.jpg")));
			//catch for file errors 
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, 
					"ERROR: File(s) Not Found\n Please Check Your File Format\n"
							+ "and The File Name!");
		}
		// Initialize all components
		lblBackground = new JLabel (background);
		lblSavings = new JLabel ("Savings");
		lblCheq = new JLabel ("Chequings");
		btnBack = new JButton ("Back");
		btnDepositSav = new JButton ("Deposit Into Saving");
		btnDepositCheq = new JButton ("Deposit Into Chequing");
		btnWdSav = new JButton ("Withdraw From Saving");
		btnWdCheq = new JButton ("Withdraw From Chequing");
		lblTitle = new JLabel ("Account Editor");
		cheqBalDet = new JLabel();
		savBalDet = new JLabel();
		cheqFeeDet = new JLabel();
		savFeeDet = new JLabel();

		//set the fonts
		lblBackground.setFont(new Font("Superclarendon", Font.PLAIN, 13));
		lblTitle.setFont(new Font("Superclarendon", Font.ITALIC, 30));
		cheqBalDet.setFont(new Font("Superclarendon", Font.PLAIN, 20));
		savBalDet.setFont(new Font("Superclarendon", Font.PLAIN, 20));
		cheqFeeDet.setFont(new Font("Superclarendon", Font.PLAIN, 20));
		savFeeDet.setFont(new Font("Superclarendon", Font.PLAIN, 20));

		//get the chequing and savings account ID 
		chequingIndex = UserInterface.getAccountList().getChequing(customerID);
		savingsIndex = UserInterface.getAccountList().getSavings(customerID);
		//get the chequing and savings using the Account ID
		chequing = UserInterface.getAccountList().getChequing(chequingIndex);
		savings = UserInterface.getAccountList().getSavings(savingsIndex);
		//get the current balance and fee for both accounts 
		cheqBal = chequing.getBalance();
		cheqFee = chequing.getFee();
		savBal = savings.getBalance();
		savFee = savings.getFee();

		//set the labels as the fee and balances with the %.2f to format the double
		cheqBalDet.setText("Chequing Balance:$" + String.format("%.2f",cheqBal));
		cheqFeeDet.setText ("The Fee For Withdrawal is:$" + String.format("%.2f",cheqFee));
		savBalDet.setText("Savings Balance:$" +String.format("%.2f",savBal));
		savFeeDet.setText("The Fee For Withdrawal is:$" + String.format("%.2f",savFee));

		//add action listeners to all buttons 
		btnDepositSav.addActionListener(this);
		btnDepositCheq.addActionListener(this);
		btnWdSav.addActionListener(this);
		btnWdCheq.addActionListener(this);
		btnBack.addActionListener(this);
		//set the bounds for all the components 
		cheqFeeDet.setBounds(16, 152, 471, 150);
		cheqBalDet.setBounds(16, 100, 385, 150);
		savBalDet.setBounds(521, 120, 385, 100);
		savFeeDet.setBounds(521, 172, 385, 100);
		btnDepositSav.setBounds(749, 558, 186, 36);
		btnDepositCheq.setBounds(240,558, 186, 36);
		btnWdSav.setBounds(521, 557, 195, 38);
		btnWdCheq.setBounds (33,558,195,36);
		btnBack.setBounds(6,716,64,36);
		lblTitle.setBounds(372, 0, 261, 50);
		lblBackground.setBounds(0, 0, 1000, 1000);
		//add all components to the frame
		getContentPane().add(cheqFeeDet);
		getContentPane().add(savFeeDet);
		getContentPane().add(cheqBalDet);
		getContentPane().add(savBalDet);
		getContentPane().add(btnDepositSav);
		getContentPane().add(btnDepositCheq);
		getContentPane().add(btnWdSav);
		getContentPane().add(btnWdCheq);
		getContentPane().add(btnBack);
		getContentPane().add(lblTitle);
		getContentPane().add(lblBackground);
		//set size, visibility and action for close 
		setSize(1000,1000);
		setVisible(true);
		//setLocation(100,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//SELF TESTING MAIN
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create the accountEditor object
		accountEditor testMenu = new accountEditor("790767", "Samandeep");

	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnBack){
			if (e.getSource() == btnBack){
				//if user presses back, ask user if they are sure
				if (UserInterface.getMethods().yesOrNoDialogBox("Are You Sure You Want To Go Back??", "Return")){
					//if true then create listMenu and dispose current window
					try {
						ListMenu list = new ListMenu();
						//Catch for errors
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "ERROR: Number Format Error");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, 
								"ERROR: File(s) Not Found\n Please Check Your File Format\n"
										+ "and The File Name!");
					}
					dispose();
				}			
			}
		}
		else if (e.getSource() ==btnDepositCheq){
			//if deposit chequing button is pressed
			//ask the user the amount you want to deposit into chequings 
			Double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "What Amount Do You Want To Deposit Into Chequings?"));
			//deposit into chequing
			chequing.deposit(amount);
			//get the balance and fee
			cheqBal = chequing.getBalance();
			cheqFee = chequing.getFee();
			//set the label as the updated balance and fee
			cheqBalDet.setText("Chequing Balance:$" + String.format("%.2f",cheqBal));
			cheqFeeDet.setText ("The Fee For Withdrawal is:$" + String.format("%.2f",cheqFee));
			//get updated info from the account list and save it to the file 
			String str2 = UserInterface.getAccountList().print();
			UserInterface.getMethods().writeToFile(UserInterface.getAccFileName(), str2);
			//ask if the user wants to save the transaction
			if (UserInterface.getMethods().yesOrNoDialogBox("Do You Want To Save A Receipt?", "Receipt")){
				//Call method from MethodClass and print the receipt via JFileChooser
				UserInterface.getMethods().printReceipt(globalCustomerName, globalCustomerID, savBal, savFee, cheqBal, cheqFee,amount,true,true);
			}
		}
		else if  (e.getSource() ==btnDepositSav){
			//if deposit savings button is pressed 
			//ask the user for the amount you want to deposit into savings
			Double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "What Amount Do You Want To Deposit Into Savings?"));
			//deposit into savings 
			savings.deposit(amount);
			//get the updated fee and balance 
			savBal = savings.getBalance();
			savFee = savings.getFee();
			//set the label as the updated balance and fee
			savBalDet.setText("Savings Balance:$" +String.format("%.2f",savBal));
			savFeeDet.setText("The Fee For Withdrawal is:$" + String.format("%.2f",savFee));
			//get updated info from the account list and save it to the file 
			String str2 = UserInterface.getAccountList().print();
			UserInterface.getMethods().writeToFile(UserInterface.getAccFileName(), str2);
			//ask if the user wants to save the transaction
			if (UserInterface.getMethods().yesOrNoDialogBox("Do You Want To Save A Receipt?", "Receipt")){
				//Call method from MethodClass and print the receipt via JFileChooser
				UserInterface.getMethods().printReceipt(globalCustomerName, globalCustomerID, savBal, savFee, cheqBal, cheqFee,amount,true,false);
			}
		}
		else if (e.getSource() ==btnWdCheq){
			//if withdrawal chequings button is pressed 
			//ask the user for the amount you want to take out of chequings 
			Double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "How Much Do You Want To Withdraw From Chequings?"));
			//take out that amount
			chequing.withdraw(amount);
			//get the updated fee and balance 
			cheqBal = chequing.getBalance();
			cheqFee = chequing.getFee();
			//set the label as the updated balance and fee
			cheqBalDet.setText("Chequing Balance:$" + String.format("%.2f",cheqBal));
			cheqFeeDet.setText ("The Fee For Withdrawal is:$" + String.format("%.2f",cheqFee));
			//get updated info from the account list and save it to the file 
			String str2 = UserInterface.getAccountList().print();
			UserInterface.getMethods().writeToFile(UserInterface.getAccFileName(), str2);
			//ask if the user wants to save the transaction
			if (UserInterface.getMethods().yesOrNoDialogBox("Do You Want To Save A Receipt?", "Receipt")){
				//Call method from MethodClass and print the receipt via JFileChooser
				UserInterface.getMethods().printReceipt(globalCustomerName, globalCustomerID, savBal, savFee, cheqBal, cheqFee,amount, false,true);
			}
		}
		else if (e.getSource() == btnWdSav){
			//if withdrawal savings button is pressed 
			//ask the user for the amount you want to take out of savings
			Double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "How Much Do You Want To Withdraw From Savings?"));
			//take out that amount
			savings.withdraw(amount);
			//get the updated fee and balance 
			savBal = savings.getBalance();
			savFee = savings.getFee();
			//set the label as the updated balance and fee
			savBalDet.setText("Savings Balance:$" +String.format("%.2f",savBal));
			savFeeDet.setText("The Fee For Withdrawal is:$" + String.format("%.2f",savFee));
			//get updated info from the account list and save it to the file 
			String str2 = UserInterface.getAccountList().print();
			UserInterface.getMethods().writeToFile(UserInterface.getAccFileName(), str2);
			//ask if the user wants to save the transaction
			if (UserInterface.getMethods().yesOrNoDialogBox("Do You Want To Save A Receipt?", "Receipt")){
				//Call method from MethodClass and print the receipt via JFileChooser
				UserInterface.getMethods().printReceipt(globalCustomerName, globalCustomerID, savBal, savFee, cheqBal, cheqFee,amount,false,false);
			}
		}
	}
}