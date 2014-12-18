package concurrency.executor.lesson11.rejectedhandler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月5日 下午4:30:12
 *@version 1.0
 *@Description:拒绝任务控制
 */
public class RejectedTaskController implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.printf("RejectedTaskController: the task %s has been rejected \n",r.toString());
		System.out.printf("RejectedTaskController: %s\n",executor.toString());
		System.out.printf("RejectedTaskController: Terminating: %s\n",executor.isTerminating());
		System.out.printf("RejectedTaskController: Terminated: %s\n",executor.isTerminated());
	}

}
