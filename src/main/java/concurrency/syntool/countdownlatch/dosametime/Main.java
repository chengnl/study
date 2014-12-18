package concurrency.syntool.countdownlatch.dosametime;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月19日 下午4:14:09
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		int persons =10;
		CountDownLatch  count = new CountDownLatch(1);
		for(int i=0;i<persons;i++){
			Race  person = new Race(count,"runner"+i);
			Thread  personThread = new Thread(person);
			personThread.start();
		}
		TimeUnit.SECONDS.sleep(3);
        System.out.println("start running");
        count.countDown();
	}

}
