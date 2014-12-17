package executor.lesson10.completionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月5日 上午10:37:31
 *@version 1.0
 *@Description:报告过程
 */
public class ReportProcessor implements Runnable {
	 private CompletionService<String> service;
	 private boolean end;
	 public ReportProcessor(CompletionService<String> service) {
		this.service=service;
		this.end=false;
	}
	@Override
	public void run() {
		while(!end){
              try {
            	  Future<String> result=this.service.poll(20, TimeUnit.SECONDS);
            	  if(result!=null){
            		  String report = result.get();
            		  System.out.printf("ReportReceiver: Report Received: %s \n",report);
            	  }
			  } catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			  }				
		}
		 System.out.printf("ReportSender: End \n");
	}
	public void setEnd(boolean end) {
		this.end = end;
	}

}
