package concurrency.customized.lesson1.threadpoolexecutor;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@date 2015年1月13日 下午7:32:13
 *@version 1.0
 *@Description:定制执行类
 */
public class MyExecutor extends ThreadPoolExecutor{
    private ConcurrentHashMap<String, Date> startTimes;
	public MyExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		startTimes= new ConcurrentHashMap<String, Date>();
	}
    @Override
	protected void beforeExecute(Thread t, Runnable r) {
    	Date  startTime= new Date();
    	System.out.printf("Task %s  start time:%s \n.",t.getName(),startTime);
    	startTimes.put(String.valueOf(r.hashCode()),startTime);
    }
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
    	Date  endTime= new Date();
    	Date  startTime=startTimes.remove(String.valueOf(r.hashCode()));
    	System.out.printf("Task %s  execute  time:%d \n.",String.valueOf(r.hashCode()),(endTime.getTime()
    			-startTime.getTime()));
    }
    @Override
	public void shutdown(){
    	System.out.printf("MyExecutor  completed  task count:%d \n.",this.getCompletedTaskCount());
    	System.out.printf("MyExecutor  active  task count:%d \n.",this.getActiveCount());
    	super.shutdown();
    }
    @Override
    public  List<Runnable> shutdownNow(){
    	System.out.printf("MyExecutor  completed  task count:%d \n.",this.getCompletedTaskCount());
    	System.out.printf("MyExecutor  active  task count:%d \n.",this.getActiveCount());
    	return super.shutdownNow();
    }
}
