package thread.sleep;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 上午10:18:32
 *@version 1.0
 *@Description:线程休眠
 */
public class ThreadSleep implements Runnable{

	@Override
	public void run() {
		   for(int i=0;i<10;i++){
			   System.out.printf("%s\n",new Date());
			    try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					 System.out.printf("%s\n","the threadsleep has bean interrupted");
				}
		   }
	}
    public static void main(String args[]) throws InterruptedException{
    	ThreadSleep   sleep = new ThreadSleep();
    	Thread  thread = new  Thread(sleep);
    	thread.start();
    	TimeUnit.SECONDS.sleep(5);
    	thread.interrupt();
    }   
}
