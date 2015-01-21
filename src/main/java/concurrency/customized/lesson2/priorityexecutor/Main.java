package concurrency.customized.lesson2.priorityexecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@date 2015年1月21日 下午3:39:20
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		ThreadPoolExecutor  executor= new ThreadPoolExecutor(2, 4, 5, TimeUnit.SECONDS, 
				new PriorityBlockingQueue<Runnable>());
		
		for(int i=0;i<4;i++){
			Task  task = new Task("task"+i,i);
			executor.execute(task);
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i=4;i<8;i++){
			Task  task = new Task("task"+i,i);
			executor.execute(task);
		}
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
