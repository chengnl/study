package concurrency.customized.lesson3.threadfactory;

import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@date 2015年1月21日 下午5:31:26
 *@version 1.0
 *@Description:任务
 */
public class Task implements Runnable{

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Task:%s  \n",Thread.currentThread().getName());
	}

}
