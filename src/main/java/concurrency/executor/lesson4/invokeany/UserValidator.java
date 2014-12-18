package concurrency.executor.lesson4.invokeany;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月2日 下午3:11:49
 *@version 1.0
 *@Description:用户验证
 */
public class UserValidator {
      private  String name;
      public UserValidator(String name){
    	  this.name=name;
      }
      public boolean validate(String name ,String password){
    	  long duration = new Random().nextInt(10);
    	  System.out.printf("validator %s :validating a user during %d seconds\n",this.name,duration);
    	  try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
    	  return new Random().nextBoolean();
      }
	public String getName() {
		return name;
	}
}
