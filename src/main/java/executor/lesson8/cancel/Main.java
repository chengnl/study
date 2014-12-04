package executor.lesson8.cancel;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月4日 上午11:39:28
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
		Future<String> result=executor.submit(new Task("test"));
		try {
			TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
			e.printStackTrace();
		}
		result.cancel(true);
		
		System.out.printf("Main: Canceled: %s\n",result.isCancelled());
		System.out.printf("Main: Done: %s\n",result.isDone());
		executor.shutdown();
		System.out.printf("Main: The executor has finished\n");

	}

}
