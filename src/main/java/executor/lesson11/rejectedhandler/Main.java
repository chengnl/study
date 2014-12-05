package executor.lesson11.rejectedhandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月5日 下午4:36:52
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
		executor.setRejectedExecutionHandler(new RejectedTaskController());
		System.out.printf("Main: Starting.\n");
		for(int i=0;i<5;i++){
			executor.submit(new Task("task"+i));
		}
		System.out.printf("Main: Shutting down the Exceutor.\n");
		executor.shutdown();
		System.out.printf("Main: Sending another Task.\n");
		executor.submit(new Task("RejectedTask"));
		System.out.printf("Main: End.\n");
	}

}
