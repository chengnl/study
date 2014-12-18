在任务中抛出异常
===
##在Java中有两种异常：

       已检查异常（Checked exceptions）：这些异常必须在一个方法的throws从句中指定或在内部捕捉它们。比如：IOException或ClassNotFoundException。
       未检查异常（Unchecked exceptions）：这些异常不必指定或捕捉。比如：NumberFormatException。
在ForkJoinTask类的compute()方法中，你不能抛出任何已检查异常，因为在这个方法的实现中，它没有包含任何抛出（异常）声明。你必须包含必要的代码来处理异常。但是，你可以抛出（或者它可以被任何方法或使用内部方法的对象抛出）一个未检查异常。ForkJoinTask和ForkJoinPool类的行为与你可能的期望不同。程序不会结束执行，并且你将不会在控制台看到任何关于异常的信息。它只是被吞没，好像它没抛出（异常）。你可以使用ForkJoinTask类的一些方法，得知一个任务是否抛出异常及其异常种类。在这个指南中，你将学习如何获取这些信息。

##它是如何工作的…

在这个指南中，你已经实现Task类来处理一个数字数组。它检查要处理的数字块是否是10个或更多的元素。在这种情况下，它将数字块分成两块，并创建两个新的Task对象来处理这些块。否则，他查找数组中的第4个位置的元素（索引号3）。如果这个元素在任务要处理的块中，它抛出一个RuntimeException异常。

当你执行这个程序，异常是抛出了，但程序并没有停止。在Main类中，你已经使用发起任务调用ForkJoinTask类的isCompletedAbnormally()方法。如果任务或它的子任务抛出异常，这个方法返回true。你同时使用了同样对象的getException()方法来获取已抛出的Exception对象。

当你在一个任务中抛出一个未检查异常时，它也影响到它的父任务（把它提交到ForkJoinPool类的任务）和父任务的父任务，以此类推。

这些任务是那些及其父任务抛出异常的任务。它们全部异常地完成。考虑到这一点，当你使用ForkJoinPool和ForkJoinTask对象开发一个程序，当你不想这种行为时，可以抛出异常。

##不止这些…

你可以获取与这个例子相同的结果，如果不是抛出异常，你可以使用ForkJoinTask类的completeExceptionally()方法。代码如下：
````
Exception e=new Exception("This task throws an Exception: "+ "Task
from "+start+" to "+end);
completeExceptionally(e);

````