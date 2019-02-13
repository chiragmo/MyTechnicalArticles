package InterThreadCommunication.Latch;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {
	public static void main(String[] args) {
		/**
		* Producer1: Class.Producer, Producer2: Class.Producer2 and Consumer: Class.Consumer
		* Class.CountDownLatch is set at two
		*/
		CountDownLatch lt = new CountDownLatch(2);
		BlockingQueue<List<String>> qu = new LinkedBlockingQueue<>(2);
		Producer1 p1 = new Producer1(qu,lt);
		Producer2 p2 = new Producer2(qu,lt);	
		Consumer c = new Consumer(qu,lt);
		
		new Thread(p1).start();
		new Thread(p2).start();
		new Thread(c).start();
	}
}