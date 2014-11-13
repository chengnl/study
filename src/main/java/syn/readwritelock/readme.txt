锁所提供的最重要的改进之一就是ReadWriteLock接口和唯一 一个实现它的ReentrantReadWriteLock类。
这个类提供两把锁，一把用于读操作和一把用于写操作。同时可以有多个线程执行读操作，
但只有一个线程可以执行写操作。当一个线程正在执行一个写操作，不可能有任何线程执行读操作。


ReentrantReadWriteLock类有两把锁，一把用于读操作，一把用于写操作。用于读操作的锁，
是通过在 ReadWriteLock接口中声明的readLock()方法获取的。这个锁是实现Lock接口的一个对象，
所以我们可以使用lock()， unlock() 和tryLock()方法。用于写操作的锁，是通过在ReadWriteLock接口中
声明的writeLock()方法获取的。这个锁是实现Lock接 口的一个对象，所以我们可以使用lock()， unlock() 和tryLock()方法。