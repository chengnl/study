在同一个点同步任务

Java 并发 API 提供了可以允许2个或多个线程在在一个确定点的同步应用。它是 CyclicBarrier 类。但是它有一些特殊性让它成为更强大的类。

CyclicBarrier 类有一个整数初始值，此值表示将在同一点同步的线程数量。当其中一个线程到达确定点，它会调用await() 方法来等待
其他线程。当线程调用这个方法，CyclicBarrier阻塞线程进入休眠直到其他线程到达。当最后一个线程调用CyclicBarrier 类的await() 
方法，它唤醒所有等待的线程并继续执行它们的任务。

CyclicBarrier 类有个有趣的优势是，你可以传递一个外加的 Runnable 对象作为初始参数，并且当全部线程都到达同一个点时，
CyclicBarrier类 会把这个对象当做线程来执行。此特点让这个类在使用 divide 和 conquer 编程技术时，可以充分发挥任务的并行性，

CyclicBarrier 类有另一个版本的 await() 方法:

    await(long time, TimeUnit unit): 线程会一直休眠直到被中断；内部计数器到达0，或者特定的时间过去了。TimeUnit类有多种常量： 
    DAYS, HOURS, MICROSECONDS, MILLISECONDS, MINUTES, NANOSECONDS, and SECONDS.

此类也提供了 getNumberWaiting() 方法，返回被 await() 方法阻塞的线程数，还有 getParties() 方法，返回将与CyclicBarrier同步的任务数。

重置 CyclicBarrier 对象
CyclicBarrier 类与CountDownLatch有一些共同点，但是也有一些不同。最主要的不同是，CyclicBarrier对象可以重置到它的初始状态，
重新分配新的值给内部计数器，即使它已经被初始过了。

可以使用 CyclicBarrier的reset() 方法来进行重置操作。当这个方法被调用后，全部的正在await() 方法里等待的线程接收到一个 
BrokenBarrierException 异常。此异常在例子中已经用打印stack trace处理了，但是在一个更复制的应用，它可以执行一些其
他操作，例如重新开始执行或者在中断点恢复操作。

破坏 CyclicBarrier 对象
CyclicBarrier 对象可能处于一个特殊的状态，称为 broken。当多个线程正在 await() 方法中等待时，其中一个被中断了，
此线程会收到 InterruptedException 异常，但是其他正在等待的线程将收到 BrokenBarrierException 异常，并且 CyclicBarrier 
会被置于broken 状态中。

CyclicBarrier 类提供了isBroken() 方法，如果对象在 broken 状态，返回true，否则返回false。