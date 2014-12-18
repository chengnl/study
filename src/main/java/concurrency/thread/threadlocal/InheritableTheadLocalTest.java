package concurrency.thread.threadlocal;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月6日 下午4:34:15
 *@version 1.0
 *@Description:继承本地变量
 */
public class InheritableTheadLocalTest {
    public static void main(String args[]){
    	//使用threadlocal 父子线程不共享value
    	final ThreadLocal<String> tl= new ThreadLocal<String>();
    	tl.set("ThreadLocal -val");
    	System.out.println("Main-1:"+tl.get());
    	new Thread(){
    		public void run(){
    			System.out.println("Child-1:"+tl.get());
    		}
    	}.start();
    	
    	//使用inheritableThreadLocal，父子线程共享value
    	final ThreadLocal<String> itl= new InheritableThreadLocal<String>();
    	itl.set("InheritableThreadLocal -val");
    	System.out.println("Main-2:"+itl.get());
    	new Thread(){
    		public void run(){
    			System.out.println("Child-2:"+itl.get());
    		}
    	}.start();
    	
    	
    	//使用inheritableThreadLocal，父子线程共享value childValue 初始化子线程本地本量
    	final ThreadLocal<String> ictl= new InheritableThreadLocal<String>(){
    		protected String  childValue(String value){
    			System.out.println("Main-3 parent thread value:"+value);
    			return "child";
    		}
    	};
    	ictl.set("InheritableThreadLocal child-val");
    	System.out.println("Main-3:"+ictl.get());
    	new Thread(){
    		public void run(){
    			System.out.println("Child-3:"+ictl.get());
    		}
    	}.start();
    }
}
