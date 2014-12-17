package thread.daemon;

import java.util.Date;
import java.util.Deque;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 上午11:19:51
 *@version 1.0
 *@Description:TODO 类描述
 */
public class CleanerTask extends Thread{
    private Deque<Event> deque;
    public CleanerTask(Deque<Event> deque){
    	this.deque=deque;
    	this.setDaemon(true);
    }
    @Override
    public void run(){
    	while(true){
    		Date date = new Date();
    		clean(date);
    	}
    }
    
    private void clean(Date date){
		long difference;
		boolean delete;
		if(deque.size()==0)
			return;
		//System.out.printf("size of the queue1  %s\n", deque.size());
		delete=false;
		do{
			Event e =deque.getLast();
			difference = date.getTime()-e.getDate().getTime();
			if(difference>10000){
				System.out.printf("cleaner: %s\n", e.getEvent());
				deque.removeLast();
				delete=true;
			}
		}while(difference>10000);
		if(delete)
			System.out.printf("cleaner: size of the queue :%s\n", deque.size());
    }
}
