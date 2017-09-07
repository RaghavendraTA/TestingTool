import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executor implements Callable {

	@Override
	public String call() throws InterruptedException {
		Thread.sleep(5000);
		return "Hello";
	}

	public static void main(String args[]) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		Callable<String> callable = new Solution();
		Future<String> value = executor.submit(callable);
		try {
			System.out.println("The returned value is : " + value.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}

}
