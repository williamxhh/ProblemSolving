package learn.multithread.producer_consumer;
import java.util.concurrent.*;

public class Test {
	public static void main(String[] args) {
//		Storage storage = new StorageWaitNotify();
//		Storage storage = new StorageLock();
//		Storage storage = new StorageBlockingQueue();
		Storage storage = new StorageSemaphore();
		
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i = 1; i <= 2; i++) {
			executor.execute(new Thread(new Consumer(i,storage)));
		}
		
		for(int i = 1; i <= 3; i++) {
			executor.execute(new Thread(new Producer(i,storage)));
		}
		
		
		
		executor.shutdown();
	}
}
