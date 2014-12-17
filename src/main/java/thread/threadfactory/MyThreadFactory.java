package thread.threadfactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 下午6:15:37
 *@version 1.0
 *@Description:线程工厂
 */
public class MyThreadFactory implements  ThreadFactory{
    private String name;
    private int counter;
    private List<String> stats;
    public MyThreadFactory(String name){
    	counter=0;
    	this.name=name;
    	stats =new ArrayList<String>();
    }
	@Override
	public Thread newThread(Runnable r) {
		Thread   thread = new Thread(r,name+"-Thread_"+counter);
		counter++;
		stats.add(String.format("created thread %d with name %s on %s \n", thread.getId(),thread.getName(),new Date()));
		return thread;
	}
    public  String getStats(){
    	StringBuffer buffer = new StringBuffer();
    	Iterator<String> it =stats.iterator();
    	while(it.hasNext()){
    		buffer.append(it.next());
    		buffer.append("\n");
    	}
    	return buffer.toString();
    }
}
