package InterThreadCommunication.Pipes;
import java.io.PipedReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

public class Consumer implements Runnable {
	PipedReader reader;
	List<String> li;	

	public Consumer(PipedReader reader) {
		this.reader = reader;
		li = new LinkedList<String>();
	}

	public void run() {
		System.out.println("Consumer thread is started....");
		try {
		while(!reader.ready()) {
		// wait for Writer pipe 
		}
		System.out.println("Consumer thread will start to read data from pipe");
		int data; 
		String s = "";
		while(true) {
			int chunk = reader.read();
			char ch = (char) chunk;
			if(chunk == -1) {
				li.add(s);
				break;
			}
			if(ch == '\n') {
			li.add(s);
			s = ""; 
			continue;
			}
			s = s + String.valueOf(ch);
		}
	} catch(IOException ex) {
		System.out.print(ex);
		System.exit(1);
	}
	try {
			reader.close();
		} catch (IOException e) {
			// Ignore
		}
		System.out.println("Printing the transferred data over pipe");
		for (int i=0; i<li.size() ; i++) {
			System.out.println(li.get(i));
		}
		System.out.println("Consumer thread ended");
	}
}