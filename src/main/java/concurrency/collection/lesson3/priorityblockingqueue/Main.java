package concurrency.collection.lesson3.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月23日 上午10:06:59
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		PriorityBlockingQueue<Event>  list = new PriorityBlockingQueue<>();
		Thread  threads[] = new Thread[5];
		for(int i=0;i<5;i++){
			Task   task = new Task(i,list);
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
		System.out.printf("main: queue size= %d \n",list.size());
		for(int i=0;i<threads.length*1000;i++){
			Event  event=list.poll();
			System.out.printf("main: event id =%d, event priority =%d \n",event.getId(),event.getPriority());
		}
	}

}
