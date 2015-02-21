package exercise.concurrency.one.way2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@date 2015年2月10日 下午9:15:51
 *@version 1.0
 *@Description:模拟客户端
 */
public class MockClient {
    public static void main(String[] args) {
    	final MockServer  mockServer= new MockServer();
    	ExecutorService  executor = Executors.newFixedThreadPool(10);
		int i=1000;
		while(i>0){
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executor.execute(new Runnable(){
				@Override
				public void run() {
					int result=mockServer.visit();
					if(result==1){
					   System.out.println(result);
					   //System.exit(0);
					}
				}
			});
			i--;
		}
		executor.shutdown();
	}
}
