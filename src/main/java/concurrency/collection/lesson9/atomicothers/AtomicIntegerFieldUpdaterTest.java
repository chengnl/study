package concurrency.collection.lesson9.atomicothers;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 *@author chengnl
 *@date 2014年12月26日 上午10:28:07
 *@version 1.0
 *@Description:AtomicIntegerFieldUpdater使用
 *反射以原子方式修改volatile类型字段的变量
 */
public class AtomicIntegerFieldUpdaterTest {
	class DemoData{
		public volatile  int count=1,num=1;
	}
	AtomicIntegerFieldUpdater<DemoData> getUpdater(String fieldName){
		//抽象类的静态工厂方法
		return AtomicIntegerFieldUpdater.newUpdater(DemoData.class, fieldName);
	}
	
	public void doIt(){
		DemoData  test = new DemoData();
		System.out.printf("count =%d \n",getUpdater("count").addAndGet(test, 10));
		System.out.printf("num =%d \n",getUpdater("num").addAndGet(test, 100));
	}
    public static void main(String[] args) {
    	AtomicIntegerFieldUpdaterTest  test = new AtomicIntegerFieldUpdaterTest();
    	test.doIt();
	}
}
