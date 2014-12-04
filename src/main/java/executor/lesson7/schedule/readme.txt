执行者周期性地运行一个任务

执行者框架提供ThreadPoolExecutor类，使用池中的线程执行并发任务，从而避免所有线程的创建操作。当你提交任务给执行者，根据它的配置，
它尽快地执行任务。当它结束，任务将被执行者删除，如果你想再次运行任务，你必须再次提交任务给执行者。

但是执行者框架通过ScheduledThreadPoolExecutor类可以执行周期性任务。

当你想要使用执行者框架执行一个周期性任务，你需要ScheduledExecutorService对象。Java建议使用 Executors类创建执行者，Executors类是
一个执行者对象工厂。在本例中，你应该使用newScheduledThreadPool()方法，创建一个 ScheduledExecutorService对象。这个方法接收池的
线程数作为参数。正如在本例中你只有一个任务，你传入了值1作为参数。

一旦你有执行者需要执行一个周期性任务，你提交任务给该执行者。你已经使用了scheduledAtFixedRate()方法。此方法接收4个参数：
你想要周期性执行的任务、第一次执行任务的延迟时间、两次执行之间的间隔期间、第2、3个参数的时间单位。它是TimeUnit类的常 量，
TimeUnit类是个枚举类，有如下常量：DAYS，HOURS，MICROSECONDS， MILLISECONDS， MINUTES,，NANOSECONDS 和SECONDS。

很重要的一点需要考虑的是两次执行之间的（间隔）期间，是这两个执行开始之间的一段时间。如果你有一个花5秒执行的周期性任务，
而你给一段3秒时间，同一时刻，不会有两个任务并发执行,后续的任务等上个任务完成后,立即执行下个任务。

scheduleAtFixedRate() 方法返回ScheduledFuture对象，它继承Future接口，这个方法和调度任务一起协同工作。ScheduledFuture是一个参数化
接口（校对注：ScheduledFuture<V>）。在这个示例中，由于你的任务是非参数化的Runnable对象，你必须使用 问号作为参数。

你已经使用ScheduledFuture接口的一个方法。getDelay()方法返回直到任务的下次执行时间。这个方法接收一个TimeUnit常量，这是你想要接收
结果的时间单位。

cheduledThreadPoolExecutor 提供其他方法来调度周期性任务。这就是scheduleWithFixedDelay()方法。它与scheduledAtFixedRate()方法有一 
样的参数，但它们之间的差异值得注意。在scheduledAtFixedRate()方法中，第3个参数决定两个执行开始的一段时间。
在 scheduleWithFixedDelay()方法中，参数决定任务执行结束与下次执行开始之间的一段时间。

当你使用 shutdown()方法时，你也可以通过参数配置一个SeduledThreadPoolExecutor的行为。shutdown()方法默认的行为是，当你调用这个
方法时，计划任务就结束。 你可以使用ScheduledThreadPoolExecutor类的 setContinueExistingPeriodicTasksAfterShutdownPolicy()方法设置
true值改变这个行为。在调用 shutdown()方法时，周期性任务将不会结束。