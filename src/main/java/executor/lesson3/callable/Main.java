package executor.lesson3.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月2日 上午10:39:09
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		ThreadPoolExecutor executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		List<Future<Integer>> results = new ArrayList<Future<Integer>>();
		for(int i=0;i<10;i++){
			Future<Integer>  future=	executor.submit(new FactorialCalculator(i));
			results.add(future);
		}
		do{
			System.out.printf("Main: number of completed tasks :%d \n",executor.getCompletedTaskCount());
			for(int i=0;i<results.size();i++){
				Future<Integer>  future  = results.get(i);
				System.out.printf("Main: task :%d:%s\n",i,future.isDone());
			}
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(executor.getCompletedTaskCount()<results.size());
		
		System.out.printf("Main: results \n");
		for(int i=0;i<results.size();i++){
			Future<Integer>  future  = results.get(i);
			try {
				System.out.printf("Main: task :%d:%d\n",i,future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
	}

}
