package executor.lesson1.createexecutor;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月1日 下午3:36:25
 *@version 1.0
 *@Description:模拟任务
 */
public class Task implements Runnable{
    private Date  initDate;
    private String name;
    public Task(Date  initDate,String name){
    	this.initDate=initDate;
    	this.name=name;
    }
	@Override
	public void run() {
		System.out.printf("%s:task %s : created on :%s .\n",Thread.currentThread().getName(),name,initDate);		
		System.out.printf("%s:task %s : started on :%s .\n",Thread.currentThread().getName(),name,new Date());		
		long  duration = new Random().nextInt(10);
		System.out.printf("%s:task %s : doing a task during  %d seconds  .\n",Thread.currentThread().getName(),name,duration);		
	    try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    System.out.printf("%s:task %s : finished on  :%s .\n",Thread.currentThread().getName(),name,new Date());		
	}

}
