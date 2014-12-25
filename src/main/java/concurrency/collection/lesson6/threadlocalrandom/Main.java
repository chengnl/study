package concurrency.collection.lesson6.threadlocalrandom;
/**
 *@author chengnl
 *@date 2014年12月25日 下午4:53:04
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
            for (int i = 0; i <5; i++) {
            	TaskLocalRandom  task  = new TaskLocalRandom();
                 Thread  thread = new Thread(task,"task"+i);
                 thread.start();
			}
	}

}
