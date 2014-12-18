package concurrency.thread.threadlocal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 下午4:18:14
 *@version 1.0
 *@Description:不安全
 */
public class UnsafeTask implements Runnable{
    private Date  date;
	@Override
	public void run() {
		date = new Date();
		System.out.printf("starting thread :%s  :  %s \n",Thread.currentThread().getId(),date);
		try {
			TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("thread finished:%s  :  %s \n",Thread.currentThread().getId(),date);
	}
    
	public static void main(String args[]) throws InterruptedException{
		UnsafeTask  task = new UnsafeTask();
	    for(int i=0;i<3;i++){
	    	Thread  thread = new Thread(task);
	    	thread.start();
	    	TimeUnit.SECONDS.sleep(2);
	    }
	}
}
