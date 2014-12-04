执行者延迟运行一个任务

执行者框架提供ThreadPoolExecutor类，使用池中的线程来执行Callable和Runnable任务，这样可以避免所有线程的创建操作。
当你提交一个任务给执行者，会根据执行者的配置尽快执行它。在有些使用情况下，当你对尽快执行任务不感觉兴趣。你可能想
要在一段时间之后执行任务或周期性地执行任务。基于这些目的，执行者框架提供 ScheduledThreadPoolExecutor类。

使用schedule()方法，让执行者在一段时间后执行任务。这个方法接收3个参数，如下：

    你想要执行的任务
    你想要让任务在执行前等待多长时间
    时间单位，指定为TimeUnit类的常数

    
 ScheduledThreadPoolExecutor类的schedule()方法接收这两种类型（Runnable和Callable）的任务。

尽管ScheduledThreadPoolExecutor类是ThreadPoolExecutor类的子类，因此，它继承 ThreadPoolExecutor类的所有功能，
但Java建议使用ScheduledThreadPoolExecutor仅适用于调度任务。

最后，你可以配置ScheduledThreadPoolExecutor的行为，当你调用shutdown()方法时，并且有待处理的任务正在等待它们延迟结束。
默认的行为是，不管执行者是否结束这些任务都将被执行。你可以使用ScheduledThreadPoolExecutor类的setExecuteExistingDelayedTasksAfterShutdownPolicy()
方法来改变这种行为。使用false，调用 shutdown()时，待处理的任务不会被执行。