package concurrency.collection.lesson2.linkedblockingdeque;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author chengnl
 * @E-mail:chengnengliang@vrvmail.com.cn
 * @date 2014年12月19日 下午4:36:26
 * @version 1.0
 * @Description:测试
 */
public class Main {
	public static void main(String[] args) {
		LinkedBlockingDeque<String> list = new LinkedBlockingDeque<String>(3);
		Client client = new Client(list);
		Thread thread = new Thread(client);
		thread.start();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				String request;
				try {
					request = list.take();
					System.out.printf("Main: Request: %s at %s. Size:%d\n",
							request, new Date(), list.size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Main: End of the program.\n");
	}
}
