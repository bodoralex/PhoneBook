package addressbook;

import java.util.Comparator;

public class PersonComparator {
	public Comparator<Person> byFirstName() {
		return new byFirstName();
	}

	public Comparator<Person> byLastName() {
		return new byLastName();
	}

	public Comparator<Person> byName() {
		return new byName();
	}

	public Comparator<Person> byZip() {
		return new byZip();
	}

	final class byZip implements Comparator<Person> {
		@Override
		public int compare(Person o1, Person o2) {
			int zipCompare = o1.getZip().compareTo(o2.getZip());
			if (zipCompare != 0)
				return zipCompare;
			return new byName().compare(o1, o2);
		}
	}

	final class byName implements Comparator<Person> {
		@Override
		public int compare(Person o1, Person o2) {
			int lastNameCompare = new byLastName().compare(o1, o2);
			if (lastNameCompare != 0)
				return lastNameCompare;
			return new byFirstName().compare(o1, o2);
		}
	}

	final class byFirstName implements Comparator<Person> {
		@Override
		public int compare(Person o1, Person o2) {
			return o1.getFirstName().compareTo(o2.getFirstName());
		}
	}

	final public class byLastName implements Comparator<Person> {
		@Override
		public int compare(Person o1, Person o2) {
			return o1.getLastName().compareTo(o2.getLastName());
		}
	}

}
