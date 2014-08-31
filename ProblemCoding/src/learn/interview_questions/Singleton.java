package learn.interview_questions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Singleton {
	private Singleton() {}
	
	private static Singleton INSTANCE = null;
	
	//ʹ�ð���synchronized������double checkʵ��
	public static Singleton getInstance() {
		if (INSTANCE == null) {
			synchronized(Singleton.class) {
				if(INSTANCE == null){
					INSTANCE = new Singleton();
				}
			}
		}
		return INSTANCE;
	}
	
	//ʹ�þ�̬�ڲ���ʵ��
	public static Singleton getInstanceNested() {
		return Nested.instance;
	}
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
//					Singleton ins = Singleton.getInstance();
					Singleton ins = Singleton.getInstanceNested();
					System.out.println(ins);
				}
			});
		}
		executor.shutdown();
	}
	
	private static class Nested {
		private static Singleton instance = new Singleton();
	}
}
