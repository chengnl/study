package concurrency.collection.lesson3.priorityblockingqueue;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月23日 上午9:46:33
 *@version 1.0
 *@Description:事件
 */
public class Event  implements  Comparable<Event>{
     private int id;
     private int priority;
     public Event(int id,int priority) {
    	 this.id=id;
    	 this.priority=priority;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	public int compareTo(Event o) {
		if(this.priority>o.getPriority())
			return -1;
		else if(this.priority<o.getPriority())
			return 1;
		else
			return 0;
	}

}
