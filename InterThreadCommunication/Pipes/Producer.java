package InterThreadCommunication.Pipes;
import java.io.PipedWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Producer implements Runnable {
	PipedWriter pipedWriter;

	public Producer(PipedWriter pipedWriter) {
		this.pipedWriter = pipedWriter;
	}

	public void run() {
		System.out.println("Producer thread is started....");
		System.out.println("Producer thread is sending the data to Consumer....");
		try(Scanner sc = new Scanner(new File("input.txt"))) {
			while (sc.hasNextLine()) {
     		   pipedWriter.write(sc.nextLine()+"\n");
			}
		}
		catch(IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		try {
			pipedWriter.close();
		} catch (IOException e) {
			// Ignore 
		}
		System.out.println("Producer thread has sent the complete data");
		System.out.println("Producer thread ended");

	}

}