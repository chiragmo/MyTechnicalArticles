package InterThreadCommunication.CyclicBarrier;
import java.util.concurrent.BlockingQueue;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;


public class CyclicConsumer implements Runnable {
	BlockingQueue<List<String>> qu;
	List<String> li;
	CyclicBarrier barrier;

	public CyclicConsumer(BlockingQueue<List<String>> qu, CyclicBarrier barrier) {
	this.qu = qu;
	li = new LinkedList<>();
	this.barrier = barrier;	
	}

	public void run() {
		System.out.println("Consumer thread is started....");
		System.out.println("Consumer Thread has reached the cyclic barrier");
		try {
		barrier.await();
		} catch(InterruptedException e) {
            e.printStackTrace();
        } catch(BrokenBarrierException e) {
            e.printStackTrace();
        }
        if(qu.peek() == null) {
        	System.out.println("Nothing is inserted in queue");
        	System.exit(1);
        }
        li = qu.poll();
        System.out.println("\nPrinting the elements after successful transfer from Producer Thread");
        for(int i=0; i<li.size(); i++){
        	System.out.println(li.get(i));
        }
        System.out.println("Consumer Thread ended");
	}


}