package concurrency.customized.lesson1.threadpoolexecutor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@date 2015年1月13日 下午7:49:57
 *@version 1.0
 *@Description:休息两秒任务
 */
public class SleepSecondTask  implements Callable<String>{

	@Override
	public String call() throws Exception {
		TimeUnit.SECONDS.sleep(2);
		return new Date().toLocaleString();
	}

}
