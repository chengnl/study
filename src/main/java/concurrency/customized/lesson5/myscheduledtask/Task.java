package concurrency.customized.lesson5.myscheduledtask;

import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@date 2015年1月21日 下午7:22:06
 *@version 1.0
 *@Description:测试任务
 */
//17.  创建一个类，名为 Task，实现 Runnable 接口。
public class Task implements Runnable {

	//18. 实现方法 run() 。在任务开始时打印一条信息，再让当前线程进入休眠2秒。最后在任务结束时，再打印另一条信息。
	@Override
	public void run() {
		System.out.printf("Task: Begin.\n");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Task: End.\n");
	}
}
