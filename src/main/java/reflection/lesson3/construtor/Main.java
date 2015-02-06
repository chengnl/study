package reflection.lesson3.construtor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *@author chengnl
 *@date 2015年2月7日 上午10:43:19
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		Constructor[]  cons = Book.class.getConstructors();
		for(Constructor con:cons){
			System.out.println("Constructor :"+con.getName()+"  param size="+con.getParameterCount());
			//获取指定构造方法的方法参数信息
			System.out.println("Constructor :"+con.getName()+"  param type="+con.getParameterTypes());
		}
		
		
		//如果要访问的构造方法的方法参数类型，获取指定的构造方法
		try {
			Constructor  con = Book.class.getConstructor();
			try {
				//利用Constructor对象实例化一个类
				Book book=(Book) con.newInstance();
				System.out.println("book info ="+book.toString());
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			Constructor  con = Book.class.getConstructor(new Class[]{String.class});
			try {
				//利用Constructor对象实例化一个类
				Book book=(Book) con.newInstance("不一样的卡梅拉");
				System.out.println("book info ="+book.toString());
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		
		try {
			Constructor  con = Book.class.getConstructor(new Class[]{String.class,String.class});
			try {
				//利用Constructor对象实例化一个类
				Book book=(Book) con.newInstance("不一样的卡梅拉","（法）约里波瓦 著，（法）艾利施 绘");
				System.out.println("book info ="+book.toString());
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

}
