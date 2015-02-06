package reflection.lesson6.getset;

import java.lang.reflect.Method;

/**
 *@author chengnl
 *@date 2015年2月7日 上午11:43:51
 *@version 1.0
 *@Description:测试
 */
public class Main {
	public static void main(String[] args) {
		Method[] methods = Student.class.getMethods();
		  for(Method method : methods){
		    if(isGetter(method)) System.out.println("getter: " + method);
		    if(isSetter(method)) System.out.println("setter: " + method);
		  }
	}

	public static boolean isGetter(Method method){
	  if(!method.getName().startsWith("get"))      return false;
	  if(method.getParameterTypes().length != 0)   return false;
	  if(void.class.equals(method.getReturnType())) return false;
	  return true;
	}

	public static boolean isSetter(Method method){
	  if(!method.getName().startsWith("set")) return false;
	  if(method.getParameterTypes().length != 1) return false;
	  return true;
	}

	
	//java.beans.Introspector
	
	//内省(IntroSpector)是Java语言对JavaBean 类属性、事件的一种缺省处理方法。
	//Java中提供了一套API 用来访问某个属性的getter/setter方法，通过这些API 可以使你不需要了解这个规则，这些API存放于包java.beans 中。
	//一般的做法是通过类Introspector的getBeanInfo方法获取某个对象的BeanInfo 信息,然后通过BeanInfo来获取属性的描述器(PropertyDescriptor),
	//通过这个属性描述器就可以获取某个属性对应的getter/setter方法,然后我们就可以通过反射机制来调用这些方法。
}
