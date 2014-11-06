package thread.uncheckexception;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 下午3:57:24
 *@version 1.0
 *@Description:TODO 类描述
 */
public class ExceptionTest  implements Runnable{

	@Override
	public void run() {
		 int number = Integer.parseInt("tttt");
	}
	
	public static void main(String[] args){
		ExceptionTest  test = new ExceptionTest();
		Thread  thread = new Thread(test);
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler());
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		MyThreadGroup  mtg = new MyThreadGroup("threadgroup");
		Thread  thread1 = new Thread(mtg,test);
		thread1.start();
	}

}
