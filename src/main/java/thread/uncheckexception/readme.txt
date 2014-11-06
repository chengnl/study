Java里有2种异常:

    检查异常（Checked exceptions）: 这些异常必须强制捕获它们或在一个方法里的throws子句中。 例如， IOException 或者ClassNotFoundException。
    未检查异常（Unchecked exceptions）: 这些异常不用强制捕获它们。例如， NumberFormatException。

在一个线程 对象的 run() 方法里抛出一个检查异常，我们必须捕获并处理他们。因为 run() 方法不接受 throws 子句。当一个非检查异常被抛出，默认的行为是在控制台写下stack trace并退出程序。

幸运的是, Java 提供我们一种机制可以捕获和处理线程对象抛出的未检测异常来避免程序终结。


The Thread 类有其他相关方法可以处理未捕获的异常。静态方法 setDefaultUncaughtExceptionHandler() 为应用里的所有线程对象建立异常 handler 。

当一个未捕捉的异常在线程里被抛出，JVM会寻找此异常的3种可能潜在的处理者（handler）。

首先, 它寻找这个未捕捉的线程对象的异常handle，如我们在在这个指南中学习的。如果这个handle 不存在，那么JVM会在线程对象的ThreadGroup里寻找非捕捉异常的handler，如在处理线程组内的不受控制异常里介绍的那样。如果此方法不存在，正如我们在这个指南中学习的，那么 JVM 会寻找默认非捕捉异常handle。

如果没有一个handler存在, 那么 JVM会把异常的 stack trace 写入操控台并结束任务。