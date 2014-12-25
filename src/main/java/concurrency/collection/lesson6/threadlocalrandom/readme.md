创建并发随机数
==

Java并发API提供指定的类在并发应用程序中生成伪随机。它是ThreadLocalRandom类，这是Java 7版本中的新类。它使用线程局部变量。每个线程希望以不同的生成器生成随机数。在这种机制下，将获得比使用共享的Random对象为所有线程生成随机数更好的性能。

##它是如何工作的…

TaskLocalRandom类是这个例子的关键。在这个类的构造器中，我们使用ThreadLocalRandom的current()方法。这是一个静态方法，它返回当前线程的ThreadLocalRandom对象，可以使用这个对象生成随机数。如果调用这个方法的线程没有与任何（ThreadLocalRandom）对象关联，这个类将创建一个新的ThreadLocalRandom对象。在这种情况下，使用这个方法初始化与任务相关的随机数生成器，所以，在这个方法下次调用时，它直接获取创建的ThreadLocalRandom对象。

在TaskLocalRandom类的run()方法中，调用一次current()方法来生成与线程相关的随机数生成器，然后，你调用一次nextInt()方法，并传入数值10作为参数。这个方法将返回0到10之间的一个伪随机数。每个任务生成10个随机数。

##不止这些…

ThreadLocalRandom类同样提供方法来生成long、float 和 double类型的数以及 Boolean值。这些方法允许你传入一个数值作为参数，然后生成0到这个数值之间的随机数。还有允许你传入两个参数的其他方法，然后生成在这两个参数数值之间的随机数。

