package thread.interrupted;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 上午9:58:23
 *@version 1.0
 *@Description:线程中断
 */
public class ThreadInterrupted  extends Thread{

	@Override
	public void run() {
		int i=0;
		while(true){
			i++;
			System.out.println(i);
			if(this.isInterrupted()){
				System.out.println("thread isInterrupted");
				break;
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		ThreadInterrupted  thread = new ThreadInterrupted();
		thread.start();
		
		Thread.sleep(1000);
		thread.interrupt();
	}
}
