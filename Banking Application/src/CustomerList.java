import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CustomerList {

	private ArrayList<Customer> list;
	private int size;

	public CustomerList() {
		this.list = new ArrayList<Customer>();
		this.size = 0;
	}

	public CustomerList(String fileName) throws NumberFormatException, IOException {
		this.list = new ArrayList<Customer>();
		readFromFile(fileName);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		CustomerList list = new CustomerList();

		list.readFromFile("CustomerRecords.txt");
		list.print();
		System.out.println("\n");

		String customerID = JOptionPane.showInputDialog("Customer ID:");
		String info = JOptionPane.showInputDialog("Name, Address, Phone");
		list.change(customerID, info);
		list.print();
		System.out.println("\n");

		list.delete(customerID);
		list.print();
	}

	public String toString(Customer c) {
		return c.getCustomerID() + ","
				+ c.getName() + ","
				+ c.getAddress() + ","
				+ c.getPhone();
	}

	public void insert(Customer customer) {
		list.add(customer);
		setSize(getSize() + 1);
	}

	public boolean change(String customerID, String info) {
		int index = searchCustomerID(customerID);
		String data[] = info.split(",");

		if (index > -1) {
			Customer customer = getCustomer(index);
			customer.setName(data[0]);
			customer.setAddress(data[1]);
			customer.setPhone(data[2]);
			return true;
		}

		return false;
	}

	public boolean delete(String customerID) {
		int index = searchCustomerID(customerID);

		if (index > -1) {
			list.remove(index);
			setSize(getSize() - 1);
			return true;
		}
		return false;
	}

	public ArrayList<Customer> getList() {
		return this.list;
	}

	public Customer getCustomer(int index) {
		return this.list.get(index);
	}

	public void readFromFile(String fileName) throws NumberFormatException, IOException {
		//		// get file name to read from
		//		String fileName = JOptionPane.showInputDialog(
		//				"Enter file name to read from:",
		//				"CustomerRecords.txt");

		// set up file to read from
		FileReader file = new FileReader(fileName);
		BufferedReader input = new BufferedReader(file);
		
		int size = Integer.parseInt(input.readLine());

		for (int i = 0; i < size; i++) {
			// get and process record
			String info = input.readLine();
			Customer customer = new Customer(info);
			insert(customer);
		}

		input.close(); // close input stream
	}

	// MADE BY SAMANDEEP
	public String [][]printToList() {
		//creates a 2D array string and initializes it to 
		//the list size
		String str[][] = new String[list.size()][list.size()];
		//loop to set the data
		for (int i = 0; i < list.size(); i++) {
				//get the list
				Customer customer = list.get(i);
				//put it as a string
				String data = toString(customer);
				//split the data up 
				String temp[] = data.split(",");
				//populate the 2D array
				str[i][0] = temp[0];
				str[i][1] = temp[1];
				str[i][2] = temp[2];
				str[i][3] = temp[3];
		}	
		//return
		return str;
	}
	public String print() {
		String str = list.size() + "\n";

		for (int i = 0; i < list.size(); i++) {
			Customer customer = list.get(i);
			str += toString(customer) + "\n";
		}

		return str;
	}

	public void sort(int type) {
		switch(type) {
		case 1: // sort by name
			for (int i = 0; i < list.size(); i++) {
				Customer customer = list.get(i);
				int j = i;

				while (j > 0 && customer.getName().compareToIgnoreCase(list.get(j - 1).getName()) < 0) {
					list.set(j, list.get(j - 1));
					j--;
				}

				list.set(j, customer);
			}

			break;
		case 2: // sort by address
			for (int i = 0; i < list.size(); i++) {
				Customer customer = list.get(i);
				int j = i;

				while (j > 0 && customer.getAddress().compareToIgnoreCase(list.get(j - 1).getAddress()) < 0) {
					list.set(j, list.get(j - 1));
					j--;
				}

				list.set(j, customer);
			}

			break;
		case 3: // sort by phone
			for (int i = 0; i < list.size(); i++) {
				Customer customer = list.get(i);
				int j = i;

				while (j > 0 && customer.getPhone().compareToIgnoreCase(list.get(j - 1).getPhone()) < 0) {
					list.set(j, list.get(j - 1));
					j--;
				}

				list.set(j, customer);
			}

			break;
		default:
			System.out.println("Error: Customer List sort type.");
		}
	}

	public int searchCustomerID(String customerID) {
		for (int i = 0; i < list.size(); i++) {
			if (customerID.equalsIgnoreCase(list.get(i).getCustomerID())) {
				return i; // found it
			}
		}
		return -1;
	}

}
