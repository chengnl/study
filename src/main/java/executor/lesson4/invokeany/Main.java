package executor.lesson4.invokeany;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月2日 下午3:28:48
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		String  name = "test";
		String password="test";
		UserValidator   ldapValidator = new UserValidator("ldap");
		UserValidator   dbValidator = new UserValidator("db");
		
		List<TaskValidator>  tasks = new ArrayList<TaskValidator>();
		TaskValidator    ldapTask = new TaskValidator(ldapValidator,name,password);
		TaskValidator    dbTask = new TaskValidator(dbValidator,name,password);
		tasks.add(ldapTask);
		tasks.add(dbTask);
		
		ThreadPoolExecutor executor=(ThreadPoolExecutor) Executors.newCachedThreadPool();
		try {
			 String result =executor.invokeAny(tasks);
			 System.out.printf("Main : result : %s \n",result); 
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		System.out.printf("Main : end of the execution  \n"); 
		
	}

}
