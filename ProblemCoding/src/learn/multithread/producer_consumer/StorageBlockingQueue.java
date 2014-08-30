package learn.multithread.producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class StorageBlockingQueue implements Storage{
	static int MAX_VAL = 12;
	private BlockingQueue<Item> storage = new LinkedBlockingQueue<Item>(MAX_VAL);
	
	public StorageBlockingQueue() {
		System.out.println("Using StorageBlockingQueue");
	}

	@Override
	public void put(int producer_id) {
		try {
			Item item = new Item();
			storage.put(item);
			System.out.println(producer_id + " is putting " + item);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void take(int consumer_id) {
		try {
			Item item = storage.take();
			System.out.println(consumer_id + " is getting " + item);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
