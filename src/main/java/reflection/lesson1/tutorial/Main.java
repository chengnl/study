package reflection.lesson1.tutorial;

import java.lang.reflect.Method;

/**
 *@author chengnl
 *@date 2015年2月6日 下午11:51:59
 *@version 1.0
 *@Description:获取类的方法
 */
public class Main {

	public static void main(String[] args) {
		Method[] methods = Math.class.getMethods();
		for(Method method:methods){
			System.out.println("Math have method:"+method.getName());
		}

	}

}
