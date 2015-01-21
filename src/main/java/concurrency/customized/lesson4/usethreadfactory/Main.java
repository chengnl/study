package concurrency.customized.lesson4.usethreadfactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *@author chengnl
 *@date 2015年1月21日 下午6:47:08
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Main {

	public static void main(String[] args) {
		MyThreadFactory  myThreadFactory = new MyThreadFactory("myThreadFactory");
		ExecutorService  execute=Executors.newCachedThreadPool(myThreadFactory);
		execute.execute(new Task());
		execute.shutdown();
	}

}
