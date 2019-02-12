package InterThreadCommunication.CyclicBarrier;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Main {
	public static void main(String[] args) {
		/**
		* Producer: Class.CyclicProducer and Consumer: Class.CyclicConsumer
		* CyclicBarrier is set at two
		*/
		CyclicBarrier barrier = new CyclicBarrier(2);
		BlockingQueue<List<String>> qu = new LinkedBlockingQueue<>(1);
		CyclicProducer c1 = new CyclicProducer(qu,barrier);
		CyclicConsumer c2 = new CyclicConsumer(qu,barrier);	
		new Thread(c1).start();
		new Thread(c2).start();
	}
}