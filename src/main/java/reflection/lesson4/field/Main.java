package reflection.lesson4.field;

import java.lang.reflect.Field;

/**
 *@author chengnl
 *@date 2015年2月7日 上午10:59:02
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		//返回的Field对象数组包含了指定类中声明为公有的(public)的所有变量集合
		Field[]  fields=Book.class.getFields();
		System.out.println("fields count="+fields.length);
		try {
			Field   field = Book.class.getField("pub");
			System.out.println("field type="+field.getType());
			
			
			//传入Field.get()/Field.set()方法的参数objetInstance应该是拥有指定变量的类的实例
			Book  book = new Book();
			field.set(book, "二十一世纪出版社");
			
			System.out.println("book info="+book.toString());
			
			System.out.println("book pub="+field.get(book));
			
			//如果变量是静态变量的话(public static)那么在调用Field.get()/Field.set()方法的时候传入null做为参数而不用传递拥有该变量的类的实例。
			//(如果传入拥有该变量的类的实例也可以得到相同的结果)
			
			Field   field1 = Book.class.getField("pubDate");
			System.out.println("field type="+field1.getType());
			field1.set(null, "2006-10-1");
			
			System.out.println("book info="+book.toString());
			
			System.out.println("book pubDate="+field1.get(book));
			
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
        
	}

}
