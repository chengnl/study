## 创建一个Fork/Join池

它包括：

    创建一个ForkJoinPool对象来执行任务。
    创建一个ForkJoinPool执行的ForkJoinTask类。

这个示例中使用Fork/Join框架的主要特点，如下：

   你将使用默认构造器创建ForkJoinPool。在这个任务中，你将使用Java API文档推荐的结构：

````
   If (problem size < default size){
 	tasks=divide(task);
	execute(tasks);	} else {
	resolve problem using another algorithm;
  }

````
你将以一种同步方式执行任务。当一个任务执行2个或2个以上的子任务时，它将等待它们的结束。通过这种方式 ，正在执行这些任务的线程（工作线程）将会查找其他任务（尚未执行的任务）来执行，充分利用它们的执行时间。你将要实现的任务将不会返回任何结果，所以你将使用RecursiveAction作为它们实现的基类。

##本例工作原理

在这个示例中，你已经创建一个ForkJoinPool对象和一个在池中执行的ForkJoinTask类的子类。为了创建ForkJoinPool对象，你已经使用了无参构造器，所以它会以默认的配置来执行。它创建一个线程数等于计算机处理器数的池。当ForkJoinPool对象被创建时，这些线程被创建并且在池中等待，直到有任务到达让它们执行。

由于Task类没有返回结果，所以它继承RecursiveAction类。在这个指南中，你已经使用了推荐的结构来实现任务。如果这个任务更新超过10产品，它将被分解成两部分，并创建两个任务，一个任务执行一部分。你已经在Task类中使用first和last属性，用来了解这个任务要更新的产品队列的位置范围。你已经使用first和last属性，只复制产品数列一次，而不是为每个任务创建不同的数列。

它调用invokeAll()方法，执行每个任务所创建的子任务。这是一个同步调用，这个任务在继续（可能完成）它的执行之前，必须等待子任务的结束。当任务正在等待它的子任务（结束）时，正在执行它的工作线程执行其他正在等待的任务。在这种行为下，Fork/Join框架比Runnable和Callable对象本身提供一种更高效的任务管理。

ForkJoinTask类的invokeAll()方法是执行者（Executor）和Fork/Join框架的一个主要区别。在执行者框架中，所有任务被提交给执行者，而在这种情况下，这些任务包括执行和控制这些任务的方法都在池内。你已经在Task类中使用invokeAll()方法，它是继承了继承ForkJoinTask类的RecursiveAction类。

你使用execute()方法提交唯一的任务给这个池，用来所有产品数列。在这种情况下，它是一个异步调用，而主线程继续它的执行。


##ForkJoinPool类提供其他的方法，用来执行一个任务。

这些方法如下：

    execute (Runnable task)：这是在这个示例中，使用的execute()方法的另一个版本。在这种情况下，你可以提交一个Runnable对象给ForkJoinPool类。注意：ForkJoinPool类不会对Runnable对象使用work-stealing算法。它（work-stealing算法）只用于ForkJoinTask对象。

    invoke(ForkJoinTask<T> task)：当execute()方法使用一个异步调用ForkJoinPool类，正如你在本示例中所学的，invoke()方法使用同步调用ForkJoinPool类。这个调用不会（立即）返回，直到传递的参数任务完成它的执行。

    你也可以使用在ExecutorService接口的invokeAll()和invokeAny()方法。这些方法接收一个Callable对象作为参数。ForkJoinPool类不会对Callable对象使用work-stealing算法，所以你最好使用执行者去执行它们。

ForkJoinTask类同样提供在示例中使用的invokeAll()的其他版本。这些版本如下：

    invokeAll(ForkJoinTask<?>… tasks)：这个版本的方法使用一个可变参数列表。你可以传入许多你想要执行的ForkJoinTask对象作为参数。

    invokeAll(Collection<T> tasks)：这个版本的方法接收一个泛型类型T对象的集合（如：一个ArrayList对象，一个LinkedList对象或者一个TreeSet对象）。这个泛型类型T必须是ForkJoinTask类或它的子类。

即使ForkJoinPool类被设计成用来执行一个ForkJoinTask，你也可以直接执行Runnable和Callable对象。你也可以使用ForkJoinTask类的adapt()方法来执行任务，它接收一个Callable对象或Runnable对象（作为参数）并返回一个ForkJoinTask对象。



