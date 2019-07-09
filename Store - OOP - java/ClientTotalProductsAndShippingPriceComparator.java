
public class ClientTotalProductsAndShippingPriceComparator implements Comparator {
	public int compare (Object o1, Object o2) {
		if (o1 instanceof Client && o2 instanceof Client) {
			
			double o1FinalPrice=((Client)o1).computeFinalOrderPrice();
			double o2FinalPrice=((Client)o2).computeFinalOrderPrice();
			
			return ((int)(o2FinalPrice-o1FinalPrice));
		}
		throw new ClassCastException();
	}
}
