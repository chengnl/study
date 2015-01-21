实现ThreadFactory接口生成自定义的线程
==

在面向对象编程的世界中，工厂模式（factory pattern）是一个被广泛使用的设计模式。它是一个创建模式，它的目的是开发一个类，这个类的使命是创建一个或多个类的对象。然后，当我们要创建一个类的一个对象时，我们使用这个工厂而不是使用new操作。

    使用这个工厂，我们集中对象的创建，获取容易改变创建对象的类的优势，或我们创建这些对象的方式，容易限制创建对象的有限资源。比如，我们只能有一个类型的N个对象，就很容易产生关于对象创建的统计数据。

Java提供ThreadFactory接口，用来实现一个Thread对象工厂。Java并发API的一些高级工具，如执行者框架（Executor framework）或Fork/Join框架（Fork/Join framework），使用线程工厂创建线程。

在Java并发API中的其他工厂模式的例子是Executors类。它提供许多方法来创建不同类型的Executor对象。

在这个指南中，你将继承Thread类，以添加新功能，并且你将实现一个线程工厂来创建这个新类的线程。


一旦你有自己的线程类，你已实现一个工厂来创建这个实现了ThreadFactory接口的类的对象。如果你要使用你的工厂作为一个独立的对象，这个接口的使用并不是强制的，但是如果你想要用这个工厂使用Java并发API的其他类，你必须通过实现这个接口来构建你的工厂。ThreadFactory接口只有一个方法，newThread()方法接收一个Runnable对象作为参数，并且返回一个用来执行Runnable对象的Thread对象。

Java并发API提供Executors类来产生线程执行者，通常是ThreadPoolExecutor类的对象。你也可以使用defaultThreadFactory()方法，让这个类来获取ThreadFactory接口最基本的实现。这个方法产生的工厂所产生的基本Thread对象都属性同一个ThreadGroup对象。

你可以在你的程序中使用ThreadFactory接口用于任何目的，不一定要与执行者框架（Executor framework）有关。 