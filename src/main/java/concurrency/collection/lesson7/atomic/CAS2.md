[原文](http://www.tuicool.com/articles/zuui6z)

##锁（lock）的代价

锁是用来做并发最简单的方式，当然其代价也是最高的。内核态的锁的时候需要操作系统进行一次上下文切换，加锁、释放锁会导致比较多的上下文切换和调度延时，等待锁的线程会被挂起直至锁释放。在上下文切换的时候，cpu之前缓存的指令和数据都将失效，对性能有很大的损失。用户态的锁虽然避免了这些问题，但是其实它们只是在没有真实的竞争时才有效。

Java在JDK1.5之前都是靠synchronized关键字保证同步的，这种通过使用一致的锁定协议来协调对共享状态的访问，可以确保无论哪个线程持有守护变量的锁，都采用独占的方式来访问这些变量，如果出现多个线程同时访问锁，那第一些线线程将被挂起，当线程恢复执行时，必须等待其它线程执行完他们的时间片以后才能被调度执行，在挂起和恢复执行过程中存在着很大的开销。锁还存在着其它一些缺点，当一个线程正在等待锁时，它不能做任何事。如果一个线程在持有锁的情况下被延迟执行，那么所有需要这个锁的线程都无法执行下去。如果被阻塞的线程优先级高，而持有锁的线程优先级低，将会导致优先级反转(Priority Inversion)。

##乐观锁与悲观锁

独占锁是一种悲观锁，synchronized就是一种独占锁，它假设最坏的情况，并且只有在确保其它线程不会造成干扰的情况下执行，会导致其它所有需要锁的线程挂起，等待持有锁的线程释放锁。而另一个更加有效的锁就是乐观锁。所谓乐观锁就是，每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止。

##volatile的问题

与锁相比，volatile变量是一和更轻量级的同步机制，因为在使用这些变量时不会发生上下文切换和线程调度等操作，但是volatile变量也存在一些局限：不能用于构建原子的复合操作，因此当一个变量依赖旧值时就不能使用volatile变量。（参考：谈谈volatiile）

##Java中的原子操作( atomic operations)

原子操作指的是在一步之内就完成而且不能被中断。原子操作在多线程环境中是线程安全的，无需考虑同步的问题。在java中，下列操作是原子操作：

    all assignments of primitive types except for long and double
    all assignments of references
    all operations of java.concurrent.Atomic* classes
    all assignments to volatile longs and doubles

问题来了，为什么long型赋值不是原子操作呢？例如：

long foo = 65465498L;

实时上java会分两步写入这个long变量，先写32位，再写后32位。这样就线程不安全了。如果改成下面的就线程安全了：

    private volatile long foo;

因为volatile内部已经做了synchronized.

##CAS无锁算法

要实现无锁（lock-free）的非阻塞算法有多种实现方法，其中 CAS（比较与交换，Compare and swap） 是一种有名的无锁算法。CAS, CPU指令，在大多数处理器架构，包括IA32、Space中采用的都是CAS指令，CAS的语义是“我认为V的值应该为A，如果是，那么将V的值更新为B，否则不修改并告诉V的值实际为多少”，CAS是项 乐观锁 技术，当多个线程尝试使用CAS同时更新同一个变量时，只有其中一个线程能更新变量的值，而其它线程都失败，失败的线程并不会被挂起，而是被告知这次竞争中失败，并可以再次尝试。CAS有3个操作数，内存值V，旧的预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做。CAS无锁算法的C实现如下：

int compare_and_swap (int* reg, int oldval, int newval) 
{
  ATOMIC();
  int old_reg_val = *reg;
  if (old_reg_val == oldval) 
     *reg = newval;
  END_ATOMIC();
  return old_reg_val;
}

##CAS（乐观锁算法）的基本假设前提

CAS比较与交换的伪代码可以表示为：

do{  
       备份旧数据； 
       基于旧数据构造新数据； 
}while(!CAS( 内存地址，备份的旧数据，新数据 ))  

就是指当两者进行比较时，如果相等，则证明共享数据没有被修改，替换成新值，然后继续往下运行；如果不相等，说明共享数据已经被修改，放弃已经所做的操作，然后重新执行刚才的操作。容易看出 CAS 操作是基于共享数据不会被修改的假设，采用了类似于数据库的 commit-retry 的模式。当同步冲突出现的机会很少时，这种假设能带来较大的性能提升。

##JVM对CAS的支持：AtomicInt, AtomicLong.incrementAndGet()

在JDK1.5之前，如果不编写明确的代码就无法执行CAS操作，在JDK1.5中引入了底层的支持，在int、long和对象的引用等类型上都公开了CAS的操作，并且JVM把它们编译为底层硬件提供的最有效的方法，在运行CAS的平台上，运行时把它们编译为相应的机器指令，如果处理器/CPU不支持CAS指令，那么JVM将使用自旋锁。因此，值得注意的是， CAS解决方案与平台/编译器紧密相关（比如x86架构下其对应的汇编指令是lock cmpxchg，如果想要64Bit的交换，则应使用lock cmpxchg8b。在.NET中我们可以使用Interlocked.CompareExchange函数） 。

在原子类变量中，如java.util.concurrent.atomic中的AtomicXXX，都使用了这些底层的JVM支持为数字类型的引用类型提供一种高效的CAS操作，而在java.util.concurrent中的大多数类在实现时都直接或间接的使用了这些原子变量类。


##CAS的例子：非阻塞堆栈

下面是比非阻塞自增稍微复杂一点的CAS的例子：非阻塞堆栈/ ConcurrentStack 。 ConcurrentStack 中的 push() 和 pop() 操作在结构上与 NonblockingCounter 上相似，只是做的工作有些冒险，希望在 “提交” 工作的时候，底层假设没有失效。 push() 方法观察当前最顶的节点，构建一个新节点放在堆栈上，然后，如果最顶端的节点在初始观察之后没有变化，那么就安装新节点。如果 CAS 失败，意味着另一个线程已经修改了堆栈，那么过程就会重新开始。

`````
			public class ConcurrentStack<E> {
			    AtomicReference<Node<E>> head = new AtomicReference<Node<E>>();
			    public void push(E item) {
			        Node<E> newHead = new Node<E>(item);
			        Node<E> oldHead;
			        do {
			            oldHead = head.get();
			            newHead.next = oldHead;
			        } while (!head.compareAndSet(oldHead, newHead));
			    }
			    public E pop() {
			        Node<E> oldHead;
			        Node<E> newHead;
			        do {
			            oldHead = head.get();
			            if (oldHead == null) 
			                return null;
			            newHead = oldHead.next;
			        } while (!head.compareAndSet(oldHead,newHead));
			        return oldHead.item;
			    }
			    static class Node<E> {
			        final E item;
			        Node<E> next;
			        public Node(E item) { this.item = item; }
			    }
			}
`````

在轻度到中度的争用情况下，非阻塞算法的性能会超越阻塞算法，因为 CAS 的多数时间都在第一次尝试时就成功，而发生争用时的开销也不涉及线程挂起和上下文切换，只多了几个循环迭代。没有争用的 CAS 要比没有争用的锁便宜得多（这句话肯定是真的，因为没有争用的锁涉及 CAS 加上额外的处理），而争用的 CAS 比争用的锁获取涉及更短的延迟。

在高度争用的情况下（即有多个线程不断争用一个内存位置的时候），基于锁的算法开始提供比非阻塞算法更好的吞吐率，因为当线程阻塞时，它就会停止争用，耐心地等候轮到自己，从而避免了进一步争用。但是，这么高的争用程度并不常见，因为多数时候，线程会把线程本地的计算与争用共享数据的操作分开，从而给其他线程使用共享数据的机会。

##CAS的例子3：非阻塞链表

以上的示例（自增计数器和堆栈）都是非常简单的非阻塞算法，一旦掌握了在循环中使用 CAS，就可以容易地模仿它们。对于更复杂的数据结构，非阻塞算法要比这些简单示例复杂得多，因为修改链表、树或哈希表可能涉及对多个指针的更新。CAS 支持对单一指针的原子性条件更新，但是不支持两个以上的指针。所以，要构建一个非阻塞的链表、树或哈希表，需要找到一种方式，可以用 CAS 更新多个指针，同时不会让数据结构处于不一致的状态。

在链表的尾部插入元素，通常涉及对两个指针的更新：“尾” 指针总是指向列表中的最后一个元素，“下一个” 指针从过去的最后一个元素指向新插入的元素。因为需要更新两个指针，所以需要两个 CAS。在独立的 CAS 中更新两个指针带来了两个需要考虑的潜在问题：如果第一个 CAS 成功，而第二个 CAS 失败，会发生什么？如果其他线程在第一个和第二个 CAS 之间企图访问链表，会发生什么？

对于非复杂数据结构，构建非阻塞算法的 “技巧” 是确保数据结构总处于一致的状态（甚至包括在线程开始修改数据结构和它完成修改之间），还要确保其他线程不仅能够判断出第一个线程已经完成了更新还是处在更新的中途，还能够判断出如果第一个线程走向 AWOL，完成更新还需要什么操作。如果线程发现了处在更新中途的数据结构，它就可以 “帮助” 正在执行更新的线程完成更新，然后再进行自己的操作。当第一个线程回来试图完成自己的更新时，会发现不再需要了，返回即可，因为 CAS 会检测到帮助线程的干预（在这种情况下，是建设性的干预）。

这种 “帮助邻居” 的要求，对于让数据结构免受单个线程失败的影响，是必需的。如果线程发现数据结构正处在被其他线程更新的中途，然后就等候其他线程完成更新，那么如果其他线程在操作中途失败，这个线程就可能永远等候下去。即使不出现故障，这种方式也会提供糟糕的性能，因为新到达的线程必须放弃处理器，导致上下文切换，或者等到自己的时间片过期（而这更糟）。
`````
		public class LinkedQueue <E> {
		    private static class Node <E> {
		        final E item;
		        final AtomicReference<Node<E>> next;
		        Node(E item, Node<E> next) {
		            this.item = item;
		            this.next = new AtomicReference<Node<E>>(next);
		        }
		    }
		    private AtomicReference<Node<E>> head
		        = new AtomicReference<Node<E>>(new Node<E>(null, null));
		    private AtomicReference<Node<E>> tail = head;
		    public boolean put(E item) {
		        Node<E> newNode = new Node<E>(item, null);
		        while (true) {
		            Node<E> curTail = tail.get();
		            Node<E> residue = curTail.next.get();
		            if (curTail == tail.get()) {
		                if (residue == null) /* A */ {
		                    if (curTail.next.compareAndSet(null, newNode)) /* C */ {
		                        tail.compareAndSet(curTail, newNode) /* D */ ;
		                        return true;
		                    }
		                } else {
		                    tail.compareAndSet(curTail, residue) /* B */;
		                }
		            }
		        }
		    }
		}

`````