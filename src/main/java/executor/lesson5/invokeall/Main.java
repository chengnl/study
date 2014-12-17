package executor.lesson5.invokeall;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月3日 上午9:27:40
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		
		List<Task>   tasks = new ArrayList<Task>();
		for(int i=0;i<5;i++){
			Task  task = new Task("task"+i);
			tasks.add(task);
		}
		List<Future<Result>> results = null; 
		ThreadPoolExecutor executor=(ThreadPoolExecutor) Executors.newCachedThreadPool();
		try {
			results=executor.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		System.out.printf("Main: results : \n");
		
		for(Future<Result> result:results){
			try {
				Result re =	result.get();
				System.out.printf("Result  : name=%s , value=%d \n",re.getName(),re.getValue());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
