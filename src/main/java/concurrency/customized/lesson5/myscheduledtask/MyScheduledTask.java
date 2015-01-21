package concurrency.customized.lesson5.myscheduledtask;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chengnl
 * @date 2015年1月21日 下午7:15:57
 * @version 1.0
 * @Description:自定义计划任务
 */
// 1. 创建一个类，名为 MyScheduledTask，使名为 V 的泛型类型参数化。它扩展 FutureTask 类并实现
// RunnableScheduledFuture 接口。
public class MyScheduledTask<V> extends FutureTask<V> implements
		RunnableScheduledFuture<V> {

	// 2. 声明一个私有 RunnableScheduledFuture 属性，名为 task.
	private RunnableScheduledFuture<V> task;

	// 3. 声明一个私有 ScheduledThreadPoolExecutor，名为 executor.
	private ScheduledThreadPoolExecutor executor;

	// 4. 声明一个私有long属性，名为 period。
	private long period;

	// 5. 声明一个私有long属性，名为 startDate。
	private long startDate;

	// 6. 实现类的构造函数。它接收任务：将要运行的 Runnable 对象，任务要返回的 result，将被用来创建 MyScheduledTask
	// 对象的 RunnableScheduledFuture 任务，和要执行这个任务的 ScheduledThreadPoolExecutor 对象。
	// 调用它的父类的构造函数并储存任务和执行者属性。
	public MyScheduledTask(Runnable runnable, V result,
			RunnableScheduledFuture<V> task,
			ScheduledThreadPoolExecutor executor) {
		super(runnable, result);
		this.task = task;
		this.executor = executor;
	}

	// 7. 实现 getDelay() 方法。如果是周期性任务且 startDate 形象的值非0，计算并返回 startDate
	// 属性与当前日期的相差值。否则，返回储存在 task 属性的原先任务的延迟值。不要忘记你要返回结果时，要传递 time unit 作为参数哦。
	@Override
	public long getDelay(TimeUnit unit) {
		if (!isPeriodic()) {
			return task.getDelay(unit);
		} else {
			if (startDate == 0) {
				return task.getDelay(unit);
			} else {
				Date now = new Date();
				long delay = startDate - now.getTime();
				return unit.convert(delay, TimeUnit.MILLISECONDS);
			}
		}
	}

	// 8. 实现 compareTo() 方法。调用原先任务的 compareTo() 方法。
	@Override
	public int compareTo(Delayed o) {
		return task.compareTo(o);
	}

	// 9. 实现 isPeriodic() 方法。调用原来任务的 isPeriodic() 方法。
	@Override
	public boolean isPeriodic() {
		return task.isPeriodic();
	}

	// 10. 实现方法 run()。如果这是一个周期性任务，你要用下一个执行任务的开始日期更新它的 startDate
	// 属性。用当前日期和时间间隔的和计算它。 然后，把再次把任务添加到 ScheduledThreadPoolExecutor 对象的 queue中。
	@Override
	public void run() {
		if (isPeriodic() && (!executor.isShutdown())) {
			Date now = new Date();
			startDate = now.getTime() + period;
			executor.getQueue().add(this);
		}

		// 11.打印当前日期的信息到操控台，调用 runAndReset() 方法运行任务，然后再打印另一条关于当前日期的信息到操控台。
		System.out.printf("Pre-MyScheduledTask: %s\n", new Date());
		System.out.printf("MyScheduledTask: Is Periodic:%s\n", isPeriodic());
		super.runAndReset();
		System.out.printf("Post-MyScheduledTask: %s\n", new Date());
	}

	// 12. 实现 setPeriod() 方法，来确立任务的周期时间。
	public void setPeriod(long period) {
		this.period = period;
	}
}
