package addressbook;

public class Person {

	private final String firstName;
	private final String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String number;

	public Person(String firstName, String lastName, String address, String city, String state, String zip,
			String number) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.number = number;
	}

	public void update(String address, String city, String state, String zip, String number) {
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.number = number;
	}

	public String[] getInformations() {
		return new String[] { address, city, state, zip, number };
	}

	@Override
	public String toString() {
		return String.format("%s %s\t %s %s %s %s %s", firstName, lastName, address, city, zip, state, number);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return String.format("%s %s", firstName, lastName);
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
