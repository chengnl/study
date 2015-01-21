package concurrency.customized.lesson4.usethreadfactory;

import java.util.Date;

/**
 *@author chengnl
 *@date 2015年1月21日 下午5:26:54
 *@version 1.0
 *@Description:自定义线程
 */
public class MyThead  extends Thread{
	private Date createTime;
	private Date startTime;
	private Date endTime;
	public MyThead(Runnable r, String string) {
		super(r);
		createTime=new Date();
	}
	@Override
     public void run(){
		 startTime=new Date();
    	 super.run();
    	 endTime=new Date();
     }
	@Override
	public String  toString(){
		StringBuilder  sb = new StringBuilder();
		sb.append("this thread "+Thread.currentThread().getName()+""
				+ " execute time="+(endTime.getTime()-startTime.getTime())+"");
		return sb.toString();
	}
}
