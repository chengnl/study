package thread.daemon;

import java.util.Date;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 上午11:17:14
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Event {
    private Date date;
    private String event;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
    
}
