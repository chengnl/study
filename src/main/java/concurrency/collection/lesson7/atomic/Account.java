package concurrency.collection.lesson7.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 *@author chengnl
 *@date 2014年12月25日 下午5:51:13
 *@version 1.0
 *@Description:账户
 */
public class Account {
     private  AtomicLong  balance;
     public Account() {
    	 balance=new AtomicLong();
     }
     public long getBalance(){
    	 return this.balance.get();
     }
     public void  addBalance(long balance){
    	 this.balance.addAndGet(balance);
     }
     public void  subtractAmount(long balance){
    	 this.balance.addAndGet(-balance);
     }
}
