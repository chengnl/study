package thread.join;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 上午10:43:44
 *@version 1.0
 *@Description:线程终结
 */
public class ThreadJoin {
    class  DataSourcesLoader implements Runnable{
		@Override
		public void run() {
			 System.out.printf("running DataSourcesLoader:%s\n",new Date());
			 try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 System.out.printf("DataSourcesLoader is finished\n");
		}
     }
    class  NetworkConnectionsLoader implements Runnable{
		@Override
		public void run() {
			 System.out.printf("running NetworkConnectionsLoader:%s\n",new Date());
			 try {
				TimeUnit.SECONDS.sleep(6);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 System.out.printf("NetworkConnectionsLoader is finished\n");
		}
     }
    
    public static void main(String[] args) throws InterruptedException{
    	ThreadJoin  tj = new ThreadJoin();
       	Thread thread1 = new Thread(tj.new DataSourcesLoader(),"DataSourcesLoaderThread");
    	Thread thread2 = new Thread(tj.new NetworkConnectionsLoader(),"DataSourcesLoaderThread");
       	
       	thread1.start();
       	thread2.start();
       	
       	thread1.join();
       	thread2.join();
       	
       	System.out.printf("configuration has been loaded %s\n",new Date());
       	
    }
}
