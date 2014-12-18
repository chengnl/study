package concurrency.forkjoin.lesson1.create;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月15日 下午5:06:06
 *@version 1.0
 *@Description:任务
 */
public class Task extends RecursiveAction{
	private static final long serialVersionUID = 1L;
	private   List<Product> list;
	private int first;
	private int last;
	private double increment;
	public Task(List<Product> list,int first, int last,double increment){
		this.list=list;
		this.first=first;
		this.last=last;
		this.increment=increment;
	}
	@Override
	protected void compute() {
		if(this.last-this.first<10){
			updatePrice(first, last, increment);
		}else{
			int middle=(last+first)/2;
			System.out.printf("Task: Pending tasks:	%s\n",getQueuedTaskCount());
			Task t1=new Task(list, first,middle+1, increment);
			Task t2=new Task(list, middle+1,last, increment);
			invokeAll(t1, t2);
		}
	}
	
	private void updatePrice(int first, int last,double increment){
		for(int j=first;j<last;j++){
			Product  p =list.get(j);
			p.setPrice(p.getPrice()*(1+increment));
		}
	}

}
