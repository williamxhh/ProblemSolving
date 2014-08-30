package learn.multithread.producer_consumer;

import java.util.*;

public class StorageWaitNotify implements Storage{
	static int MAX_VAL = 6;
	private List<Item> storage = new LinkedList<Item>();
	
	public StorageWaitNotify() {
		System.out.println("Using StorageWaitNotify");
	}
	
	public synchronized void put(int producer_id) {
		try {
			while (storage.size() == MAX_VAL) {
				System.out.println("Full Waiting");
				this.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.notifyAll();
		Item item = new Item();
		storage.add(item);
		System.out.println(producer_id + " is putting " + item);
	}
	
	public synchronized void take(int consumer_id) {
		try {
			while(storage.size() == 0) {
				System.out.println("Empty Waiting");
				this.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.notifyAll();
		Item item = storage.get(0);
		System.out.println(consumer_id + " is getting " + item);
		storage.remove(0);
	}
}
