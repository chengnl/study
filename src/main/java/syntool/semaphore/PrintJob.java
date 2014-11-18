package syntool.semaphore;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月18日 下午6:14:32
 *@version 1.0
 *@Description:TODO 类描述
 */
public class PrintJob implements Runnable{
	private PrintQueue printQueue;
    public PrintJob(PrintQueue printQueue){
    	this.printQueue=printQueue;
    }
	@Override
	public void run() {
		this.printQueue.print("test msg");
	}
       
}
