使用线程安全的NavigableMap
===

Java API 提供的有趣的数据结构，并且你可以在并发应用程序中使用，它就是ConcurrentNavigableMap接口的定义。实现ConcurrentNavigableMap接口的类存储以下两部分元素：

    唯一标识元素的key
    定义元素的剩余数据

每部分在不同的类中实现。

Java API 也提供了这个接口的实现类，这个类是ConcurrentSkipListMap，它实现了非阻塞列表且拥有ConcurrentNavigableMap的行为。在内部实现中，它使用**Skip List(跳表)**来存储数据。Skip List是基于并行列表的数据结构，它允许我们获取类似二叉树的效率。使用它，你可以得到一个**排序的数据结构，这比排序数列使用更短的访问时间来插入、搜索和删除元素**。

注意：在1990年，由William Pugh引入Skip List。

当你往map中插入数据时，它使用key来排序它们，所以，所有元素将是有序的。除了返回具体的元素，这个类也提供了获取map的子map的方法。

在这个指南中，你将学习如何使用ConcurrentSkipListMap类来实现一个通讯录的map。

##它是如何工作的...

在这个指南中，我们已实现Task类来存储Contact对象到NavigableMap 中。每个通讯录都有一个名称（创建它的任务的ID的）和电话号码（1000到2000之间的数字）。我们已使用这些值的连续值作为通讯录的key。每个Task对象创建1000个通讯录，并使用put()方法将它们存储到NavigableMap中。

注意：如果你插入的key已存在，那么这个key的元素将被新的元素取代。

Main类的main()方法创建25个Task对象，并使用A-Z的字母作为IDs。然后，你已使用一些方法从map中获取数据。firstEntry()方法返回map第一个元素的Map.Entry对象，且不会删除这个元素。这个对象包含key和元素。你已调用getValue()方法来获取元素。你可以使用getKey()来获取元素的key。

lastEntry()方法返回map最后一个元素的Map.Entry对象，subMap()方法返回map的部分元素的ConcurrentNavigableMap对象。在这个例子中，元素拥有A1996到B1002之间的key。在这种情况下，你可以使用pollFirst()方法来处理subMap()方法返回的这些元素。这个方法将返回并删除submap中的第一个Map.Entry对象。


##不止这些...

ConcurrentSkipListMap类有其他有趣的方法，这些方法如下：

    headMap(K toKey)：K是参数化ConcurrentSkipListMap对象的Key值的类。返回此映射的部分视图，其键值小于 toKey。
    tailMap(K fromKey)：K是参数化ConcurrentSkipListMap对象的Key值的类。返回此映射的部分视图，其键大于等于 fromKey。
    putIfAbsent(K key, V Value)：如果key不存在map中，则这个方法插入指定的key和value。
    pollLastEntry()：这个方法返回并删除map中最后一个元素的Map.Entry对象。
    replace(K key, V Value)：如果这个key存在map中，则这个方法将指定key的value替换成新的value。
