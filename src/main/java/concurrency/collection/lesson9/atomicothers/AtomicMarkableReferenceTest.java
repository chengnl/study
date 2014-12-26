package concurrency.collection.lesson9.atomicothers;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 *@author chengnl
 *@date 2014年12月26日 上午11:09:13
 *@version 1.0
 *@Description:
 */
public class AtomicMarkableReferenceTest {
	class  User{
		private String userName;
		public User() {
		}
	}
	public void  update(){
		User  user  = new User();
		AtomicMarkableReference<User>  atomic = new AtomicMarkableReference<>(user,true);
		atomic.set(user, false);
		boolean   test[] =new boolean[1];
		atomic.get(test);
		System.out.println(test[0]);
	}
	public static void main(String[] args) {
		AtomicMarkableReferenceTest  test = new AtomicMarkableReferenceTest();
		test.update();
	}
	
}
