在并发编程中的一个经典问题是生产者与消费者问题，我们有一个数据缓冲区，一个或多个数据的生产者在缓冲区存储数据，而一个或多个数据的消费者，把数据从缓冲区取出。

由于缓冲区是一个共享的数据结构，我们必须采用同步机制，比如synchronized关键字来控制对它的访问。但是我们有更多的限制因素，如果缓冲区是满的，
生产者不能存储数据，如果缓冲区是空的，消费者不能取出数据。

对于这些类型的情况，Java在Object对象中提供wait()，notify()，和notifyAll() 方法的实现。
一个线程可以在synchronized代码块中调用wait()方法。如果在synchronized代码块外部调用wait()方法，
JVM会抛出IllegalMonitorStateException异常。当线程调用wait()方法，JVM让这个线程睡眠，
并且释放控制 synchronized代码块的对象，这样，虽然它正在执行但允许其他线程执行由该对象保护的其他synchronized代码块。
为了唤醒线程，你必 须在由相同对象保护的synchronized代码块中调用notify()或notifyAll()方法。



EventStorage 类的set（）方法和get（）方法是这个示例的关键。首先，set()方法检查storage属性是否有空闲空间。
如果它满了，调用wait()方法等 待有空闲的空间。当其他线程调用notifyAll()方法，这个线程将被唤醒并且再次检查这个条件。
这个notifyAll()方法并不保证线程会醒 来。这个过程是重复，直到storage有空闲空间，然后它可以生成一个新的事件并存储它。

get()方法的行为是相似的。首先，它检查storage是否有事件。如果EventStorage类是空的，调用wait()方法等待事件。
当其他线程调用notifyAll()方法，这个线程将被唤醒并且再次检查这个条件直到storage有一些事件。

注释：在while循环中，你必须保持检查条件和调用wait()方法。你不能继续执行，直到这个条件为true。