package concurrency.collection.lesson5.navigablemap;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月25日 下午3:38:10
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		ConcurrentSkipListMap<String, Contact> map = new ConcurrentSkipListMap<>();
		Thread  threads[]  = new Thread[25];
		int count=0;
		for(char i='A';i<'Z';i++){
			Task  task = new Task(String.valueOf(i),map);
			Thread  thread = new Thread(task);
			threads[count]=thread;
			count++;
		}
		for(int i=0;i<threads.length;i++){
			threads[i].start();
		}
		for(int i=0;i<threads.length;i++){
			threads[i].join();
		}
		System.out.printf("Main: Size of the map: %d\n",map.size());
		Map.Entry<String, Contact> element;
		Contact contact;
		element=map.firstEntry();
		contact=element.getValue();
		System.out.printf("Main: First Entry: %s: %s\n",contact.
		getName(),contact.getPhone());
		
		element=map.lastEntry();
		contact=element.getValue();
		System.out.printf("Main: Last Entry: %s: %s\n",contact.
		getName(),contact.getPhone());
		
		System.out.printf("Main: Submap from A1996 to B1002: \n");
		ConcurrentNavigableMap<String, Contact> submap=map.
		subMap("A1996", "B1002");
		do {
			element=submap.pollFirstEntry();
			if (element!=null) {
				contact=element.getValue();
				System.out.printf("%s: %s\n",contact.getName(),contact.
				getPhone());
			}
		} while (element!=null);
		
		System.out.printf("Main: Size of the map: %d\n",map.size());
	}
}
