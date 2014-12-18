创建一个线程执行者

使用Executor framework的第一步就是创建一个ThreadPoolExecutor类的对象。你可以使用这个类提供的4个构造器
或Executors工厂类来 创建ThreadPoolExecutor。一旦有执行者，你就可以提交Runnable或Callable对象给执行者来执行。


##
Server类是这个示例的关键。它创建和使用ThreadPoolExecutor执行任务。

第一个重要点是在Server类的构造器中创建ThreadPoolExecutor。ThreadPoolExecutor有4个不同的构造器，但由于它 们的复杂性，
Java并发API提供Executors类来构造执行者和其他相关对象。即使我们可以通过ThreadPoolExecutor类的任意一 个构造器来创建
ThreadPoolExecutor，但这里推荐使用Executors类。

在本例中，你已经使用 newCachedThreadPool()方法创建一个缓存线程池。这个方法返回ExecutorService对象，所以它被转换为
 ThreadPoolExecutor类型来访问它的所有方法。你已创建的缓存线程池，当需要执行新的任务会创建新的线程，如果它们已经完成
 运行任务，变成可用状态，会重新使用这些线程。线程重复利用的好处是，它减少线程创建的时间。缓存线程池的缺点是，为新任务
 不断创建线程， 所以如果你提交过多的任务给执行者，会使系统超载。

注意事项：使用通过newCachedThreadPool()方法创建的执行者，只有当你有一个合理的线程数或任务有一个很短的执行时间。

一旦你创建执行者，你可以使用execute()方法提交Runnable或Callable类型的任务。在本例中，你提交实现Runnable接口的Task类对象。

你也打印了一些关于执行者信息的日志信息。特别地，你可以使用了以下方法：

    getPoolSize()：此方法返回线程池实际的线程数。
    getActiveCount()：此方法返回在执行者中正在执行任务的线程数。
    getCompletedTaskCount()：此方法返回执行者完成的任务数。

ThreadPoolExecutor 类和一般执行者的一个关键方面是，你必须明确地结束它。如果你没有这么做，这个执行者会继续它的执行，
并且这个程序不会结束。如果执行者没有任务可执行， 它会继续等待新任务并且不会结束它的执行。一个Java应用程序将不会结束，
除非所有的非守护线程完成它们的执行。所以，如果你不结束这个执行者，你的应用程序将不会结束。

当执行者完成所有待处理的任务，你可以使用ThreadPoolExecutor类的shutdown()方法来表明你想要结束执行者。在你调用shutdown()
方法之后，如果你试图提交其他任务给执行者，它将会拒绝，并且抛出RejectedExecutionException异常。

##
ThreadPoolExecutor 类提供了许多获取它状态的方法，我们在这个示例中，使用getPoolSize()、getActiveCount()和 getCompletedTaskCount()
方法来获取执行者的池大小、线程数、完成任务数信息。你也可以使用 getLargestPoolSize()方法，返回池中某一时刻最大的线程数。

ThreadPoolExecutor类也提供其他与结束执行者相关的方法，这些方法是：

    shutdownNow()：此方法立即关闭执行者。它不会执行待处理的任务，但是它会返回待处理任务的列表。当你调用这个方法时，
    正在运行的任务继续它们的执行，但这个方法并不会等待它们的结束。
    isTerminated()：如果你已经调用shutdown()或shutdownNow()方法，并且执行者完成关闭它的处理时，此方法返回true。
    isShutdown()：如果你在执行者中调用shutdown()方法，此方法返回true。
    awaitTermination(long timeout, TimeUnit unit)：此方法阻塞调用线程，直到执行者的任务结束或超时。TimeUnit类是个枚举类，
    有如下常 量：DAYS，HOURS，MICROSECONDS， MILLISECONDS， MINUTES,，NANOSECONDS 和SECONDS。

注意事项：如果你想要等待任务的完成，不管它们的持续时间，请使用大的超时，如：DAYS。