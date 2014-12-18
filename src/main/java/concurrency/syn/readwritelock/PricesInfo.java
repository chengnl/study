package concurrency.syn.readwritelock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月13日 上午10:02:57
 *@version 1.0
 *@Description:TODO 类描述
 */
public class PricesInfo {
         private  double  price1;
         private  double price2;
         private ReadWriteLock lock;
         public PricesInfo(){
        	 price1=1.0;
        	 price2=2.0;
        	 lock= new ReentrantReadWriteLock();
         }
         public double getPrice1(){
        	 lock.readLock().lock();
        	 double value = price1;
        	 System.out.printf("%s: Reader Price 1: %f\n", Thread.currentThread().getName(),price1);
        	 lock.readLock().unlock();
        	 return value;
         }
         public double getPrice2(){
        	 lock.readLock().lock();
        	 double value = price2;
        	 System.out.printf("%s: Reader Price 2: %f\n", Thread.currentThread().getName(),price2);
        	 lock.readLock().unlock();
        	 return value;
         }
         public void setPrices(double price1,double price2){
        	 lock.writeLock().lock();
        	 this.price1=price1;
        	 this.price2=price2;
        	 System.out.printf("%s: Writer Price 1: %f,Price 2: %f\n", Thread.currentThread().getName(),
        			 price1,price2);
        	 lock.writeLock().unlock();
         }
}
