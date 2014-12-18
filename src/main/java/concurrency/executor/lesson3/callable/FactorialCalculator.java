package concurrency.executor.lesson3.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月2日 上午9:43:55
 *@version 1.0
 *@Description:计算结果
 */
public class FactorialCalculator implements Callable<Integer> {
     private Integer number;
     public FactorialCalculator(Integer number){
    	 this.number=number;
     }
	@Override
	public Integer call() throws Exception {
		int result =1;
		if(this.number==0||this.number==1)
			result= 1;
		else{
			for(int i=2;i<this.number;i++){
				result *=i;
				TimeUnit.SECONDS.sleep(20);
			}
		}
		System.out.printf("%s:%d \n",Thread.currentThread().getName(),result);
		return result;
	}

}
