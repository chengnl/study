package executor.lesson4.invokeany;

import java.util.concurrent.Callable;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月2日 下午3:23:17
 *@version 1.0
 *@Description:验证任务
 */
public class TaskValidator implements Callable<String> {
    private String name;
    private String password;
    private UserValidator validator;
    public TaskValidator(UserValidator validator,String name,String password){
    	this.validator=validator;
    	this.name=name;
    	this.password=password;
    }
	@Override
	public String call() throws Exception {
         if(!this.validator.validate(name, password)){
        	 System.out.printf("%s :the user has not been found \n",this.validator.getName()); 
        	 throw new Exception("error validating user ");
         }
         System.out.printf("%s :the user has been found \n",this.validator.getName()); 
		return this.validator.getName();
	}

}
