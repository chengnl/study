package syntool.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月1日 下午2:56:48
 *@version 1.0
 *@Description:消费者
 */
public class Consumer implements Runnable{
    private List<String> buffer;
    private final Exchanger<List<String>> exchange;
    public Consumer(List<String> buffer,Exchanger<List<String>> exchange){
    	this.buffer=buffer;
    	this.exchange=exchange;
    }
	@Override
	public void run() {
		int cycle =1;
		for(int i=0;i<10;i++){
			System.out.printf("consumer: Cycle %d .\n",cycle);
			try {
				this.buffer=exchange.exchange(this.buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int j=0;j<10;j++){
				String message= buffer.get(0);
				System.out.printf("consumer: %s .\n",message);
				buffer.remove(0);
			}
			cycle++;
		}
	}

}
