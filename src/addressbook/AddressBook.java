package addressbook;

import java.io.File;
import java.util.Observable;
import java.util.Vector;

public class AddressBook extends Observable {

	File file;
	boolean changedSinceLastSave;
	Vector<Person> collection = new Vector<Person>();
	
	final String[] NEWATTRNAMES = {"First Name","Last Name","Address","City","State","Zip","Number"};
	final String[] EDITATTRNAMES = {"Address","City","State","Zip","Number"};


	public int getNumberOfPersons() {
		return collection.size();
	}

	public String getFullNameOfPerson(int i) {
		return collection.get(i).getName();
	}

	public String[] getOtherPersonInformation(int i) {
		return collection.get(i).getInformations();
	}

	public void updatePerson(int i, String address, String city, String state, String zip, String number) {

		collection.get(i).update(address,city,state,zip,number);
	}

	public void removePerson(int i) {
		collection.remove(i);
	}

	public void printAll() {
		collection.forEach(System.out::println);
		//TODO ??? jó?
	}

	public void addPerson(String firstName, String lastName, String address, String city, String state, String zip,
			String number) {
		collection.add(new Person(firstName, lastName, address, city, state, zip, number));
	}

	public void sortByName() {
		collection.sort(new PersonComparator().byName());
	}

	public void sortByZip() {
		collection.sort(new PersonComparator().byZip());
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getTitle() {
		
		return file == null ? "Untitled" : file.getName();
	}

	public boolean getChangedSinceLastSave() {
		return changedSinceLastSave;
	}

	public void setChangedSinceLastSave(boolean modified) {
		this.changedSinceLastSave = modified;
	}

}
