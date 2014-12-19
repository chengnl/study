package concurrency.collection.lesson1.concurrentlinkeddeque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月18日 下午5:53:08
 *@version 1.0
 *@Description:获取任务
 */
public class PollTask implements Runnable{
    private ConcurrentLinkedDeque<String> list;
    private String name;
    public PollTask(ConcurrentLinkedDeque<String> list,String name) {
        this.list=list;
        this.name=name;
	}
	@Override
	public void run() {
		for(int i=0;i<5000;i++){
			this.list.pollFirst();
			this.list.pollLast();
		}
	}

}
