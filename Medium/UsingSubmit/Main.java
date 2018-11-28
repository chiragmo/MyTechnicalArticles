package Medium.UsingSubmit;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Main {
	public static void main(String[] args) {
		List<Integer> listRandomNum = new ArrayList<Integer>();
		List<Future<String>> taskFutures = new ArrayList<Future<String>>();
	    ExecutorService service = Executors.newFixedThreadPool(10);
	
	/*
	* Adding randomly generated 9-digit number to the list 
    */
	for(int i=0; i<8; i++) {
		Random rand = new Random();
		int number =  rand.nextInt(999999999) + 100000000;
		listRandomNum.add(number);
	}

	final long recTimeStart = System.currentTimeMillis();

	/*
	* Use submit() for each of the tasks and put the futures in the list
	*/
	for(Integer num:listRandomNum) {
		Future<String> task = service.submit(new IsPrime(num));
		taskFutures.add(task);
	}
	
	/*
	* Use isDone()method of Future to check for the task completeness and loop until 
	* all the tasks from the list is completed
	*/
	while(taskFutures.size() != 0) {
	for(int i=0; i<taskFutures.size(); i++) {
		if(taskFutures.get(i).isDone()) {
			String ans = "";
			try{
			 ans = taskFutures.get(i).get();
		}
		catch(Exception e){
			System.err.println(e);
		}
			System.out.println(ans);
			taskFutures.remove(i);
		}
	}
   }

   final long recTimeEnd = System.currentTimeMillis();

   service.shutdown();
   while(!service.isTerminated()) {
   	/**
   	* Wait for the ExecutorService to get fully terminated
   	*/
   }
   System.out.println("\nTime capture for the async: "+(recTimeEnd-recTimeStart));
   System.out.println("End of the program");
}
}