package concurrency.syn.condition;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 上午10:03:07
 *@version 1.0
 *@Description:存储  wait notify notifyall
 */
public class EventStorage {
        private int maxSize;
        private LinkedList<Date> storage;
        public EventStorage(){
        	maxSize=10;
        	storage= new LinkedList<Date>();
        }
        public synchronized void set(){
        	while(storage.size()==maxSize){
        		try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
    		storage.offer(new Date());
    		System.out.printf("Set  : %d\n ", storage.size());
    		notifyAll();
        }
        public synchronized void get(){
        	while(storage.size()==0){
        		try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
    		System.out.printf("Get  : %d  : %s\n", storage.size(),storage.poll());
    		notifyAll();
        }
}
