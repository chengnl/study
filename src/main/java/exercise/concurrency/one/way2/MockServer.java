package exercise.concurrency.one.way2;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 *@author chengnl
 *@date 2015年2月10日 下午8:52:25
 *@version 1.0
 *@Description:模拟服务
 */
public class MockServer {
	private AtomicLong count = new AtomicLong(100);
	
	public MockServer() {
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable(){

			@Override
			public void run() {
				
				count.set(100);
				System.out.println("1 秒过去了");
			}
			
		}, 1, 1, TimeUnit.SECONDS);
	}
	
	public int visit(){
         if(count.decrementAndGet()>0){
             System.out.println("允许访问。"+count.longValue());
             return 0;
         }
         return 1;
	}
}
