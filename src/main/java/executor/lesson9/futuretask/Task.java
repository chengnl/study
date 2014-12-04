package executor.lesson9.futuretask;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月4日 下午2:21:33
 *@version 1.0
 *@Description:任务
 */
public class Task implements Callable<String>{
    private final String name;
    public Task(String name){
    	this.name=name;
    }
	@Override
	public String call() throws Exception {
		System.out.printf("%s : task start \n",this.name);
		long duration = new Random().nextInt(10);
		System.out.printf("%s : task duration %d \n",this.name,duration);
		TimeUnit.SECONDS.sleep(duration);
		System.out.printf("%s : task end \n",this.name);
		return "hello word!";
	}
	public String getName() {
		return name;
	}

}
