package concurrency.thread.daemon;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 上午11:12:31
 *@version 1.0
 *@Description:TODO 类描述
 */
public class WriterTask implements Runnable{
    private Deque<Event> deque;
    public WriterTask(Deque<Event> deque){
    	this.deque=deque;
    }
    @Override
	public void run() {
         	for(int i=0;i<100;i++){
         		Event event = new Event();
         		event.setDate(new Date());
         		event.setEvent(String.format("the thread %s has generated an event", Thread.currentThread().getId()));
         		System.out.printf("add event %s\n",event);
         		deque.addFirst(event);
         		try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
         	}	
	}

}
