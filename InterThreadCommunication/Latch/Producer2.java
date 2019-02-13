package InterThreadCommunication.Latch;
import java.util.concurrent.BlockingQueue;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Producer2 implements Runnable {
	BlockingQueue<List<String>> qu;
	List<String> li;
	CountDownLatch lt;

	public Producer2(BlockingQueue<List<String>> qu, CountDownLatch lt) {
		this.qu = qu;
		li = new LinkedList<>();
		this.lt = lt;	
	}
	public void run() {
		System.out.println("Producer2 thread is started....");
		try(Scanner sc = new Scanner(new File("input2.txt"))) {
			while (sc.hasNextLine()) {
     		   li.add(sc.nextLine());
			}
		}
		catch(IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		System.out.println("Producer2 thread finished reading the file....");
		 int i = 3; boolean done = false;
		 while (i>0) {
		 try{
       		qu.add(li); 
       		done = true;
       		break;
       	}catch(IllegalStateException ex) {
       	 System.out.println("Producer2 thread was interrupted while adding to the queue");
       	 System.out.println("Retrying after 10 seconds");	
       	 i--;	
       		}
   		}
   		if(!done) {
   			System.out.println("There was a problem adding to the queue");
   			System.exit(1);
   		}
		System.out.println("Producer2 Thread will be count down the latch");

		lt.countDown();
	
        System.out.println("Producer2 Thread ended");
	}
}