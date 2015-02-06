package reflection.lesson5.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *@author chengnl
 *@date 2015年2月7日 上午11:21:44
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		//返回的Method对象数组包含了指定类中声明为公有的(public)的所有变量集合
		Method[] methods =LampOper.class.getMethods();
		for(Method method:methods ){
			System.out.println("LampOper method name ="+method.getName());
			System.out.println("LampOper method param count ="+method.getParameterCount());
			System.out.println("LampOper method param types ="+method.getParameterTypes());
			System.out.println("LampOper method return type ="+method.getReturnType());
		}
		
		
		//通过Method对象调用方法
		try {
			LampOper  lamp = new LampOper();
			Method  method = LampOper.class.getMethod("open", new Class[]{String.class});
			//如果是一个静态方法调用的话则可以用null代替指定对象作为invoke()的参数
			int result=(int)method.invoke(lamp, "white");
			System.out.println("LampOper method open result="+result);
			
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException 
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		

	}

}
