package thread.threadgroup;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 下午5:22:02
 *@version 1.0
 *@Description:TODO 类描述
 */
public class SearchTask implements Runnable{
	private Result result;
	public SearchTask(Result result){
		this.result=result;
	}
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.printf("thread :%s  start \n",name);
		 try {
			doTask();
			result.setName(name);
		} catch (InterruptedException e) {
			 System.out.printf("thread :%s  Interrupted \n",name); 
			return;
		}
		 System.out.printf("thread :%s  End \n",name); 
	}
   private void doTask() throws InterruptedException{
	   int value = (int )(Math.random()*10);
	   System.out.printf("thread :%s :%d \n",Thread.currentThread().getName(),value);
	   TimeUnit.SECONDS.sleep(value);
   }
}
