运行多个任务并处理所有结果

执行者框架允许你在不用担心线程创建和执行的情况下，并发的执行任务。它还提供了Future类，这个类可以用来控制任务的状态,
也可以用来获得执行者执行任务的结果。

如果你想要等待一个任务完成，你可以使用以下两种方法：

    如果任务执行完成，Future接口的isDone()方法将返回true。
    ThreadPoolExecutor类的awaitTermination()方法使线程进入睡眠，直到调用shutdown()方法之后每一个任务完成执行。

这两种方法都有一些缺点。第一个方法，你只能控制一个任务的完成。第二个方法，你必须等待一个线程来关闭执行者，
否则这个方法的调用立即返回。

ThreadPoolExecutor类提供invokeAll()，允许你提交任务列表给执行者，并且在这个列表上等待所有任务的完成。这个方法接收Callable
对象列表和返回 Future对象列表。这个列表将会有列表中每个任务的一个Future对象。Future对象列表的第一个对象是Callable对象列表
控制的第一个任务，以此类推。第一点要考虑的是，在存储结果对象的列表中声明的Future接口参数化的数据类型必须与使用的Callable
对象的参数化相兼容。

另一个重要的一点就是关于invokeAll()方法，你会使用Future对象获取任务的结果。当所有的任务完成时，这个方法结束，如果你调用返回
的Future对象的isDone()方法，所有调用都将返回true值。

ExecutorService类提供其他版本的invokeAll()方法：

    invokeAll(Collection<? extends Callable<T>> tasks, long timeout,TimeUnit unit)：此方法执行所有任务，当它们全部完成且未超时，
    返回它们的执行结果。TimeUnit类是个枚举类，有如下常量：DAYS，HOURS，MICROSECONDS， MILLISECONDS， MINUTES,
    ，NANOSECONDS 和SECONDS。

    
   invokeAll方法返回所有结果,包括异常,如果某个任务发生异常,也是等待所有任务完成,然后返回,异常信息返回到future,Future对象列表的第
   一个对象是Callable对象列表控制的第一个任务，以此类推
    