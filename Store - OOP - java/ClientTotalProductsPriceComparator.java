
public class ClientTotalProductsPriceComparator implements Comparator {
public int compare (Object o1, Object o2) {
	if (o1 instanceof Client && o2 instanceof Client) {
		
		int o1FinalPrice=(int)((Client)o1).computeFinalProductsPrice();
		int o2FinalPrice=(int)((Client)o2).computeFinalProductsPrice();
		
		return ((o2FinalPrice-o1FinalPrice));
	}
	throw new ClassCastException();
}
}
