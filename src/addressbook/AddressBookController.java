package addressbook;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class AddressBookController {

	FileSystem fileSystem;

	public AddressBookController(FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}

	public void doAdd(AddressBookGUI gui) {
		AddressBook aB = gui.getAddressBook();
		String[] vals = MultiInputPane.showMultiInputDialog(gui, aB.NEWATTRNAMES, null, "Add new");
		aB.addPerson(vals[0], vals[1], vals[2], vals[3], vals[4], vals[5], vals[6]);
		aB.setChangedSinceLastSave(true);
		gui.setAddressBook(aB); // TODO majd próbáld meg enélkül is
	}

	public void doEdit(AddressBookGUI gui, int index) {
		AddressBook aB = gui.getAddressBook();
		String[] initialValues = aB.getOtherPersonInformation(index);
		String[] vals = MultiInputPane.showMultiInputDialog(gui, aB.EDITATTRNAMES, initialValues, "Edit");
		gui.getAddressBook().updatePerson(index, vals[0], vals[1], vals[2], vals[3], vals[4]);
		aB.setChangedSinceLastSave(true);
		gui.setAddressBook(aB);

	}

	public void doDelete(AddressBookGUI gui, int index) {
		gui.getAddressBook().removePerson(index);
		gui.getAddressBook().setChangedSinceLastSave(true);
	}

	public void doSortByName(AddressBookGUI gui) {
		gui.getAddressBook().sortByName();
		gui.getAddressBook().setChangedSinceLastSave(true);
	}

	public void doSortByZip(AddressBookGUI gui) {
		gui.getAddressBook().sortByZip();
		gui.getAddressBook().setChangedSinceLastSave(true);
	}

	public void doPrint(AddressBookGUI gui) {
		gui.getAddressBook().printAll();
	}

	public void doNew(AddressBookGUI gui) {
		gui.setAddressBook(new AddressBook());
		gui.getAddressBook().setChangedSinceLastSave(true);

	}

	public void doSave(AddressBookGUI gui) throws IOException, SecurityException, InterruptedException {

		AddressBook ad = gui.getAddressBook();
		if (ad.getFile() == null) {
			doSaveAs(gui);
		} else {
			fileSystem.saveFile(ad, ad.getFile());
		}
		ad.setChangedSinceLastSave(false);

	}

	public void doSaveAs(AddressBookGUI gui)
			throws java.io.IOException, java.lang.InterruptedException, java.lang.SecurityException {
		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("/"));
		fc.setDialogTitle("Save as");
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION)
			;

		File newFile = new File(fc.getSelectedFile().getAbsolutePath());

		fileSystem.saveFile(gui.getAddressBook(), newFile);
		gui.getAddressBook().setChangedSinceLastSave(false);

	}

	public void doOpen(AddressBookGUI gui) throws java.io.IOException, java.lang.ClassCastException,
			java.lang.ClassNotFoundException, java.lang.InterruptedException, java.lang.SecurityException {

		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("/"));
		fc.setDialogTitle("énkicsipónim");
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION)
			;

		File file = new File(fc.getSelectedFile().getAbsolutePath());

		gui.setAddressBook(fileSystem.readFile(file));

	}

	public void doOfferSaveChanges(AddressBookGUI gui)
			throws java.lang.InterruptedException, java.io.IOException, java.lang.SecurityException {
		// Do Offer to Save Changes extension. This method is called if the user
		// initiates a new, open, or quit operation, or tries to close the
		// window,
		// with unsaved changes to the address book. The user is offered an
		// opportunity
		// to save those changes before proceeding.
		AddressBook aB = gui.getAddressBook();

		int answer = JOptionPane.showConfirmDialog(gui, "Would you want to save the changes?", "Are you sure?",
				JOptionPane.YES_NO_OPTION);
		System.out.println(answer);
		if (answer == 0) {
			doSave(gui);
			gui.getAddressBook().setChangedSinceLastSave(false); // yes 0, no 1
		}

	}
}
