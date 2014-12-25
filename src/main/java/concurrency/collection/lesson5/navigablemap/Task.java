package concurrency.collection.lesson5.navigablemap;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月25日 上午9:47:36
 *@version 1.0
 *@Description:任务
 */
public class Task implements Runnable{
    private String id;
    private ConcurrentSkipListMap<String, Contact> map;
    public Task(String id,ConcurrentSkipListMap<String, Contact> map) {
		this.id=id;
		this.map=map;
	}
	@Override
	public void run() {
		for (int i=0; i<1000; i++) {
			Contact contact=new Contact(id, String.valueOf(i+1000));
			map.put(id+contact.getPhone(), contact);
		}
	}

}
