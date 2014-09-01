package learn.multithread.producer_consumer;
import java.util.*;
import java.util.concurrent.Semaphore;

public class StorageSemaphore implements Storage{
	static int MAX_VAL = 8;
	private List<Item> storage = new LinkedList<Item>();
	
	private Semaphore mutex = new Semaphore(1);
	private Semaphore notfull = new Semaphore(MAX_VAL);
	private Semaphore notempty = new Semaphore(0);
	
	@Override
	public void put(int producer_id) {
		try {
			notfull.acquire();
			
			mutex.acquire();
			Item item = new Item();
			storage.add(item);
			System.out.println(producer_id + " is putting " + item);
			mutex.release();
			
			notempty.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void take(int consumer_id) {
		try {
			notempty.acquire();
			
			mutex.acquire();
			Item item = storage.get(0);
			System.out.println(consumer_id + " is getting " + item);
			storage.remove(0);
			mutex.release();
			
			notfull.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
