package concurrency.syntool.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月18日 下午6:10:33
 *@version 1.0
 *@Description:控制一个资源
 */
public class OnePrinter implements PrintQueue {
        private  Semaphore  semaphore = new Semaphore(1);
        
        public void print(String msg){
        	try {
				semaphore.acquire();
				System.out.println(Thread.currentThread().getName()+":print msg ="+msg);
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				semaphore.release();
			}
        }
}
