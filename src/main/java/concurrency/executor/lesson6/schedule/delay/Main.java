package concurrency.executor.lesson6.schedule.delay;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月4日 上午10:14:08
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		ScheduledExecutorService executor=Executors.newScheduledThreadPool(1);
		System.out.println("Main: start");
		for(int i=0;i<10;i++){
			executor.schedule(new Task(""+i), i+1, TimeUnit.SECONDS);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main: end");
	}

}
