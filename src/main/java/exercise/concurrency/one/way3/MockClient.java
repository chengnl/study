package exercise.concurrency.one.way3;
import java.util.Date;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import java.util.concurrent.Semaphore;

import java.util.concurrent.TimeUnit;
/**
 *@author chengnl
 *@date 2015年2月10日 下午11:28:56
 *@version 1.0
 *@Description:TODO类描述
 */
public class MockClient {


	    final static int MAX_QPS = 10;

	    final static Semaphore semaphore = new Semaphore(MAX_QPS);

	    public static void main (String ... args) throws Exception {

	        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {

	            @Override

	            public void run() {
	            	//存在问题，并发问题，如果这时，正好又用了呢，相当于释放不一致，释放多了
	            	int available = semaphore.availablePermits();
	            	//只释放用掉的许可证数量
	            	semaphore.release(MAX_QPS-available);

	            }

	        }, 1000, 1000, TimeUnit.MILLISECONDS);

	        //lots of concurrent calls:100 * 1000
	        ExecutorService pool = Executors.newFixedThreadPool(100);

	        for (int i=100;i>0;i--) {

	            final int x = i;

	            pool.submit(new Runnable() {

	                @Override

	                public void run() {

	                    for (int j=1000;j>0;j--) {

	                        semaphore.acquireUninterruptibly(1);
	                        remoteCall(x, j);

	                    }

	                }

	            });

	        }

	        pool.shutdown();

	        pool.awaitTermination(1, TimeUnit.HOURS);

	        System.out.println("DONE");
	    }

	    private static void remoteCall(int i, int j) {
	        System.out.println(String.format("%s - %s: %d %d",new Date(),
	            Thread.currentThread(), i, j));
	    }
}
