package concurrency.executor.lesson9.futuretask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月4日 下午3:26:54
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		ExecutorService executor=(ExecutorService)Executors.newCachedThreadPool();
		ResultTask resultTasks[]=new ResultTask[5];
		for (int i=0; i<5; i++) {
			Task executableTask=new Task("Task "+i);
			resultTasks[i]=new ResultTask(executableTask);
			executor.submit(resultTasks[i]);
		}
		try {
			TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		for (int i=0; i<resultTasks.length; i++) {
			resultTasks[i].cancel(true);
		}
		
		for (int i=0; i<resultTasks.length; i++) {
			try {
				if (!resultTasks[i].isCancelled()){
				    System.out.printf("%s\n",resultTasks[i].get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}

}
