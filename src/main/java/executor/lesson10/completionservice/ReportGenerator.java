package executor.lesson10.completionservice;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月5日 上午9:49:01
 *@version 1.0
 *@Description:报告生成
 */
public class ReportGenerator  implements Callable<String>{
    private final String sender;
    private final String title;
    public ReportGenerator(String sender,String title) {
		this.sender=sender;
		this.title=title;
	}
	@Override
	public String call() throws Exception {
		long duration = (long)new Random().nextInt(10);
		System.out.printf("%s_%s: reportGenerator: Generating a report during %d seconds \n",this.sender,this.title,duration);
		TimeUnit.SECONDS.sleep(duration);
		String ret = sender+ ": "+title;
		return ret;
	}

}
