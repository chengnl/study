本地线程类还提供 remove() 方法，删除存储在线程本地变量里的值。

Java 并发 API 包括 InheritableThreadLocal 类提供线程创建线程的值的遗传性 。
如果线程A有一个本地线程变量，然后它创建了另一个线程B，那么线程B将有与A相同的本地线程变量值。
 你可以覆盖 childValue() 方法来初始子线程的本地线程变量的值。 它接收父线程的本地线程变量作为参数。