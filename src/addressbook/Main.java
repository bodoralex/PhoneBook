package addressbook;

import java.awt.Component;

import java.util.ArrayList;
import java.util.Arrays;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person p1 = new Person("bbb", "last99Nme", "Bem apo 22", "Onga", "BAZ", "3562", "46789795");
		Person p2 = new Person("aa", "xxxxx", "Munkácsy 112", "Miskolc", "BAZ", "3532", "46255667");
		Person p3 = new Person("aaaa", "last99Nme", "Hunyyadi 22", "String city", "Object state", "011001", "88776688");
		Person p4 = new Person("aaa", "aaasssddd", "Károly István 33", "Szerencs", "Borsod", "1666", "69696969");
		ArrayList<Person> array = new ArrayList<Person>();
		array.add(p1);
		array.add(p2);
		array.add(p3);
		array.add(p4);
		array.sort(new PersonComparator().byZip());
		// array.forEach(System.out::println);

		Component parentComponent = null;
		String[] prompts = { "ez", "az" };
		String[] initialValues = { "egy", "ket" };
		// String[] kek = MultiInputPane.showMultiInputDialog(parentComponent,
		// prompts, initialValues, "ezKépremSzépen");
		// System.out.println(Arrays.toString(kek));

		/*
		 * JButton open = new JButton(); JFileChooser fc = new JFileChooser();
		 * fc.setCurrentDirectory(new File("/"));
		 * fc.setDialogTitle("énkicsipónim");
		 * fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); if
		 * (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION);
		 * System.out.println(fc.getSelectedFile().getAbsolutePath());
		 * 
		 */try {
			saveFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void saveFile() throws java.io.IOException {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			// root element
			Element rootElement = doc.createElement("Collection");
			doc.appendChild(rootElement);
			
			Element person = doc.createElement("Entry");
			rootElement.appendChild(person);
			
			Element firstname = doc.createElement("firstName");
			firstname.appendChild(doc.createTextNode("Alex"));
			person.appendChild(firstname);
			
			Element lastname = doc.createElement("lastName");
			lastname.appendChild(doc.createTextNode("Bodor"));
			person.appendChild(lastname);
			
			
			
			/*
			 * Element carname1 = doc.createElement("Person"); Attr attrType1 =
			 * doc.createAttribute("type"); attrType1.setValue("sports");
			 * carname1.setAttributeNode(attrType1);
			 * carname1.appendChild(doc.createTextNode("Ferrari 202"));
			 * rootElement.appendChild(carname1);
			 */

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("lol.xml"));
			transformer.transform(source, result);
			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// TODO esse
	}
}
