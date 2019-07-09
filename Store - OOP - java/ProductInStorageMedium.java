
public class ProductInStorageMedium extends Product {
	private double sendPrice;
	public ProductInStorageMedium(String name, int serialNum, double price){
		super(name, serialNum, price);
		sendPrice=Math.floor(price*0.07);
	}
	public ProductInStorageMedium(Object other) {
		super (other);
		sendPrice=Math.floor(getProductPrice()*0.07);
	}
	public double[] computeFinalPrice() {
		double [] computeFinalPrice = new double [2];

		computeFinalPrice[0]=getProductPrice();
		computeFinalPrice[1]=sendPrice;
		return computeFinalPrice;
	}
}
