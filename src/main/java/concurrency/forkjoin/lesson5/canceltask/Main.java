package concurrency.forkjoin.lesson5.canceltask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author chengnl
 * @E-mail: chengnengliang@vrvmail.com.cn
 * @date 2014年12月17日 下午10:06:19
 * @version V1.0
 * @Description: 测试
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		TaskManager  taskManager= new TaskManager();
		FolderProcessor documents = new FolderProcessor(
				"/home/chengnl/git/study/", "TaskManager.java",taskManager);
		pool.execute(documents);
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.DAYS);
		System.out.printf("Documents search End.\n");
	}
}
