运行阶段性并发任务

Java 并发 API 提供的一个非常复杂且强大的功能是，能够使用Phaser类运行阶段性的并发任务。当某些并发任务是分成多个
步骤来执行时，那么此机制是非常有用的。Phaser类提供的机制是在每个步骤的结尾同步线程，所以除非全部线程完成第一个步骤，
否则线程不能开始进行第二步。

相对于其他同步应用，我们必须初始化Phaser类与这次同步操作有关的任务数，我们可以通过增加或者减少来不断的改变这个数。

在这个指南，你将学习如果使用Phaser类来同步3个并发任务。这3个任务会在3个不同的文件夹和它们的子文件夹中搜索扩展名
是.java并在72小时内修改过的文件。这个任务被分成3个步骤：

1. 在指定的文件夹和子文件夹中获得文件扩展名为.java的文件列表。
2. 过滤第一步的列表中修改超过72小时的文件。
3. 在操控台打印结果。

在步骤1和步骤2的结尾我们要检查列表是否为空。如果为空，那么线程直接结束运行并从phaser类中淘汰、


这程序开始创建的 Phaser 对象是用来在每个phase的末端控制线程的同步。Phaser的构造函数接收参与者的数量作为参数。
在这里，Phaser有3个参与者。这个数向Phaser表示 Phaser改变phase之前执行 arriveAndAwaitAdvance() 方法的线程数，
并叫醒正在休眠的线程。

一旦Phaser被创建，我们运行3个线程分别执行3个不同的FileSearch对象。

在例子里，我们使用 Windows operating system 的路径。如果你使用的是其他操作系统，那么修改成适应你的环境的路径。

FileSearch对象的 run() 方法中的第一个指令是调用Phaser对象的 arriveAndAwaitAdvance() 方法。像之前提到的，Phaser知道
我们要同步的线程的数量。当某个线程调用此方法，Phaser减少终结actual phase的线程数，并让这个线程进入休眠 直到全部
其余线程结束phase。在run() 方法前面调用此方法，没有任何 FileSearch 线程可以开始他们的工作，直到全部线程被创建。

在phase 1 和 phase 2 的末端，我们检查phase 是否生成有元素的结果list,或者它没有生成结果且list为空。在第一个情况，
checkResults() 方法之前提的调用 arriveAndAwaitAdvance()。在第二个情况，如果list为空，那就没有必要让线程继续了，
就直接返回吧。但是你必须通知phaser，将会少一个参与者。为了这个，我们使用arriveAndDeregister()。它通知phaser
线程结束了actual phase， 但是它将不会继续参见后面的phases,所以请phaser不要再等待它了。

在phase3的结尾实现了 showInfo() 方法, 调用了 phaser 的 arriveAndAwaitAdvance() 方法。这个调用，保证了全部线程在
同一时间结束。当此方法结束执行，有一个调用phaser的arriveAndDeregister() 方法。这个调用，我们撤销了对phaser线程的注册，
所以当全部线程结束时，phaser 有0个参与者。

最后，main() 方法等待3个线程的完成并调用phaser的 isTerminated() 方法。当phaser 有0个参与者时，它进入termination状态，
此状态与此调用将会打印true到操控台。

Phaser 对象可能是在这2中状态：

    Active: 当 Phaser 接受新的参与者注册，它进入这个状态，并且在每个phase的末端同步。 在此状态，Phaser像在这个指南里
    解释的那样工作。此状态不在Java 并发 API中。
    Termination: 默认状态，当Phaser里全部的参与者都取消注册，它进入这个状态，所以这时 Phaser 有0个参与者。更具体的说，
    当onAdvance() 方法返回真值时，Phaser 是在这个状态里。如果你覆盖那个方法,你可以改变它的默认行为。当 Phaser 在这个状态，
    同步方法 arriveAndAwaitAdvance()会 立刻返回，不会做任何同步。

Phaser 类的一个显著特点是你不需要控制任何与phaser相关的方法的异常。不像其他同步应用，线程们在phaser休眠不会响应任何
中断也不会抛出 InterruptedException 异常。




The Phaser类还提供了其他相关方法来改变phase。他们是：

    arrive(): 此方法示意phaser某个参与者已经结束actual phase了，但是他应该等待其他的参与者才能继续执行。小心使用此法，
    因为它并不能与其他线程同步。
    awaitAdvance(int phase): 如果我们传递的参数值等于phaser的actual phase，此方法让当前线程进入睡眠直到phaser的全部
    参与者结束当前的phase。如果参数值与phaser 的 actual phase不等，那么立刻返回。
    awaitAdvanceInterruptibly(int phaser): 此方法等同与之前的方法，只是在线程正在此方法中休眠而被中断时候，
    它会抛出InterruptedException 异常。

Phaser的参与者的注册
当你创建一个 Phaser 对象,你表明了参与者的数量。但是Phaser类还有2种方法来增加参与者的数量。他们是：

    register(): 此方法为Phaser添加一个新的参与者。这个新加入者会被认为是还未到达 actual phase.
    bulkRegister(int Parties): 此方法为Phaser添加一个特定数量的参与者。这些新加入的参与都会被认为是还未到达 actual phase.

Phaser类提供的唯一一个减少参与者数量的方法是arriveAndDeregister() 方法，它通知phaser线程已经结束了actual phase,而且
他不想继续phased的操作了。

强制终止 Phaser
当phaser有0个参与者，它进入一个称为Termination的状态。Phaser 类提供 forceTermination() 来改变phaser的状态，让它直接
进入Termination 状态，不在乎已经在phaser中注册的参与者的数量。此机制可能会很有用在一个参与者出现异常的情况下来强制
结束phaser.

当phaser在 Termination 状态， awaitAdvance() 和 arriveAndAwaitAdvance() 方法立刻返回一个负值，而不是一般情况下的正值
如果你知道你的phaser可能终止了，那么你可以用这些方法来确认他是否真的终止了。
