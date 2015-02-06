package reflection.lesson7.privateproperties;
/**
 *@author chengnl
 *@date 2015年2月7日 下午1:21:20
 *@version 1.0
 *@Description:演示实例
 */
public class Account {
	
	private int balance;
	public Account(int balance){
		this.balance=balance; 
	}
	private int getBalance(){
		return this.balance;
	}

}
