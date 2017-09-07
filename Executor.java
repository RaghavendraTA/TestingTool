package hackerearth;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executor implements Callable {

	@Override
	public String call() throws InterruptedException {
		Thread.sleep(1000);
		return "Hello";
	}

	public static void main(String args[]) throws Exception {
		
		long startTime = System.nanoTime();
		ExecutorService executor = Executors.newFixedThreadPool(5);
		Callable<String> callable = () -> {
			return "Hi";
		};
		Future<String> value = executor.submit(callable);
		System.out.println("The returned value is : " + value.get());
		executor.shutdown();
		System.out.println("Took " + ((System.nanoTime() - startTime) / 1000000)+ " ms"); 
	}

}
