
public class ClientProductManagementSystem {
	private LinkedList clients;
	private LinkedList products;
	
	public ClientProductManagementSystem() {
		clients=new LinkedList();
		products= new LinkedList();
	}
	public boolean addClient(Client client) {
		if (!clients.contains(client)) {
			clients.add(client);
			return true;
		}
		return false;
	}
	public boolean addProduct(Product product) {
		if (!products.contains(product)) {
			products.add(product);
			return true;
		}
		return false;
	}
	public boolean addProductToClient(Client client, Product product) {
		if (clients.contains(client)&&products.contains(product)&&!client.isInterestedIn(product)) {
			client.addProduct(product);
			return true;
		}
		return false;
	}
	public LinkedList getFirstKClients(Comparator comp, int k) {
		if (k>clients.size()||k<0||comp==null) {
			throw new IllegalArgumentException();
		}
		LinkedList firstK = new LinkedList(clients);
		firstK.sortBy(comp);
		LinkedList sortClient = new LinkedList();
	
		for (int i=0 ; i<k; i++) {		
			sortClient.add(firstK.get(i));
		}
		return sortClient;
		
	}
	public int getNumberOfClients() {
		return clients.size();
	}
	public int getNumberOfProducts() {
		return products.size();
	}
	public double computeFinalOrderPrice(Client client) {
		if (clients.contains(client)) {
			return client.computeFinalOrderPrice();
		}
		return 0;
	}
	public String toString() {
		
		return clients.toString();
	}
	
}
