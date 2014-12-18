package concurrency.syn.lock;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月12日 上午10:00:15
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Job implements Runnable{
    private PrintQueue printQueue;
    public Job(PrintQueue printQueue){
    	this.printQueue=printQueue;
    }
	@Override
	public void run() {
		System.out.printf("%s : going to print a documet\n",Thread.currentThread().getName());
		this.printQueue.printJob(new Object());
		System.out.printf("%s : the documet has been printed\n",Thread.currentThread().getName());
		
	}

}
