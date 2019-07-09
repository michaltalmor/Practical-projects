public class LinkedList implements List{

	// Fields


	private Link head;
	private Link tail;
	private int size;


	// Constructors
	public LinkedList (){
		head = null;
		tail = null;
	}
	//copy constructor
	public LinkedList (LinkedList list){
		if(list.head != null){
			this.head = new Link(list.getHead());
			Link pointer = this.head;
			while(pointer.getNext() != null){
				pointer = pointer.getNext();
			}
			this.tail = pointer;
		}
		this.size = list.size;
	}

	private Link getHead () {
		return head;
	}
	// Methods
	public void add(Object element){
		if(element == null){
			throw new NullPointerException();
		}
		if(isEmpty())
		{
			Link newLink = new Link(element, null);
			head = newLink;
			tail = newLink;
		}
		else
		{

			Link newLink = new Link(element, null);
			tail.setNext(newLink);
			tail = newLink;
		}
		size++;
	}

	public void add(int index, Object element){
		if(index<0 || index>=size)
			throw new IndexOutOfBoundsException();
		if(element == null){
			throw new NullPointerException();
		}
		if(index == 0){
			Link newLink = new Link(element, null);
			newLink.setNext(head);
			head = newLink;
		}
		else{
			int listIndex = 1;
			Link pointer = head;
			Link pointerNext = head.getNext();
			while(listIndex < index){
				pointer = pointerNext;
				if(pointer.getNext() != null)
					pointerNext = pointerNext.getNext();
				listIndex++;
			}
			Link newLink = new Link(element, null);
			newLink.setNext(pointer.getNext());
			pointer.setNext(newLink);
		}
		size++;
	}

	public boolean isEmpty () {
		if (head==null) {
			return true;
		}
		return false;
	}
	public Object get(int index) {
		if (index<0||index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		Link indexLink = head;
		for (int i=0; i<index;i++) {			
			indexLink=indexLink.getNext();
		}
		return indexLink.getData();



	}

	public int size() {
		size=1;
		if (head==null) {
			return 0;
		}
		Link indexLink = head;

		while (indexLink.getNext()!=null) {
			indexLink=indexLink.getNext();
			size++;
		}
		return size;
	}
	public boolean contains(Object element) {
		boolean ans = false;
		Link curr =head;
		while (!ans&&curr!=null){
			if(curr.getData().equals(element)) {
				ans=true;
			}
			curr=curr.getNext();
		}
		return ans;
	}

	public Object set(int index, Object element) {
		if (element==null) {
			throw new NullPointerException();
		}
		if (index<0||index>size()-1) {
			throw new IndexOutOfBoundsException();
		}
		Object res = get(index);
		Link indexLink = head;
		for (int i=0; i<index;i++) {			
			indexLink=indexLink.getNext();
		}
		indexLink.setData(element);
		return res;

	}
	public String toString() {
		String ans="";
		Link pointer=head;
		for (int i=0;i<size;i++) {
			ans= ans+pointer.toString()+"\n";

			pointer=pointer.getNext();
		}
		return ans;
	}
	public boolean equals(Object other) {
		Link pointer = head;
		boolean ans=false;
		if (other instanceof LinkedList) {
			if (((LinkedList) other).size()!=size) {
				return false;
			}
			for (int i=0; i<size;i++) {
				if(((LinkedList)other).get(i).equals(pointer.getData())) {
					ans=true;
				}
				pointer=pointer.getNext();
			}

		}
		return ans;
	}
	public void sortBy(Comparator comp) {
		if (comp==null) {
			throw new NullPointerException();
		}


		Link first = head;
		Link min=new Link(first.getData());
		Link pointer=first.getNext();
		for (int i=0; i<size;i++) {

			min.setData(first.getData());
			for(int j=i+1;j<size;j++) {
				if (comp.compare(min.getData(), pointer.getData())>0) {
					min.setData(pointer.getData());
					
				}
				pointer=pointer.getNext();
			}
			pointer=first;
			boolean found = false;
			while (pointer!=null&&found==false) {	
				if (pointer.getData().equals(min.getData())) {
					pointer.setData(first.getData());
					first.setData(min.getData());
					found=true;
				}
				pointer=pointer.getNext();
			}
			first=first.getNext();
			if (first!=null) {
			pointer=first.getNext();
			}
		}













		/*
		Link pointer=head.getNext();
		Link minLink=head;

		for (int j=0; j<size-1;j++) {
			pointer=minLink.getNext();
			for (int i=j+1; i<size;i++) {
				if (comp.compare(minLink.getData(),pointer.getData())>0) {
					Link temp= new Link (pointer.getData());
					pointer.setData(minLink.getData());
					minLink.setData(temp.getData());

				}
				pointer=pointer.getNext();
			}
			minLink=minLink.getNext();
		}
		 */
	}
}


