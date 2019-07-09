public class VIPClient extends Client{

	// Constructors
	public VIPClient(String firstName, String lastName, int id) {
		super(firstName, lastName, id);
	}
	public VIPClient(Object other) {
		super(other);
	}
	public double computeFinalShippingPrice() {
	double finalShippingPrice =0;
		
		for (int i=0; i<getProducts().size();i++) {
			double [] finalPrice = ((Product)getProducts().get(i)).computeFinalPrice();
			finalShippingPrice=finalShippingPrice+finalPrice[1];
		 }
		return finalShippingPrice*0.50;
	}
	
}
