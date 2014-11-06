package thread.threadlocal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 下午4:24:55
 *@version 1.0
 *@Description:安全
 */
public class SafeTask implements Runnable{
    private  ThreadLocal<Date>  date = new ThreadLocal<Date>(){
    	protected Date initialValue(){
			return new Date();
    	}
    };
	@Override
	public void run() {
		System.out.printf("starting thread :%s  :  %s \n",Thread.currentThread().getId(),date.get());
		try {
			TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("thread finished:%s  :  %s \n",Thread.currentThread().getId(),date.get());
	}
	public static void main(String args[]) throws InterruptedException{
		SafeTask  task = new SafeTask();
	    for(int i=0;i<3;i++){
	    	Thread  thread = new Thread(task);
	    	thread.start();
	    	TimeUnit.SECONDS.sleep(2);
	    }
	}
}
