package thread.uncheckexception;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 下午3:49:07
 *@version 1.0
 *@Description:非检查异常
 */
public class ExceptionHandler  implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		 System.out.printf("ExceptionHandler：An exception has been captured\n");
	      System.out.printf("Thread: %s\n",t.getId());
	      System.out.printf("Exception: %s: %s\n",e.getClass().getName(),e.getMessage());
	      System.out.printf("Stack Trace: \n");
	      e.printStackTrace(System.out); 
	      System.out.printf("Thread status: %s\n",t.getState());
	}
}
