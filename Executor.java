import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Sol implements Callable {

	@Override
	public String call() throws InterruptedException {
		Thread.sleep(2000);
		return "trial";
	}
}

public class Executor implements Callable {

	@Override
	public String call() throws InterruptedException {
		Thread.sleep(1000);
		return "Hello";
	}

	public static void main(String args[]) {
		
		long startTime = System.nanoTime();
		ExecutorService executor = Executors.newFixedThreadPool(5);
		Callable<String> callable = new Solution();
		Callable<String> callable2 = new Sol();
		Future<String> value = executor.submit(callable);
		Future<String> value2 = executor.submit(callable2);
		try {
			System.out.println("The returned value is : " + value.get());
			System.out.println("The returned value is : " + value2.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		executor.shutdown();
		long endTime = System.nanoTime();
		System.out.println("Took " + ((endTime - startTime) / 1000000)+ " ms"); 
	}

}
