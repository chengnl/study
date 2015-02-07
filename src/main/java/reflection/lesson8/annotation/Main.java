package reflection.lesson8.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *@author chengnl
 *@date 2015年2月7日 下午4:04:13
 *@version 1.0
 *@Description:测试
 */
public class Main {
	public static void main(String[] args) {
		MyAnnotation annotation=Teacher.class.getAnnotation(MyAnnotation.class);
		System.out.println("Teacher annotation name="+annotation.name());
		System.out.println("Teacher annotation value="+annotation.value());
		
		
		try {
			Field field = Teacher.class.getField("name");
			MyAnnotation fieldAnno=field.getAnnotation(MyAnnotation.class);
			System.out.println("Field name annotation name="+fieldAnno.name());
			System.out.println("Field name annotation value="+fieldAnno.value());
			
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Method method = Teacher.class.getMethod("getFullName", new Class[]{String.class});
			MyAnnotation fieldAnno=method.getAnnotation(MyAnnotation.class);
			System.out.println("method getFullName annotation name="+fieldAnno.name());
			System.out.println("method getFullName annotation value="+fieldAnno.value());
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Method method = Teacher.class.getMethod("getFullName", new Class[]{String.class});
			Annotation[][] parameterAnnotations=method.getParameterAnnotations();
			Class[] parameterTypes = method.getParameterTypes();
			int i=0;
			for(Annotation[] annotations : parameterAnnotations){
			  Class parameterType = parameterTypes[i++];

			  for(Annotation paramannotation : annotations){
			    if(paramannotation instanceof MyAnnotation){
			        MyAnnotation myAnnotation = (MyAnnotation) paramannotation;
			        System.out.println("param: " + parameterType.getName());
			        System.out.println("name : " + myAnnotation.name());
			        System.out.println("value: " + myAnnotation.value());
			    }
			  }
			}
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//注解可以多个，Annotation[] annotations = aClass.getAnnotations();
		//Annotation[] annotations = method.getDeclaredAnnotations();

	}

}
