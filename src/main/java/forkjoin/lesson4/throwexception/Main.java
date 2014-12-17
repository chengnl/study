package forkjoin.lesson4.throwexception;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author chengnl
 * @E-mail:chengnengliang@vrvmail.com.cn
 * @date 2014年12月15日 下午5:19:03
 * @version 1.0
 * @Description:测试
 */
public class Main {

	public static void main(String[] args) {
		int array[] = new int[100];
		Task task = new Task(array, 0, array.length);
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);

		pool.shutdown();

		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (task.isCompletedAbnormally()) {
			System.out.printf("Main: An exception has ocurred\n");
			System.out.printf("Main: %s\n", task.getException());
		}
		System.out.printf("Main: Result: %d", task.join());
	}

}
