package concurrency.collection.lesson1.concurrentlinkeddeque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月18日 下午5:55:06
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		 ConcurrentLinkedDeque<String> list = new   ConcurrentLinkedDeque<String>();
         Thread[]   addThreads  = new Thread[100];
         for(int i=0;i<addThreads.length;i++){
        	 Thread  thread = new Thread(new AddTask(list,"addTask"+i));
        	 addThreads[i]=thread;
        	 addThreads[i].start();
         }
         for(int i=0;i<addThreads.length;i++){
        	 try {
				addThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
         }
         System.out.printf("Main: Size of the List: %d\n",list.size());
         Thread[]   poolThreads  = new Thread[100];
         for(int i=0;i<poolThreads.length;i++){
        	 Thread  thread = new Thread(new PollTask(list,"pollTask"+i));
        	 poolThreads[i]=thread;
        	 poolThreads[i].start();
         }
         for(int i=0;i<poolThreads.length;i++){
        	 try {
        		 poolThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
         }
         System.out.printf("Main: Size of the List: %d\n",list.size());
	}

}
