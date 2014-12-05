执行者分离任务的启动和结果的处理
========
 ## 简介
通常，当你使用执行者执行并发任务时，你将会提交 Runnable或Callable任务给这个执行者，并获取Future对象控制这个方法。你可以发现这种情况，
你需要提交任务给执行者在一个对象中，而处理结果在另一个对象中。基于这种情况，Java并发API提供CompletionService类。

CompletionService 类有一个方法来提交任务给执行者和另一个方法来获取已完成执行的下个任务的Future对象。在内部实现中，它使用Executor对象
执行任务。这种行为的优点是共享一个CompletionService对象，并提交任务给执行者，这样其他（对象）可以处理结果。其局限性是，第二个对象只能
获取那些已经完成它们的执行的任务的Future对象，所以，这些Future对象只能获取任务的结果。

示例的主类中，你使用Executors类的newCachedThreadPool()方法创建ThreadPoolExecutor。然后，使用这个对象初始化一个CompletionService对象，
因为CompletionService需要使用一个执行者来执行任务。利用CompletionService执行一个任务，你需要使用submit()方法，如在ReportRequest类中。

当其中一个任务被执行，CompletionService完成这个任务的执行时，这个CompletionService在一个队列中存储Future对象来控制它的执行。
poll()方法用来查看这个列队，如果有任何任务执行完成，那么返回列队的第一个元素，它是一个已完成任务的Future对象。当poll()方法返回一个
Future对象时，它将这个Future对象从队列中删除。这种情况下，你可以传两个属性给那个方法，表明你想要等任务结果的时间，以防队列中的
已完成任务的结果是空的。

一旦CompletionService对象被创建，你创建2个ReportRequest对象，用来执行3个ReportGenerator任务，每个都在CompletionService中，
和一个ReportSender任务，它将会处理已提交给2个ReportRequest对象的任务所产生的结果。


CompletionService类可以执行Callable和Runnable任务。在这个示例中，你已经使用Callable，但你同样可以提交Runnable对象。
由于Runnable对象不会产生结果，CompletionService类的理念不适用于这些情况。

这个类同样提供其他两个方法，用来获取已完成任务的Future对象。这两个方法如下：

    poll()：不带参数版本的poll()方法，检查是否有任何Future对象在队列中。如果列队是空的，它立即返回null。否则，它返回第一个元素，并从列队中
    删除它。
    take()：这个方法，不带参数。检查是否有任何Future对象在队列中。如果队列是空的，它阻塞线程直到队列有一个元素。当队列有元素，它返回第一
    元素，并从列队中删除它。
    
   
 ##  1、完成一个任务处理一个任务 ，不用等待所有结束再处理
 ```   
 void solve(Executor e,
        Collection<Callable<Result>> solvers)
        throws InterruptedException, ExecutionException {
       CompletionService<Result> ecs
             = new ExecutorCompletionService<Result>(e);
       for (Callable<Result> s : solvers)
          ecs.submit(s);
      int n = solvers.size();
      for (int i = 0; i < n; ++i) {
          Result r = ecs.take().get();
          if (r != null)
              use(r);
      }
  }}
   
  ```
   ##  2、随机等待一个任务完成，处理下面的业务逻辑，取消其他任务
   
    ```
     void solve(Executor e,
            Collection<Callable<Result>> solvers)
      throws InterruptedException {
      CompletionService<Result> ecs
          = new ExecutorCompletionService<Result>(e);
      int n = solvers.size();
      List<Future<Result>> futures
          = new ArrayList<Future<Result>>(n);
      Result result = null;
      try {
          for (Callable<Result> s : solvers)
              futures.add(ecs.submit(s));
          for (int i = 0; i < n; ++i) {
              try {
                  Result r = ecs.take().get();
                  if (r != null) {
                      result = r;
                      break;
                  }
              } catch (ExecutionException ignore) {}
          }
      }
      finally {
          for (Future<Result> f : futures)
              f.cancel(true);
      }
 
      if (result != null)
          use(result);
  }}
  
    ```
