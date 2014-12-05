package executor.lesson11.rejectedhandler;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月5日 下午4:35:03
 *@version 1.0
 *@Description:任务处理
 */
public class Task implements Runnable{
    private final String name;
    public Task(String name){
    	this.name=name;
    }
	@Override
	public void  run()   {
		System.out.printf("%s : task start \n",this.name);
		long duration = new Random().nextInt(10);
		System.out.printf("%s : task duration %d \n",this.name,duration);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s : task end \n",this.name);
	}
	public String getName() {
		return name;
	}

}
