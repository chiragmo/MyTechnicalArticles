package InterThreadCommunication.Latch;
import java.util.concurrent.BlockingQueue;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;


public class Consumer implements Runnable {
	BlockingQueue<List<String>> qu;
	List<String> li;
	CountDownLatch lt;

	public Consumer(BlockingQueue<List<String>> qu, CountDownLatch lt) {
	this.qu = qu;
	li = new LinkedList<>();
	this.lt = lt;	
	}

	public void run() {
		System.out.println("Consumer thread started");
		System.out.println("Consumer thread is awaiting for all latches");
		try {
            lt.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       System.out.println("\nPrinting the elements after successful transfer from Producer Thread");
        for(int i=0; i<2; i++) {
        	List<String> li = qu.remove();
        	 System.out.println(li);       	
	}
	System.out.println("Consumer thread ended");
}
}