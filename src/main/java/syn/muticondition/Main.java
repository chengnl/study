package syn.muticondition;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 上午10:54:15
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Main {

	public static void main(String[] args) {
		FileMock  mock = new FileMock(100,10);
		Buffer  buffer = new Buffer(20);
		
		Producer   producer = new Producer(mock,buffer);
		Thread  thread1 = new Thread(producer,"producer");
		
		
		Consumer   consumers[]  = new Consumer[3];
		Thread  thread2[] = new Thread[3];
		for (int i = 0; i < thread2.length; i++) {
			consumers[i]=new Consumer(buffer);
			thread2[i] = new Thread(consumers[i],"consumer "+ i);
		}
		
		thread1.start();
		
		for (int i = 0; i < thread2.length; i++) {
			thread2[i].start();
		}
		
	}

}
