package thread.threadgroup;

import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 下午5:30:06
 *@version 1.0
 *@Description:线程组测试
 */
public class ThreadGroupTest {
      public static void main(String[] args){
    	  ThreadGroup  tg = new ThreadGroup("Searcher");
    	  Result result = new Result();
    	  SearchTask  searchTask = new SearchTask(result);
    	  for(int i=0;i<5;i++){
    		  Thread thread = new Thread(tg,searchTask);
    		  thread.start();
    			  try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    	  }
    	  System.out.printf("number of threads :%d \n",tg.activeCount());
    	  System.out.printf("information about the thread group \n");
    	  tg.list();
    	  
    	  Thread[] threads = new Thread[tg.activeCount()];
    	  tg.enumerate(threads);
    	  for(int i=0;i<tg.activeCount();i++){
    		  System.out.printf("thread %s : %s \n",threads[i].getName(),threads[i].getState());
    	  }
    	  
    	  waitFinish(tg);
    	  
    	  tg.interrupt();
      }
      
      private static void waitFinish(ThreadGroup  tg ){
    	  while(tg.activeCount()>4){
    			try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    	  }
      }
}
