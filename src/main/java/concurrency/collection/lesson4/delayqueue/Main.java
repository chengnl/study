package concurrency.collection.lesson4.delayqueue;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月24日 上午11:02:29
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		    DelayQueue<Event>  delayQueue = new DelayQueue<>();
		 
			Thread  threads[] = new Thread[5];
			for(int i=0;i<5;i++){
				Task   task = new Task(i,delayQueue);
				threads[i]=new Thread(task);
			}
			for(int i=0;i<5;i++){
				threads[i].start();
			}
			for(int i=0;i<5;i++){
				try {
					threads[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
			
//			do {
//					int counter=0;
//					Event event;
//					do {
//						event=delayQueue.poll();
//						if (event!=null) counter++;
//					} while (event!=null);
//					System.out.printf("At %s you have read %d events\n",new Date(),counter);
//					try {
//						TimeUnit.MILLISECONDS.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}while (delayQueue.size()>0);
			
			do {
				Event  event =delayQueue.take();
				System.out.printf("%s  event =%s\n",new Date(),event.toString());
			}while (delayQueue.size()>0);
	}
}
