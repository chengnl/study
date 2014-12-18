package concurrency.executor.lesson8.cancel;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月4日 上午11:22:24
 *@version 1.0
 *@Description:任务
 */
public class Task implements Callable<String>{
    private String name;
    public Task(String name){
    	this.name=name;
    }
	@Override
	public String call() throws Exception {
		System.out.printf("%s: Starting at : %s\n",name,new Date());
		try {
			TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: end at : %s\n",name,new Date());
		return "hello,world";
	}

}
