package learn.multithread.producer_consumer;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StorageLock implements Storage {
	static int MAX_VAL = 10;
	private List<Item> storage = new LinkedList<Item>();
	
	private Lock lock = new ReentrantLock();
	
	private Condition full = lock.newCondition();
	private Condition empty = lock.newCondition();

	public StorageLock() {
		System.out.println("Using StorageLock");
	}
	
	@Override
	public void put(int producer_id) {
		lock.lock();
		try {
			while(storage.size() == MAX_VAL) {
				System.out.println("Full Waiting");
				full.await();
			}
			Item item = new Item();
			storage.add(item);
			System.out.println(producer_id + " is putting " + item);
			empty.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}

	@Override
	public void take(int consumer_id) {
		lock.lock();
		try {
			while(storage.size() == 0) {
				System.out.println("Empty Waiting");
				empty.await();
			}
			Item item = storage.get(0);
			System.out.println(consumer_id + " is getting " + item);
			storage.remove(0);
			full.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}
