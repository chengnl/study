package executor.lesson5.invokeall;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月3日 上午9:21:01
 *@version 1.0
 *@Description:任务
 */
public class Task  implements Callable<Result>{
    private final String name;
    public Task(String name) {
		this.name=name;
	}
	@Override
	public Result call() throws Exception {
		long duration = new Random().nextInt(10);
		System.out.printf("%s:Task has doing  duration %d \n",this.name,duration);
		TimeUnit.SECONDS.sleep(duration);
		long value=new Random().nextInt(100);
		for(int i=1;i<10;i++){
			value*=i;
			if(this.name.equals("task1"))
			    throw new Exception("1");
		}
		Result  result = new Result();
		result.setName(name);
		result.setValue(value);
		System.out.printf("%s:Task has done\n",this.name);
		return result;
	}

}
