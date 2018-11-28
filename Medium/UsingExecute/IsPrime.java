package Medium.UsingExecute;
import java.util.concurrent.Callable;

public class IsPrime implements Callable<String> {
	private int number;

	public IsPrime(int number){
		this.number = number;
	}

	@Override
	public String call() throws Exception {
		String num = String.valueOf(number);
		int flag = 0;
		for(int i=2; i< number; i++) {
			if(number%i == 0) {
				flag = 1;
			}
		}
		if(flag == 1) {
			return num+" is not a prime";
		}
		return num+" is a prime";
	}

	}

