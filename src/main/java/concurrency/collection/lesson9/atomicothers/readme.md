AtomicIntegerFieldUpdater<T>/AtomicLongFieldUpdater<T>/AtomicReferenceFieldUpdater<T,V>是基于反射的原子更新字段的值。

相应的API也是非常简单的，但是也是有一些约束的。

（1）字段必须是volatile类型的！在后面的章节中会详细说明为什么必须是volatile，volatile到底是个什么东西。

（2）字段的描述类型（修饰符public/protected/default/private）是与调用者与操作对象字段的关系一致。也就是说调用者能够直接操作对象字段，那么就可以反射进行原子操作。但是对于父类的字段，子类是不能直接操作的，尽管子类可以访问父类的字段。

（3）只能是实例变量，不能是类变量，也就是说不能加static关键字。

（4）只能是可修改变量，不能使final变量，因为final的语义就是不可修改。实际上final的语义和volatile是有冲突的，这两个关键字不能同时存在。

（5）对于AtomicIntegerFieldUpdater和AtomicLongFieldUpdater只能修改int/long类型的字段，不能修改其包装类型（Integer/Long）。如果要修改包装类型就需要使用AtomicReferenceFieldUpdater。



AtomicMarkableReference类描述的一个<Object,Boolean>的对，可以原子的修改Object或者Boolean的值，这种数据结构在一些缓存或者状态描述中比较有用。这种结构在单个或者同时修改Object/Boolean的时候能够有效的提高吞吐量。

 

AtomicStampedReference类维护带有整数“标志”的对象引用，可以用原子方式对其进行更新。对比AtomicMarkableReference类的<Object,Boolean>，AtomicStampedReference维护的是一种类似<Object,int>的数据结构，其实就是对对象（引用）的一个并发计数。但是与AtomicInteger不同的是，此数据结构可以携带一个对象引用（Object），并且能够对此对象和计数同时进行原子操作。

在后面的章节中会提到“ABA问题”，而AtomicMarkableReference/AtomicStampedReference在解决“ABA问题”上很有用。