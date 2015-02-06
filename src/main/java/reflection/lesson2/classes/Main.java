package reflection.lesson2.classes;

import java.lang.reflect.Modifier;

/**
 *@author chengnl
 *@date 2015年2月7日 上午12:00:50
 *@version 1.0
 *@Description:classes
 */
public class Main {

	public static void main(String[] args) {
		//编译期知道一个类的名字的话，那么可以使用如下的方式获取一个类的Class对象
		Class math = Math.class;
		
		//编译期知道一个类的名字的话，那么可以使用如下的方式获取一个类的Class对象
		//在使用Class.forName()方法时，你必须提供一个类的全名，这个全名包括类所在的包的名字
		try {
			Class string = Class.forName("java.lang.String");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//通过getName() 方法返回类的全限定类名（包含包名）
		System.out.println("class name:"+math.getName());
		//仅仅只是想获取类的名字(不包含包名)，那么可以使用getSimpleName()方法
		System.out.println("class simplename:"+math.getSimpleName());
		
		//可以通过Class对象来访问一个类的修饰符，即public,private,static等等的关键字
		//具体见Modifier的判断方法
		int modifiers = math.getModifiers();
		System.out.println("class modifiers:"+modifiers);
		//包名
		System.out.println("class package:"+math.getPackage());
		
		//父类
		System.out.println("class super class:"+math.getSuperclass().getName());
		
		//构造器
		System.out.println("class Constructor:"+math.getConstructors());
		
		//方法
		System.out.println("class methods:"+math.getMethods());
		
		//字段
		System.out.println("class fields:"+math.getFields());
		
		//注解
		System.out.println("class annotations:"+math.getAnnotations());
		
		

	}

}
