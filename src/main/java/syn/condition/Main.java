package syn.condition;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 上午10:54:15
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Main {

	public static void main(String[] args) {
		EventStorage   storage  = new EventStorage();
		Producer   producer = new Producer(storage);
		Thread  thread1 = new Thread(producer);
		Consumer   consumer = new Consumer(storage);
		Thread  thread2 = new Thread(consumer);
		
		thread2.start();
		thread1.start();
	}

}
