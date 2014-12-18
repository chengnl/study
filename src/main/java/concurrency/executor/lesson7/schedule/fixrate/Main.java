package concurrency.executor.lesson7.schedule.fixrate;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
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
		ScheduledThreadPoolExecutor executor=(ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(1);
		System.out.printf("Main: Starting at: %s\n",new Date());
	    ScheduledFuture<?> result=executor.scheduleAtFixedRate(new Task("test"),1,2 , TimeUnit.SECONDS);
		
		for (int i=0; i<20; i++){
			System.out.printf("Main: Delay: %d\n",result.
			getDelay(TimeUnit.MILLISECONDS));
			//线程睡眠500毫秒
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			    //TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
			e.printStackTrace();
			}
		}
		
		executor.shutdown();
		//executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
		try {
			TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Main: Finished at: %s\n",new Date());
	}

}
