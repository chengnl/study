package concurrency.forkjoin.lesson4.throwexception;

import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author chengnl
 * @E-mail:chengnengliang@vrvmail.com.cn
 * @date 2014年12月15日 下午5:06:06
 * @version 1.0
 * @Description:任务
 */
public class Task extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 1L;
	private int array[];
	private int first;
	private int last;

	public Task(int array[], int first, int last) {
		this.array = array;
		this.first = first;
		this.last = last;
	}

	@Override
	protected Integer compute() {
		System.out.printf("Task: Start from %d to %d\n", first, last);
		if (this.last - this.first < 10) {
			if ((3 > first) && (3 < last)) {
//				throw new RuntimeException("This task throws an"
//						+ "Exception: Task from " + first + " to " + last);
				
				Exception e=new Exception("This task throws an Exception: "+ "Task from "+first+" to "+last);
						completeExceptionally(e);
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			int mid = (last + first) / 2;
			Task task1 = new Task(array, first, mid);
			Task task2 = new Task(array, mid, last);
			invokeAll(task1, task2);
		}
		System.out.printf("Task: End form %d to %d\n", first, last);
		return 0;
	}
}
