修改Lock的公平性

在ReentrantLock类和 ReentrantReadWriteLock类的构造器中，允许一个名为fair的boolean类型参数，
它允许你来控制这些类的行为。默认值为 false，这将启用非公平模式。在这个模式中，当有多个线程正在
等待一把锁（ReentrantLock或者 ReentrantReadWriteLock），这个锁必须选择它们中间的一个来获得进
入临界区，选择任意一个是没有任何标准的。true值将开启公平 模式。在这个模式中，当有多个线程正在
等待一把锁（ReentrantLock或者ReentrantReadWriteLock），这个锁必须选择它们 中间的一个来获得进入
临界区，它将选择等待时间最长的线程。考虑到之前解释的行为只是使用lock()和unlock()方法。由于tryLock()
方 法并不会使线程进入睡眠，即使Lock接口正在被使用，这个公平属性并不会影响它的功能。

