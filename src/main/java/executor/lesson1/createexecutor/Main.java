package executor.lesson1.createexecutor;

import java.util.Date;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月1日 下午3:56:53
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		Server  server  = new Server();   
		for(int i=0;i<100;i++){
        	  server.executeTask(new Task(new Date(),"task"+i));
          }
		server.endServer();
	}

}
