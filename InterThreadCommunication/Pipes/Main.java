package InterThreadCommunication.Pipes;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		 PipedWriter writer = null;
		 PipedReader reader = null;
		try{
	   writer = new PipedWriter();
       reader = new PipedReader(writer);
  		}
  	  catch(IOException e) {
  			System.out.println(e);
  			System.exit(1);
  		}
      Producer p1 = new Producer(writer);
	  Consumer c1 = new Consumer(reader);
	  new Thread(p1).start();
	  new Thread(c1).start();
	}
}