package exercise.concurrency.one.way1;

import java.util.Date;

/**
 *@author chengnl
 *@date 2015年2月10日 下午8:52:25
 *@version 1.0
 *@Description:模拟服务
 */
public class MockServer {
	private VisitCount visitCount;
	private int maxLimitVisit=100;
	
	public MockServer() {
		this.visitCount=new VisitCount();
	}
	
	public int visit(){
         int result=0;
         long time = new Date().getSeconds();
         if(time==this.visitCount.getTime()){
        	long count= this.visitCount.getCount().getAndIncrement();
        	if(count>this.maxLimitVisit-1)
        		result= 1;
         }else{
        	synchronized (visitCount) {
        		if(time!=this.visitCount.getTime()){
            	  this.visitCount.setTime(time);
            	  this.visitCount.getCount().set(0);
        		}
			}
         	long count= this.visitCount.getCount().getAndIncrement();
         	if(count>this.maxLimitVisit-1)
         		result= 1;
         }
         System.out.println("time="+time+",count="+this.visitCount.getCount()+",result="+result);
         return result;
	}
}
