package reflection.lesson8.annotation;

import java.lang.annotation.*;

/**
 *@author chengnl
 *@date 2015年2月7日 下午3:51:35
 *@version 1.0
 *@Description:自定义注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
public @interface MyAnnotation {
   public String name();
   public String value();
}
