package exercise.concurrency.one.way1;

import java.util.concurrent.atomic.AtomicLong;

/**
 *@author chengnl
 *@date 2015年2月10日 下午8:48:43
 *@version 1.0
 *@Description:访问计数
 */
public class VisitCount {
    private  long time=0;
    private  AtomicLong count= new AtomicLong(0);
    
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public AtomicLong getCount() {
		return count;
	}
}
