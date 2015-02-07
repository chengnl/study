package reflection.lesson10.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 *@author chengnl
 *@date 2015年2月7日 下午11:06:45
 *@version 1.0
 *@Description:cglib代理类
 */
public class IWorkCglib implements MethodInterceptor{
	private Object target;
	public Object getInstance(Object target){
		this.target=target;
		Enhancer enhancer = new Enhancer(); 
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		System.out.println("before call method");
		arg3.invokeSuper(arg0, arg2); 
		System.out.println("after call method");
		return null;
	}

}
