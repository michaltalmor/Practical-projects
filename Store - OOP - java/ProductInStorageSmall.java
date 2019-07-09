
public class ProductInStorageSmall extends Product {
	
	private double sendPrice;
	// Constructors
	public ProductInStorageSmall(String name, int serialNum, double price){
		super(name, serialNum, price);
		sendPrice=Math.floor(price*0.05);
	}
	public ProductInStorageSmall(Object other) {
		super (other);
		sendPrice=Math.floor(getProductPrice()*0.05);
	}
	public double[] computeFinalPrice() {
		double [] computeFinalPrice = new double [2];

		computeFinalPrice[0]=getProductPrice();
		computeFinalPrice[1]=sendPrice;
		return computeFinalPrice;
	}
}
