package concurrency.collection.lesson6.threadlocalrandom;

import java.util.concurrent.ThreadLocalRandom;

/**
 *@author chengnl
 *@date 2014年12月25日 下午4:39:06
 *@version 1.0
 *@Description:随机
 */
public class TaskLocalRandom implements Runnable{
	public TaskLocalRandom() {
		ThreadLocalRandom.current();
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.printf("%s:TaskLocalRandom random=%d \n",
					Thread.currentThread().getName(),ThreadLocalRandom.current().nextInt(10));
		}
	}
}
