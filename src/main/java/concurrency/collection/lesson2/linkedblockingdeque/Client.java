package concurrency.collection.lesson2.linkedblockingdeque;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月19日 下午4:25:00
 *@version 1.0
 *@Description:客户端
 */
public class Client implements  Runnable{
	 private LinkedBlockingDeque<String> list;
	 public Client(LinkedBlockingDeque<String> list) {
	     this.list=list;	
	}
	@Override
	public void run() {
		for(int i=0;i<3;i++){
			for(int j=0;j<5;j++){
                try {
					this.list.put(i+":"+j);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                System.out.printf("add element to list : %s \n",i+":"+j);
                System.out.printf("Client: at %s.\n",new Date());
                try {
                	TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                	e.printStackTrace();
                }
			}
		}
	}
}
