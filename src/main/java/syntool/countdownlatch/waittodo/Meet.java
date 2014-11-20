package syntool.countdownlatch.waittodo;

import java.util.concurrent.CountDownLatch;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月19日 上午10:57:15
 *@version 1.0
 *@Description:全部到达开始吃饭
 */
public class Meet implements Runnable{
    private  CountDownLatch  count;
    public Meet(int num){
    	count=new CountDownLatch(num);
    }
    public void arrive(String person){
    	System.out.println(person+" arrived");
    	count.countDown();
    }
	@Override
	public void run() {
		try {
			System.out.println(" meet waiting.......................");
			count.await();
			System.out.println(" meet start!!!!!!!!!!!!!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
