package concurrency.collection.lesson9.atomicothers;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *@author chengnl
 *@date 2014年12月26日 下午2:06:40
 *@version 1.0
 *@Description:
 */
public class AtomicStampedReferenceTest {
	class  User{
		private String userName;
		public User() {
		}
	}
	public void  update(){
		User  user  = new User();
		AtomicStampedReference<User>  atomic = new AtomicStampedReference<>(user,1);
		atomic.set(user, 2);
		int   stampHolder[] =new int[1];
		atomic.get(stampHolder);
		System.out.println(stampHolder[0]);
	}
	public static void main(String[] args) {
		AtomicStampedReferenceTest  test = new AtomicStampedReferenceTest();
		test.update();
	}
}
