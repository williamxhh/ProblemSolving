package learn.multithread.producer_consumer;

public class Consumer implements Runnable {
	private Storage storage;
	private int id;
	
	public Consumer(int id, Storage storage) {
		this.id = id;
		this.storage = storage;
	}

	@Override
	public void run() {
		for(int i = 0; i < 30; i++) {
			storage.take(id);
		}
	}

}
