package reflection.lesson9.generics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 *@author chengnl
 *@date 2015年2月7日 下午5:56:53
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		
       try {
    	   Field field=MyEmail.class.getField("emails");
    	   Type genericFieldType=field.getGenericType();
    	   if(genericFieldType instanceof ParameterizedType){
    		    ParameterizedType aType = (ParameterizedType) genericFieldType;
    		    Type[] fieldArgTypes = aType.getActualTypeArguments();
    		    for(Type fieldArgType : fieldArgTypes){
    		        Class fieldArgClass = (Class) fieldArgType;
    		        System.out.println("fieldArgClass = " + fieldArgClass);
    		    }
    		}
    	   System.out.println(genericFieldType.getTypeName());
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
       
       try {
    	   Method field=MyEmail.class.getMethod("getEmails", null);
    	   Type genericFieldType=field.getGenericReturnType();
    	   if(genericFieldType instanceof ParameterizedType){
    		    ParameterizedType aType = (ParameterizedType) genericFieldType;
    		    Type[] fieldArgTypes = aType.getActualTypeArguments();
    		    for(Type fieldArgType : fieldArgTypes){
    		        Class fieldArgClass = (Class) fieldArgType;
    		        System.out.println("methodReturnArgClass = " + fieldArgClass);
    		    }
    		}
    	   System.out.println(genericFieldType.getTypeName());
		} catch ( SecurityException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       
       
       try {
    	   Method field=MyEmail.class.getMethod("setEmails", new Class<?>[]{List.class});
    	   Type[] genericFieldTypes=field.getGenericParameterTypes();
    	   for(Type genericFieldType:genericFieldTypes){
        	   if(genericFieldType instanceof ParameterizedType){
       		    ParameterizedType aType = (ParameterizedType) genericFieldType;
       		    Type[] fieldArgTypes = aType.getActualTypeArguments();
       		    for(Type fieldArgType : fieldArgTypes){
       		        Class fieldArgClass = (Class) fieldArgType;
       		        System.out.println("methodParamArgClass = " + fieldArgClass);
       		    }
       		} 
    	   }

		} catch ( SecurityException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
