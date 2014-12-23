package concurrency.collection.lesson3.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月23日 上午9:49:59
 *@version 1.0
 *@Description:任务
 */
public class Task implements Runnable{
    private int  id;
    private PriorityBlockingQueue<Event>  list;
	public Task(int  id,PriorityBlockingQueue<Event>  list) {
		this.id=id;
		this.list=list;
	}
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			Event  event =new Event(this.id,i);
			this.list.add(event);
		}
	}

}
