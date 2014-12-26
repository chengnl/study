package concurrency.collection.lesson8.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *@author chengnl
 *@date 2014年12月26日 上午10:18:21
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		AtomicIntegerArray  array  = new AtomicIntegerArray(100);
		Thread  threadsIncrementer[]  = new Thread[100];
		Thread  threadsDecrementer[]  = new Thread[100];
        for(int i=0;i<100;i++){
        	Thread  threadIncrementer  = new Thread(new Incrementer(array));
        	Thread  threadDecrementer  = new Thread(new Decrementer(array));
        	threadsIncrementer[i]=threadIncrementer;
        	threadsDecrementer[i]=threadDecrementer;
        	threadsIncrementer[i].start();
        	threadsDecrementer[i].start();
        }
        for(int i=0;i<100;i++){
        	threadsIncrementer[i].join();
        	threadsDecrementer[i].join();
        }
        for(int i=0;i<array.length();i++){
        	int  value=array.get(i);
        	System.out.printf("array %d  value=%d \n",i,value);
        }
        
	}

}
