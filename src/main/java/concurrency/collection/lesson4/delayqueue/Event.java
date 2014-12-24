package concurrency.collection.lesson4.delayqueue;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月24日 上午10:35:21
 *@version 1.0
 *@Description:事件
 */
public class Event  implements Delayed{
     private Date delay;
     public Event(Date delay) {
		this.delay=delay;
	}
	@Override
	public int compareTo(Delayed o) {
		long result= this.getDelay(TimeUnit.NANOSECONDS);
		long otherResult= o.getDelay(TimeUnit.NANOSECONDS);
		if(result-otherResult>0)
			return 1;
		else if(result-otherResult<0)
			return -1;
		else
			return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		Date now  = new Date();
		long sourceDuration= this.delay.getTime()-now.getTime();
		return unit.convert(sourceDuration, TimeUnit.MILLISECONDS);
	}

}
