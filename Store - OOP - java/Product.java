public abstract class Product {

	// Fields
	private String name;
	private int serialNum;
	private double price;


	// Constructors
	public Product(String name, int serialNum, double price){
		if (name.length()<0||serialNum<0||price<0) {
			throw new IllegalArgumentException();
		}
		this.name=name;
		this.serialNum=serialNum;
		this.price=price;
	}
	public Product(Object other) {
		this.serialNum=((Product)other).serialNum;
		this.price=((Product)other).price;
		
	}
	public String getProductName() {
		return name.toString();
	}
	public int getProductSerialNumber() {
		return serialNum;
	}

	public double getProductPrice() {
		return price;
	}
	public String toString() {
		return "Product: "+name+", "+serialNum+", "+price;
	}
	public boolean equals(Object other) {
		if(other instanceof Product) {
			if (this.serialNum==((Product)other).serialNum) {

				return true;
			}
		}
		return false;
	}
	
	
	public abstract double[] computeFinalPrice();

	
}
