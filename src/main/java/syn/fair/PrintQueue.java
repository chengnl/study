package syn.fair;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月12日 上午9:52:32
 *@version 1.0
 *@Description:打印队列
 */
public class PrintQueue {
	 private final Lock queueLock  = new ReentrantLock(true);
	 //private final Lock queueLock  = new ReentrantLock(false);
	 public void printJob(Object document){
		 queueLock.lock();
		 try{
			 long duration = (long)(Math.random()*10000);
			 System.out.println(Thread.currentThread().getName()+": printqueue :printing a job during  "
					 +(duration/1000)+"seconds");
			 TimeUnit.SECONDS.sleep(duration/1000);
		 }catch(InterruptedException e){
			 e.printStackTrace();
		 }finally{
			 queueLock.unlock();
		 }
		 queueLock.lock();
		 try{
			 long duration = (long)(Math.random()*10000);
			 System.out.println(Thread.currentThread().getName()+": printqueue :printing a job during  "
					 +(duration/1000)+"seconds");
			 TimeUnit.SECONDS.sleep(duration/1000);
		 }catch(InterruptedException e){
			 e.printStackTrace();
		 }finally{
			 queueLock.unlock();
		 }
	}

}
