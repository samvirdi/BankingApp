public class Customer {

	//declares variables for Name, address, phone number and ID
	private String customerID;
	private String name;
	private String address;
	private String phone;
	private String[] accountIDs;

	//Overloaded Constructor initializes the client information with specific information.
	public Customer(String name, String address, String phone) {
		this.customerID = generateID(6);
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
	public Customer(String info) {
		String data[] = info.split(",");
		
		this.customerID = data[0];
		this.name = data[1];
		this.address = data[2];
		this.phone = data[3];
	}
	   
	public String getName() {
		return this.name;
	}
	
	public String getAddress() {
		return this.address;
	}
   
	public String getPhone() {
		return this.phone;
	}
	
	public String getCustomerID() {
		return this.customerID;
	}

	public String[] getAccountIDs() {
		return this.accountIDs;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	//generates a random ID of a specified amount of digits
	public static String generateID(int length) {
		String id = "";
		
		for (int i = 0; i < length; i++) {
			id += (int) (Math.random() * 10);
		}
		
		return id;
	}

	public static void main(String[] args) {
		Customer customer = new Customer("TestName,51 Test St.,100-000-001");
		
		System.out.println(customer.getCustomerID());
		System.out.println(customer.getName());
		System.out.println(customer.getAddress());
		System.out.println(customer.getPhone());
		
		customer.setName("TestNameChanged");
		System.out.println(customer.getName());
	}

}
