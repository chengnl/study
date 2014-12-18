package concurrency.syntool.countdownlatch.dosametime;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月19日 下午4:08:47
 *@version 1.0
 *@Description:赛跑
 */
public class Race implements Runnable{
      private CountDownLatch  count;
      private String runner;
      public Race(CountDownLatch  count,String runner){
    	  this.count=count;
    	  this.runner=runner;
      }
	@Override
	public void run() {
		try {
			System.out.println(this.runner+"   is ready");
			this.count.await();
			System.out.println(this.runner+"   is running.........");
			Random  random = new Random();
			TimeUnit.SECONDS.sleep(random.nextInt(10));
			System.out.println(this.runner+"   is to the end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
