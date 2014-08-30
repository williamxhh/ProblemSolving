package learn.multithread.producer_consumer;

public interface Storage {
	public void put(int producer_id);
	public void take(int consumer_id);
}
