package syn.fair;

import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月12日 上午10:06:10
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Main {

	public static void main(String[] args) {
		PrintQueue   printQueue = new PrintQueue();
		Thread thread[] = new Thread[10];
		for(int i=0;i<10;i++){
			thread[i] = new Thread(new Job(printQueue),"Thread"+i);
		}
		for(int i=0;i<10;i++){
			thread[i].start();
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
