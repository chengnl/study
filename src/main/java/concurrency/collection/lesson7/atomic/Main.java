package concurrency.collection.lesson7.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 *@author chengnl
 *@date 2014年12月25日 下午6:21:44
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		     final Account   account = new Account();
		     Thread[]  addThreads = new Thread[5];
		     for(int i=0;i<addThreads.length;i++){
		    	 Thread   thread = new Thread(new Runnable(){
					@Override
					public void run() {
						   for(int i=0;i<5;i++){
							   account.addBalance(1000);
							   System.out.printf("account add  1000  balance = %d \n",account.getBalance());
						   }
					}
		    	 });
		    	 addThreads[i]=thread;
		     }
		     for(int i=0;i<addThreads.length;i++){
		    	 addThreads[i].start();
		     }
		     
		     Thread[]  subThreads = new Thread[5];
		     for(int i=0;i<subThreads.length;i++){
		    	 Thread   thread = new Thread(new Runnable(){
					@Override
					public void run() {
						   for(int i=0;i<5;i++){
							   account.subtractAmount(1000);
							   System.out.printf("account sub  1000  balance = %d \n",account.getBalance());
						   }
					}
		    	 });
		    	 subThreads[i]=thread;
		     }
		     for(int i=0;i<subThreads.length;i++){
		    	 subThreads[i].start();
		     }
		     
		     for(int i=0;i<5;i++){
		    	 addThreads[i].join();
		    	 subThreads[i].join();
		     }
		     System.out.printf("account  balance = %d \n",account.getBalance());
	}

}
