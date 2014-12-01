package syntool.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月1日 下午2:44:45
 *@version 1.0
 *@Description:生产者
 */
public class Producer implements Runnable{
    private List<String> buffer;
    private final Exchanger<List<String>> exchange;
    public Producer(List<String> buffer,Exchanger<List<String>> exchange){
    	this.buffer=buffer;
    	this.exchange=exchange;
    }
	@Override
	public void run() {
		int cycle=1;
		for(int i=0;i<10;i++){
			System.out.printf("producer: Cycle %d .\n",cycle);
			for(int j=0;j<10;j++){
				String message="event "+((i*10)+j);
				System.out.printf("producer: %s .\n",message);
				this.buffer.add(message);
			}
			try {
				this.buffer=exchange.exchange(this.buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("producer: buffer size %d .\n",this.buffer.size());
			cycle++;
		}
	}
        
}
