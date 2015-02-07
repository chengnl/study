package reflection.lesson10.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 *@author chengnl
 *@date 2015年2月7日 下午10:10:14
 *@version 1.0
 *@Description:动态代理
 */
public class DyProxy {
	public IWork target;
	public DyProxy(IWork target) {
		this.target=target;
	}
	
    public  IWork getProxyObj(){
    	InvocationHandler  h=new MyInvocationHandler(this.target);
    	IWork work=(IWork)Proxy.newProxyInstance(IWork.class.getClassLoader(), new Class[]{IWork.class}, h);
		return work;
    }
}
