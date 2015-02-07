package reflection.lesson8.annotation;
/**
 *@author chengnl
 *@date 2015年2月7日 下午3:58:41
 *@version 1.0
 *@Description:演示类
 */
@MyAnnotation(name="teacher",value="gao er")
public class Teacher {
   @MyAnnotation(name="fistname",value="wang")
   public String name;
   @MyAnnotation(name="getFullName",value="wang er")
   public String getFullName(@MyAnnotation(name="lastName",value="er") String lastName){
	   return "hello world";
   }
}
