
public class ClientNameComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		if (o1 instanceof Client && o2 instanceof Client) {
			String o1LastName = ((Client)o1).getLastName();
			String o2LastName = ((Client)o2).getLastName();
			String o1FirstName = ((Client)o1).getFirstName();
			String o2FirstName = ((Client)o2).getFirstName();

			if (o1LastName.compareTo(o2LastName)!=0) {
				return o1LastName.compareTo(o2LastName);
			}
			return o1FirstName.compareTo(o2FirstName);
		}
		throw new ClassCastException();
	}
}
