执行者执行返回结果的任务

Executor framework的一个优点是你可以并发执行返回结果的任务。Java并发API使用以下两种接口来实现：

    Callable：此接口有一个call()方法。在这个方法中，你必须实现任务的（处理）逻辑。Callable接口是一个参数化的接口。
    意味着你必须表明call()方法返回的数据类型。
    Future：此接口有一些方法来保证Callable对象结果的获取和管理它的状态。
    
    
    使用Future的get()方法。这个方法会等待，直到Callable对象完成call()方法的执 行，并且返回它的结果。如果线程在get()
    方法上等待结果时被中断，它将抛出InterruptedException异常。如果call()方法抛出 异常，这个方法会抛出ExecutionException异常。
    
    使用Future的isDone()方法来检查任务是否已经完成。
    
    使用Future的cancel(mayInterruptIfRunning)方法可以控制任务的状态：可以取消任务
    
    
    
    当调用Future对象的get()方法，并且这个对象控制的任务未完成，这个方法会阻塞直到任务完成。Future接口提供其他版本的get()方法：

    get(long timeout, TimeUnit unit)：这个版本的get方法，如果任务的结果不可用，等待它在指定的时间内。如果时间超时，并且结果不可用，
    这个方法返回null值。 TimeUnit类是个枚举类，有如下常量：DAYS，HOURS，MICROSECONDS， MILLISECONDS， MINUTES,，
    NANOSECONDS 和SECONDS。
    
