执行者控制被拒绝的任务
===

##简介
当你想要结束执行者的执行，你使用shutdown()方法来表明它的结束。执行者等待正在运行或等待它的执行的任务的结束，然后结束它们的执行。

如果你在shutdown()方法和执行者结束之间，提交任务给执行者，这个任务将被拒绝，因为执行者不再接收新的任务。ThreadPoolExecutor类提
供一种机制，在调用shutdown()后，不接受新的任务。

为了管理执行者控制拒绝任务，你应该实现RejectedExecutionHandler接口。该接口有带有两个参数的方法rejectedExecution()：

    Runnable对象，存储被拒绝的任务
    Executor对象，存储拒绝任务的执行者

每个被执行者拒绝的任务都会调用这个方法。你必须使用Executor类的setRejectedExecutionHandler()方法设置拒绝任务的处理器。

##其他
当执行者接收任务时，会检查shutdown()是否已经被调用了。如果被调用了，它拒绝这个任务。首先，它查找 setRejectedExecutionHandler()设置的处理器。如果有一个（处理器），它调用那个类的 rejectedExecution()方法，否则，它将抛RejectedExecutionExeption异常。这是一个运行时异常，所以你不需要 用catch语句来控制它。


