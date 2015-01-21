package concurrency.customized.lesson2.priorityexecutor;

import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@date 2015年1月21日 下午3:18:00
 *@version 1.0
 *@Description:任务
 */
public class Task implements Runnable,Comparable<Task>{
    private String taskName;
    private int priority;
	public Task(String taskName,int priority) {
		this.taskName=taskName;
		this.priority=priority;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	public int compareTo(Task o) {
		if(this.priority<o.getPriority())
			return 1;
		else if(this.priority>o.getPriority())
			return -1;
		return 0;
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Task:%s  this  priority is:%d \n",this.taskName,this.priority);
	}

}
