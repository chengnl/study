运行多个任务并处理第一个结果

在并发编程中的一个常见的问题就是，当有多种并发任务解决一个问题时，你只对这些任务的第一个结果感兴趣。
比如，你想要排序一个数组。你有多种排序算法。 你可以全部启用它们，并且获取第一个结果（对于给定数组排序最快的算法的结果）。


ThreadPoolExecutor类中的invokeAny()方法接收任务数列，并启动它们，返回完成时没有抛出异常的第一个 任务的结果。
该方法返回的数据类型与启动任务的call()方法返回的类型一样。


这 个示例有两个返回随机Boolean值的UserValidator对象。每个UserValidator对象被一个实现TaskValidator类的Callable对象使用。
如果UserValidator类的validate()方法返回false，TaskValidator类将抛出异常。否则，它将返回true值。

示例有两个任务，可以返回true值或抛出异常。有以下4种情况：

    两个任务都返回ture。invokeAny()方法的结果是第一个完成任务的名称。
    第一个任务返回true，第二个任务抛出异常。invokeAny()方法的结果是第一个任务的名称。
    第一个任务抛出异常，第二个任务返回true。invokeAny()方法的结果是第二个任务的名称。
    两个任务都抛出异常。在本例中，invokeAny()方法抛出一个ExecutionException异常。

    ThreadPoolExecutor类提供其他版本的invokeAny()方法：

    invokeAny(Collection<? extends Callable<T>> tasks, long timeout,TimeUnit unit)：此方法执行所有任务，
    并返回第一个完成（未超时）且没有抛出异常的任务的结果。TimeUnit类是个枚举类，有如下常量：
    DAYS，HOURS，MICROSECONDS，MILLISECONDS， MINUTES,，NANOSECONDS 和SECONDS。
    