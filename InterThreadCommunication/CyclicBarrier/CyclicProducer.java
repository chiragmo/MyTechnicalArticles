package InterThreadCommunication.CyclicBarrier;
import java.util.concurrent.BlockingQueue;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.CyclicBarrier;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;


public class CyclicProducer implements Runnable {
	BlockingQueue<List<String>> qu;
	List<String> li;
	CyclicBarrier barrier;

	public CyclicProducer(BlockingQueue<List<String>> qu, CyclicBarrier barrier) {
		this.qu = qu;
		li = new LinkedList<>();
		this.barrier = barrier;	
	}
	public void run() {
		System.out.println("Producer thread is started....");
		try(Scanner sc = new Scanner(new File("input.txt"))) {
			while (sc.hasNextLine()) {
     		   li.add(sc.nextLine());
			}
		}
		catch(IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		System.out.println("Producer thread finished reading the file....");
		 int i = 3; boolean done = false;
		 while (i>0) {
		 try{
       		qu.add(li); 
       		done = true;
       		break;
       	}catch(IllegalStateException ex) {
       	 System.out.println("Producer thread was interrupted while adding to the queue");
       	 System.out.println("Retrying after 10 seconds");	
       	 i--;	
       		}
   		}
   		if(!done) {
   			System.out.println("There was a problem adding to the queue");
   			System.exit(1);
   		}
		System.out.println("Producer Thread has reached the cyclic barrier");
		try {
		barrier.await();
		} catch(InterruptedException e) {
            e.printStackTrace();
        } catch(BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("Producer Thread ended");
	}
}