package concurrency.customized.lesson5.myscheduledtask;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@date 2015年1月21日 下午7:21:02
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Main{
	public static void main(String[] args) throws Exception{
	
		//20. 创建一个 MyScheduledThreadPoolExecutor 对象，名为 executor。使用2作为参数来在池中获得2个线程。
		MyScheduledThreadPoolExecutor executor=new MyScheduledThreadPoolExecutor(2);
	
		//21. 创建 Task 对象，名为 task。把当前日期写入操控台。
		Task task=new Task(); System.out.printf("Main: %s\n",new Date());
	
		//22. 使用 schedule() 方法发送一个延迟任务给执行者。此任务在延迟一秒后运行。
		executor.schedule(task, 1, TimeUnit.SECONDS);
	
		//23. 让主线程休眠3秒。
		TimeUnit.SECONDS.sleep(3);
	
		//24. 创建另一个 Task 对象。再次在操控台打印当前日期。
		task=new Task();
		System.out.printf("Main: %s\n",new Date());
	
		//25. 使用方法 scheduleAtFixedRate()发送一个周期性任务给执行者。此任务在延迟一秒后被运行，然后每3秒执行。
		executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
	
		//26. 让主线程休眠10秒。
		TimeUnit.SECONDS.sleep(10);
	
		//27. 使用 shutdown() 方法关闭执行者。使用 awaitTermination() 方法等待执行者的完结。
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
	
		//28. 写信息到操控台表明任务结束。
		System.out.printf("Main: End of the program.\n");
	}
}
