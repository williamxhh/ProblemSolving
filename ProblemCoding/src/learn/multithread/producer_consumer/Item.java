package learn.multithread.producer_consumer;

public class Item {
	static int COUNTER = 0;
	private int id;
	
	public Item() {
		this.id = ++COUNTER;
	}
	
	@Override
	public String toString() {
		return "Item: " + id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
