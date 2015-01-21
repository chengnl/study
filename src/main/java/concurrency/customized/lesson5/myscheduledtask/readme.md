自定义在计划的线程池内运行的任务
==

计划的线程池是 Executor 框架的基本线程池的扩展，允许你定制一个计划来执行一段时间后需要被执行的任务。 它通过 ScheduledThreadPoolExecutor 类来实现，并允许运行以下这两种任务：

    Delayed 任务：这种任务在一段时间后仅执行一次。
    Periodic 任务：这种任务在延迟后执行，然后通常周期性运行

Delayed 任务可以执行 Callable 和 Runnable 对象，但是 periodic任务只能执行 Runnable 对象。全部任务通过计划池执行的都必须实现 RunnableScheduledFuture 接口。在这个指南，你将学习如何实现你自己的 RunnableScheduledFuture 接口来执行延迟和周期性任务。

##它是怎么工作的…

在这个指南，你实现了 MyScheduledTask 类实现在 ScheduledThreadPoolExecutor 执行者中执行的自定义任务。这个类扩展 FutureTask 类并实现了 RunnableScheduledFuture 接口。它实现 RunnableScheduledFuture 接口， 因为在计划的执行者中执行的全部任务都一定要实现 这个接口，并扩展了 FutureTask 类，因为这个类提供了能有效的实现在 RunnableScheduledFuture 接口声明的方法。 之前提到的全部接口和类都被参数化成任务要返回的数据类型。

为了在计划的执行者中使用 MyScheduledTask 任务，要重写在 MyScheduledThreadPoolExecutor 类的 decorateTask() 方法。这个类扩展 ScheduledThreadPoolExecutor 执行者和它的方法提供一个把 ScheduledThreadPoolExecutor 执行者默认的计划任务转换成 MyScheduledTask 任务来实现的机制。所以，当你实现你的版本的计划任务时，你必须实现你的版本的计划的执行者。

decorateTask() 方法只是简单的创建了新的带有参数的 MyScheduledTask 对象：将要在任务中执行的 Runnable 对象; 将被任务返回结果对象，在这个例子，任务将不会返回结果，所以你要使用null值；原来执行 Runnable 对象的任务，新的对象将在池中代替这个任务；和
将执行任务的执行者，在这个例子，你使用 this 关键词指向创建这个任务的执行者。

The MyScheduledTask 类可以执行延迟和周期性任务。你已经实现了有全部必须的算法可以执行这2种任务的方法。他们是 getDelay() 和 run() 方法。

    The getDelay() 方法被计划的执行者调用来确认它是否需要运行任务。此方法对延迟任务和周期任务的响应是不同的。在之前提到的， MyScheduledClass 类的构造函数接收 原先的将要执行 Runnable 对象的 ScheduledRunnableFuture 对象， 并储存它作为类的属性来获取它的方法和它的数据。当我们要运行延迟任务时，getDelay() 方法返回原先任务的延迟，但是在周期任务的例子中，getDelay() 方法返回 startDate 属性值与当前时间的相差值。
    run() 方法是用来执行任务的。周期性任务的一个特别之处是你必须把下一次任务的执行作为一个新的任务放入到执行者的queue中，如果你要再次运行任务的话。所以，如果你执行周期性任务，你确定 startDate 属性值通过把当前时间和任务的执行周期相加，然后把任务储存在执行者的queue中。startDate 属性储存下一次任务将开始运行的时间。然后，使用 FutureTask 类提供的 runAndReset() 方法来运行任务。 在这个例子的延迟任务由于他们仅仅执行一次，就不用把他们放入执行者的queue中了。

你必须要注意如果执行者已经关闭。在这个例子，你不不需要再次把周期性任务储存进执行者的queue。

最后，你重写了在 MyScheduledThreadPoolExecutor 类的 scheduleAtFixedRate() 方法。我们之前提到的，对于周期任务，你要使用任务的周期来确定 startDate 属性值，但是你还没有初始这个周期呢。你必须重写此方法接收周期作为参数，然后传递给 MyScheduledTask 类这样它才能使用。



ScheduledThreadPoolExecutor 类提供了另一个版本的 decorateTask() 方法，它接收 Callable 对象作为参数来代替 Runnable 对象。