package concurrency.customized.lesson3.threadfactory;

import java.util.concurrent.ThreadFactory;

/**
 *@author chengnl
 *@date 2015年1月21日 下午5:32:57
 *@version 1.0
 *@Description:自定义线程工厂
 */
public class MyThreadFactory implements ThreadFactory{
	private String pre;
	private int count;
	public MyThreadFactory(String pre) {
		this.pre=pre;
		count=1;
	}
	@Override
	public Thread newThread(Runnable r) {
		MyThead  thread  = new  MyThead(r,pre+"-"+count);
		count++;
		return thread;
	}

}
