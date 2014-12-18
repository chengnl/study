package concurrency.syntool.cyclicbarrier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import concurrency.syntool.cyclicbarrier.LogMock.LogFile;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月20日 下午2:24:48
 *@version 1.0
 *@Description:登录统计
 */
public class LoginStatistics  implements Runnable {
        private CyclicBarrier  barrier;
        private  LogFile file;
        private  Result result;
        
        public LoginStatistics(CyclicBarrier  barrier,LogFile file,Result result){
        	this.barrier=barrier;
        	this.file=file;
        	this.result=result;
       }

		@Override
		public void run() {
              System.out.println(Thread.currentThread().getName()+" Statistics  start ...........");
              result.setFileName(file.getFileName());
              Map<String,Integer>  map = new HashMap<String,Integer>();
              for(String item:file.getFileContent()){
            	  if(map.containsKey(item))
            		    map.put(item, map.get(item)+1);
            	  else
            		  map.put(item, 1);
              }
              System.out.println(Thread.currentThread().getName()+" result"+map.toString());
              result.setResult( map);
              System.out.println(Thread.currentThread().getName()+" Statistics  end");
              try {
				barrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
			   }
		}
       
}
