package syntool.semaphore;

import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月18日 下午6:16:23
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		PrintQueue  printQueue= new  MultiPrinter();
		//PrintQueue  printQueue= new  OnePrinter();
		Thread threads[] = new Thread[10];
        for(int i=0;i<10;i++){
        	threads[i] = new Thread(new PrintJob(printQueue),"THREAD"+i);
        }
        for(int i=0;i<10;i++){
        	threads[i].start();
        	//TimeUnit.MILLISECONDS.sleep(100);
        }
	}

}
