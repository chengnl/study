package concurrency.syntool.cyclicbarrier;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月20日 下午3:21:11
 *@version 1.0
 *@Description:汇总
 */
public class Sum implements Runnable{
	    private Result[] results;
        public Sum(Result[] results){
        	this.results=results;
        }
		@Override
		public void run() {
			 Map<String,Integer>  map = new HashMap<String,Integer>();
			for(int i=0;i<results.length;i++){
				  Result  result = results[i];
	              for(Iterator<Entry<String, Integer>>  set=result.getResult().entrySet().iterator();set.hasNext();){
	            	  Entry<String, Integer>  entry = set.next();
	            	  if(map.containsKey(entry.getKey()))
	            		    map.put(entry.getKey(), map.get(entry.getKey())+entry.getValue());
	            	  else
	            		  map.put(entry.getKey(), entry.getValue());
	              }
			}
			 for(Iterator<Entry<String, Integer>>  set=map.entrySet().iterator();set.hasNext();){
				 Entry<String, Integer>  entry = set.next();
				 System.out.println(entry.getKey()+"   login num ="+entry.getValue());
			 }
		}
}
