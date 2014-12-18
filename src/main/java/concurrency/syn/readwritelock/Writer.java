package concurrency.syn.readwritelock;

import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月13日 上午10:12:32
 *@version 1.0
 *@Description:写线程
 */
public class Writer implements Runnable{
      private PricesInfo pricesInfo;
      public Writer (PricesInfo pricesInfo){
    	  this.pricesInfo=pricesInfo;
      }
	@Override
	public void run() {
		for(int i=0;i<3;i++){
			this.pricesInfo.setPrices(Math.random()*10, Math.random()*8);
//			try {
//				TimeUnit.SECONDS.sleep(2);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
      
}
