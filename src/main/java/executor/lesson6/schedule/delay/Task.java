package executor.lesson6.schedule.delay;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月4日 上午10:07:02
 *@version 1.0
 *@Description:任务
 */
public class Task implements Runnable{
    private String name;
    public Task(String name){
    	this.name=name;
    }
	@Override
	public void run() {
		System.out.println("Task: "+this.name);
	}
}
