package concurrency.customized.lesson1.threadpoolexecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@date 2015年1月13日 下午7:51:25
 *@version 1.0
 *@Description:测试
 */
public class Main {
    public static void main(String[] args){
    	MyExecutor   executor  =  new MyExecutor(4,10,3,TimeUnit.SECONDS,new 
    			LinkedBlockingDeque<Runnable>());
    	List<Future<String>> results=new ArrayList<>();
       for(int i=0;i<10;i++){
    	   SleepSecondTask  task  = new SleepSecondTask();
    	   results.add(executor.submit(task));
       }    	
       for(int i=0;i<5;i++){
    	   String result;
			try {
				result = results.get(i).get();
				 System.out.printf("task %d  result: %s \n.",i,result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
       }
       
       executor.shutdown();
       
       for(int i=5;i<10;i++){
	    	 String result;
			try {
				result = results.get(i).get();
				System.out.printf("task %d  result: %s  \n.",i,result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
       }
       
       try {
    	      executor.awaitTermination(1, TimeUnit.DAYS);
	   } catch (InterruptedException e) {
	   e.printStackTrace();
	   }
	}
}
