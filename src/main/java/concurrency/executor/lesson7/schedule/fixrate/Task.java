package concurrency.executor.lesson7.schedule.fixrate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月4日 上午10:07:02
 *@version 1.0
 *@Description:任务
 */
public class Task implements Runnable{
    private String name;
    public Task(String name){
    	this.name=name;
    }
	@Override
	public void run() {
		System.out.printf("%s: Starting at : %s\n",name,new Date());
//		try {
//			TimeUnit.SECONDS.sleep(5);
//			} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.printf("%s: end at : %s\n",name,new Date());
	}
}
