package concurrency.thread.daemon;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 上午11:28:04
 *@version 1.0
 *@Description:TODO 类描述
 */
public class DaemonTest {
     public static void main(String[] args){
    	 Deque<Event> deque= new ArrayDeque<Event>();
    	 WriterTask   write = new WriterTask(deque);
    	 for(int i =0;i<3;i++){
    		 Thread thread = new Thread(write);
    		 thread.start();
    	 }
    	 CleanerTask  clean = new CleanerTask(deque);
    	 clean.start();
     }
}
