package syntool.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月1日 下午3:01:19
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		List<String> buffer1 = new ArrayList<String>();
		List<String> buffer2 = new ArrayList<String>();
		
		Exchanger<List<String>> exchange = new Exchanger<List<String>>();
		
		Thread  producerThread = new Thread(new Producer(buffer1,exchange) );
		Thread  consumerThread = new Thread(new Consumer(buffer2,exchange) );
		consumerThread.start();
		producerThread.start();
		
	}

}
