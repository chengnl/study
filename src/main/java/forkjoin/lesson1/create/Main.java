package forkjoin.lesson1.create;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月15日 下午5:19:03
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		  List<Product>  products = ProductListGenerator.generate(10000);
		  Task task=new Task(products, 0, products.size(), 0.20);
		  ForkJoinPool pool=new ForkJoinPool();
		  pool.execute(task);
		  
		do {
			System.out.printf("Main: Thread Count: %d\n",
					pool.getActiveThreadCount());
			System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
		
		pool.shutdown();
		
		if (task.isCompletedNormally()){
			System.out.printf("Main: The process has completed normally.\n");
	    }
		
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			if (product.getPrice() != 12) {
				System.out.printf("Product %s: %f\n", product.getName(),
						product.getPrice());
			}
		}
		
		System.out.println("Main: End of the program.\n");
	}

}
