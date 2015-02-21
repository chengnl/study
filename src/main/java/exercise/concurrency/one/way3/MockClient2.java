package exercise.concurrency.one.way3;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chengnl
 * @date 2015年2月10日 下午11:34:50
 * @version 1.0
 * @Description:TODO类描述
 */
public class MockClient2 {
	final static int MAX_QPS = 10;

	final static Semaphore semaphore = new Semaphore(MAX_QPS);

	final static AtomicInteger accessCount = new AtomicInteger(0);

	public static void main(String[] args) throws Exception {

		// release semaphore thread
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				semaphore.release(accessCount.getAndSet(0));
			}
		}, 1000, 1000, TimeUnit.MILLISECONDS);

		// lots of concurrent calls: 100 * 1000
		ExecutorService pool = Executors.newFixedThreadPool(100);
		for (int i = 100; i > 0; i--) {
			final int x = i;
			pool.submit(new Runnable() {
				@Override
				public void run() {
					for (int j = 1000; j > 0; j--) {
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						semaphore.acquireUninterruptibly(1);
						accessCount.incrementAndGet();

						remoteCall(x, j);
					}
				}
			});
		}

		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.HOURS);
		System.out.println("done");
	}

	private static void remoteCall(int i, int j) {
		System.out.println(String.format("%s – %s: %d %d", new Date(),
				Thread.currentThread(), i, j));
	}
}
