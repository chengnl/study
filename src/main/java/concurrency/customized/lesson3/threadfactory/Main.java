package concurrency.customized.lesson3.threadfactory;
/**
 *@author chengnl
 *@date 2015年1月21日 下午5:38:12
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		MyThreadFactory  myThreadFactory = new MyThreadFactory("myThreadFactory");
		Thread  thread = myThreadFactory.newThread(new Task());
		thread.start();
		thread.join();
		System.out.printf("Main: Thread information.\n");
		System.out.printf("%s\n",thread);
		System.out.printf("Main: End of the example.\n");
	}
}
