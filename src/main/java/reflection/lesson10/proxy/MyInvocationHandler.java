package reflection.lesson10.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *@author chengnl
 *@date 2015年2月7日 下午10:13:51
 *@version 1.0
 *@Description:代理处理类 
 */
public class MyInvocationHandler implements InvocationHandler{
	public IWork target;
	public MyInvocationHandler(IWork target) {
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before call method");
		method.invoke(this.target, args);
		System.out.println("after call method");
		return null;
	}

}
