package thread.uncheckexception;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 下午5:51:42
 *@version 1.0
 *@Description:TODO 类描述
 */
public class MyThreadGroup extends ThreadGroup{

	public MyThreadGroup(String name) {
		super(name);
	}
	@Override
	public void uncaughtException(Thread t, Throwable e) {
	 System.out.printf("MyThreadGroup：An exception has been captured\n");
      System.out.printf("Thread: %s\n",t.getId());
      System.out.printf("Exception: %s: %s\n",e.getClass().getName(),e.getMessage());
      System.out.printf("Stack Trace: \n");
      e.printStackTrace(System.out); 
      System.out.printf("Thread status: %s\n",t.getState());
	  //interrupt();
	}
}
