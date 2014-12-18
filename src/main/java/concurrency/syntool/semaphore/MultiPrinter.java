package concurrency.syntool.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月18日 下午6:10:33
 *@version 1.0
 *@Description:控制多资源
 */
public class MultiPrinter implements PrintQueue{
        private  Semaphore  semaphore = new Semaphore(3);
        private   Lock  lock= new ReentrantLock();
        private   Boolean  frees[] = new Boolean[3];
        public MultiPrinter(){
        	 for(int i=0;i<3;i++){
        		 frees[i]=true;
        	 }
        }
        public void print(String msg){
        	try {
				semaphore.acquire();
				int print = getPrint();
				System.out.println(Thread.currentThread().getName()+":print "+print+" msg ="+msg);
				TimeUnit.SECONDS.sleep(1);
				frees[print]=true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				semaphore.release();
			}
        }
        
      private int  getPrint(){
    	  int ret=-1;
    	  try{
	    	  lock.lock();
	    	  for(int i=0;i<3;i++){
	    		  if(frees[i]){
	    			  frees[i]=false;
	    			  ret = i;
	    			  break;
	    		  }
	    	  }
    	  }catch(Exception e){
    		  e.printStackTrace();
    	  }finally{
    		  lock.unlock();
    	  }
    	  return ret;
      }
}
