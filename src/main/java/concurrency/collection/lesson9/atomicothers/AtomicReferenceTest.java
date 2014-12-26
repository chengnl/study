package concurrency.collection.lesson9.atomicothers;

import java.util.concurrent.atomic.AtomicReference;

/**
 *@author chengnl
 *@date 2014年12月26日 上午11:07:05
 *@version 1.0
 * @param <E>
 *@Description:AtomicReferenceTest
 *原子方式修改对象,这里是实现的ConcurrentStack
 */
public class AtomicReferenceTest<E> {
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
