package concurrency.collection.lesson4.delayqueue;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月24日 上午10:51:53
 *@version 1.0
 *@Description:任务
 */
public class Task implements Runnable {
    private int id;
    private DelayQueue<Event>  delayQueue;
    public Task(int id,DelayQueue<Event>  delayQueue) {
    	this.id=id;
    	this.delayQueue=delayQueue;
	}
	@Override
	public void run() {
        Date date= new Date();
        date.setTime(date.getTime()+this.id*1000);
        System.out.printf("task: %d, delayTime=%s \n",this.id,date.toLocaleString());
        for (int i = 0; i < 100; i++) {
            Event  event = new Event(date);
            this.delayQueue.add(event);
		}		
	}

}
