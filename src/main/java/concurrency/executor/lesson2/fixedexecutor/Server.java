package concurrency.executor.lesson2.fixedexecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月1日 下午3:47:35
 *@version 1.0
 *@Description:服务
 */
public class Server {
    private ThreadPoolExecutor executor;
    public Server(){
    	executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }
    public void executeTask(Task task){
    	System.out.printf("Server: a new task has arrived .\n");		
    	executor.execute(task);
    	System.out.printf("Server: pool size  %d.\n",this.executor.getPoolSize());
    	System.out.printf("Server: active count  %d.\n",this.executor.getActiveCount());
    	System.out.printf("Server: task count: %d .\n",this.executor.getTaskCount());
    }
    public void endServer(){
    	this.executor.shutdown();
    }
}
