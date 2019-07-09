
public class ProductInStore extends Product {
	private double sendPrice;
	public ProductInStore(String name, int serialNum, double price){
		super(name, serialNum, price);
		sendPrice=0;
	}
	public ProductInStore(Object other) {
		super (other);
		sendPrice=0;
	}
	public double[] computeFinalPrice() {
		double [] computeFinalPrice = new double [2];
		
		computeFinalPrice[0]=getProductPrice();
		computeFinalPrice[1]=sendPrice;
		return computeFinalPrice;
	}
}
