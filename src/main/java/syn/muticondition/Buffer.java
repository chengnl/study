package syn.muticondition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月13日 下午4:37:02
 *@version 1.0
 *@Description:缓存
 */
public class Buffer {
      private LinkedList<String> buffer;
      private int maxSize;
      private ReentrantLock lock;
      private Condition lines;
      private Condition space;
      public Buffer(int maxSize){
    	  this.maxSize=maxSize;
    	  this.buffer=new LinkedList<String>();
    	  this.lock= new ReentrantLock();
    	  this.lines=this.lock.newCondition();
    	  this.space=this.lock.newCondition();
      }
      
      public void insert(String line){
    	  this.lock.lock();
    	  try{
    		  while(buffer.size()==this.maxSize){
    			  space.await();
    		  }
    		  buffer.offer(line);
    		  System.out.printf("%s : inserted line: %d\n",Thread.currentThread().getName(),
    				  this.buffer.size());
    		  this.lines.signalAll();
    	  }catch(InterruptedException e){
    		  e.printStackTrace();
    	  }finally{
    		  this.lock.unlock();
    	  }
      }
      public String get(){
    	  String line=null;
    	  this.lock.lock();
    	  try{
    		  while((buffer.size()==0))
    		     this.lines.await();
    		   if(buffer.size()>0){
    			   line=this.buffer.poll();
    			   System.out.printf("%s : Line : %d\n",Thread.currentThread().getName(),
    	    				  this.buffer.size());
    			   this.space.signalAll();
    		   }  
    	  }catch(InterruptedException e){
    		  e.printStackTrace();
    	  }finally{
    		  this.lock.unlock();
    	  }
    	  return line;
      }
}
