package syntool.countdownlatch.waittodo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月19日 上午11:01:44
 *@version 1.0
 *@Description:参会人员
 */
public class Person implements Runnable{
    private String person;
    private Meet meet;
    public Person(Meet meet,int i){
    	this.person = "person"+i;
    	this.meet=meet;
    }
	@Override
	public void run() {
		Random  random = new Random();
		try {
			TimeUnit.SECONDS.sleep(random.nextInt(10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.meet.arrive(person);
	}
}
