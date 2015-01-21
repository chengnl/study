在一个Executor对象中使用我们的ThreadFactory
==

在前面的指南中，实现ThreadFactory接口生成自定义线程，我们引进了工厂模式和提供如何实现一个实现ThreadFactory接口的线程的工厂例子。

执行者框架（Executor framework）是一种机制，它允许你将线程的创建与执行分离。它是基于Executor、ExecutorService接口和实现这两个接口的ThreadPoolExecutor类。它有一个内部的线程池和提供一些方法，这些方法允许你提交两种任务给线程池执行。这两种任务是：

    实现Runnable接口的类，用来实现没有返回结果的任务
    实现Callable接口的类，用来实现有返回结果的任务

在执行者框架（Executor framework）的内部，它提供一个ThreadFactory接口来创建线程，这是用来产生新的线程。在这个指南中，你将学习如何实现你自己的线程类，用一个工厂来创建这个类的线程，及如何在执行者中使用这个工厂，所以这个执行者将执行你的线程。

