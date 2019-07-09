public class Client {

	// Fields
	private String firstName;
	private String lastName;
	private int id;
	private LinkedList products;


	// Constructors
	public Client(String firstName, String lastName, int id){
		if (firstName.length()<0||lastName.length()<0||id<0) {
			throw new  IllegalArgumentException();	 	
		}
		this.firstName=firstName;
		this.lastName=lastName;
		this.id=id;
		products=new LinkedList();
	}
	
	public Client(Object other) {
		this.firstName=((Client)other).firstName;
		this.lastName=((Client)other).lastName;
		this.id=((Client)other).id;
		products=new LinkedList();
	}
	
	public String getLastName() {
		return lastName.toString();
	}
	public String getFirstName() {
		return firstName.toString();
	}
	public int getId() {
		return id;
	}
	public LinkedList getProducts() {
		return products;
	}
	public boolean isInterestedIn(Product product) {
		if (this.products.contains(product)) {
			return true;
		}
		return false;
	}
	public boolean addProduct(Product product) {
		if (!isInterestedIn(product)) {
			this.products.add(product);
			return true;
		}
		return false;
	}
	public String toString() {
		return "Client: "+firstName+" " +lastName+"," +id+","+","+"Products:" +"\n"+products.toString();
	}
	public boolean equals(Object other) {
		if (other instanceof Client) {
			if (this.id==((Client)other).id) {
				return true;
			}
		}
		return false;
	}
	public double computeFinalProductsPrice() {
		double finalPrice = 0;
		for (int i=0; i<products.size();i++) {
			finalPrice=finalPrice+((Product)products.get(i)).getProductPrice();
		}
		return finalPrice;
	}
	public double computeFinalShippingPrice() {
		double finalShippingPrice =0;

		for (int i=0; i<products.size();i++) {
			double [] finalPrice = ((Product)products.get(i)).computeFinalPrice();
			finalShippingPrice=finalShippingPrice+finalPrice[1];
		}
		return finalShippingPrice;
	}
	public double computeFinalOrderPrice() {
		double finalPriceIncShip =0;

		finalPriceIncShip=finalPriceIncShip+computeFinalShippingPrice()+computeFinalProductsPrice();

		return finalPriceIncShip;
	}

}
