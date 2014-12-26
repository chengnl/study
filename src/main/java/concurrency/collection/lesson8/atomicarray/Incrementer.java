package concurrency.collection.lesson8.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *@author chengnl
 *@date 2014年12月26日 上午10:12:00
 *@version 1.0
 *@Description:增加计数
 */
public class Incrementer implements Runnable{
    private  AtomicIntegerArray  array;
	public Incrementer( AtomicIntegerArray  array) {
		this.array=array;
	}
	@Override
	public void run() {
		for(int i=0;i<array.length();i++){
			this.array.addAndGet(i, 10);
		}
	}

}
