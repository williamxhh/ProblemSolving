package learn.multithread.producer_consumer;

public class Producer implements Runnable{
	private Storage storage;
	private int id;
	
	public Producer(int id, Storage storage){
		this.id = id;
		this.storage = storage;
	}

	@Override
	public void run() {
		for(int i = 0; i < 20; i++) {
			storage.put(id);
		}
	}
	
}
