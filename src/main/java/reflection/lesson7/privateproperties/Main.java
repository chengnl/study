package reflection.lesson7.privateproperties;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *@author chengnl
 *@date 2015年2月7日 下午1:24:14
 *@version 1.0
 *@Description:测试
 */
public class Main {
   public static void main(String[] args) {
	   try {
		   Field field=Account.class.getDeclaredField("balance");
		   field.setAccessible(true);
		   Account account = new Account(20000);
		   System.out.println("account balance:"+field.getInt(account));
		   field.setInt(account, 1000);
		   System.out.println("account new balance:"+field.getInt(account));
		   
		   Method method =Account.class.getDeclaredMethod("getBalance", null);
		   method.setAccessible(true);
		   
		   int balance=(int)method.invoke(account, null);
		   
		   System.out.println("account balance:"+balance);
		   
		} catch (NoSuchFieldException | SecurityException 
				| IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
}
