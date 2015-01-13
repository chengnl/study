定制ThreadPoolExecutor类
====

执行者框架（Executor framework）是一种机制，允许你将线程的创建与执行分离。它是基于Executor和ExecutorService接口及其实现这两个接口的ThreadPoolExecutor类。它有一个内部的线程池和提供允许你提交两种任务给线程池执行的方法。这些任务是：

    Runnable接口，实现没有返回结果的任务
    Callable接口，实现返回结果的任务

在这两种情况下，你只有提交任务给执行者。这个执行者使用线程池中的线程或创建一个新的线程来执行这些任务。执行者同样决定任务被执行的时刻。

在这个指南中，你将学习如何覆盖ThreadPoolExecutor类的一些方法，计算你在执行者中执行的任务的执行时间，并且将关于执行者完成它的执行的统计信息写入到控制台。


它是如何工作的…

在这个指南中，我们已经通过继承ThreadPoolExecutor类和覆盖它的4个方法来实现我们自己定制的执行者。我们用beforeExecute()和afterExecute()方法来计算任务的执行时间。beforeExecute()方法是在任务执行之前被执行的。在这种情况下，我们使用HashMap来存储任务的开始（执行）时间。afterExecute()方法是在任务执行之后被执行的。你可以从HashMap中获取已完成任务的startTime（开始执行时间），然后，计算实际时间和那个时间（startTime）的差异来获取任务的执行时间。你也覆盖了shutdown()和shutdownNow()方法，将关于在执行者中已执行的任务的统计信息写入到控制台：

    对于已执行的任务，使用getCompletedTaskCount()方法（获取）。
    对于正在运行的任务，使用getActiveCount()方法（获取）。

对于待处理任务，使用执行者存储待处理任务的阻塞队列的size()方法（获取）。SleepTwoSecondsTask类，实现Callable接口，令它的执行线程睡眠2秒。Main类，使用它向你的执行者提交10个任务和演示其他类的特性。

执行这个程序，你将看到这个程序如何显示正在运行的每个任务的时间跨度，和根据调用shutdown()方法统计执行者。