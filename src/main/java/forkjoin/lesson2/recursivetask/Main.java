package forkjoin.lesson2.recursivetask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author chengnl
 * @E-mail:chengnengliang@vrvmail.com.cn
 * @date 2014年12月17日 上午11:13:41
 * @version 1.0
 * @Description:测试类
 */
public class Main {
	public static void main(String[] args) {
		Document mock = new Document();
		String[][] document = mock.generateDocument(100, 1000, "the");
		DocumentTask task = new DocumentTask(document, 0, 100, "the");
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n",
					pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n",
					pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
			System.out.printf("******************************************\n");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());

		pool.shutdown();

		try {
			System.out.printf("Main: The word appears %d in the document",
					task.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		try {
			System.out.printf("Main: The word appears %d in the document",
					task.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
