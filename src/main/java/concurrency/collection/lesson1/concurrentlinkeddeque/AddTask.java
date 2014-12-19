package concurrency.collection.lesson1.concurrentlinkeddeque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月18日 下午5:49:12
 *@version 1.0
 *@Description:添加任务
 */
public class AddTask implements Runnable{
    private ConcurrentLinkedDeque<String> list;
    private String name;
    public AddTask(ConcurrentLinkedDeque<String> list,String name) {
       this.list=list;
       this.name=name;
    }
	@Override
	public void run() {
		for(int i=0;i<10000;i++){
			this.list.add(this.name+":"+i);
		}
	}

}
