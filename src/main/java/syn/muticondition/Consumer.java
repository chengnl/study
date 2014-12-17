package syn.muticondition;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 上午10:51:38
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Consumer implements Runnable{
    private  Buffer  buffer;
    public  Consumer(Buffer  buffer){
    	this.buffer=buffer;
    }
	@Override
	public void run() {
           while(true){
        	   String line =buffer.get();
        	   try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
           }		
	}

}
