package concurrency.thread.threadfactory;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 下午6:46:44
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Main {

	public static void main(String[] args) {
		MyThreadFactory  mtf = new  MyThreadFactory("MyThreadFactory");
		Task  task = new Task();
        System.out.printf("starting the threads\n");
        for(int i=0;i<10;i++){
        	mtf.newThread(task).start();
        }
        System.out.printf("factory stats :\n");
        System.out.printf("%s \n" , mtf.getStats());
	}

}
