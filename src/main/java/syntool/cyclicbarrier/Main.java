package syntool.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月20日 下午3:41:19
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Main {
       public static void main(String[] args) {
    	   int fileNum=10;
    	   int userNum=100;
    	   //日志模拟
    	   LogMock  mock  = new LogMock(fileNum, userNum);
    	   //结果集
    	   Result[]  result  = new Result[fileNum];
    	   //汇总
    	   Sum  sum = new Sum(result);
    	   CyclicBarrier  barrier =  new CyclicBarrier(fileNum,sum);
    	   
    	   for(int i=0;i<fileNum;i++){
    		   result[i]=new Result();
    		   //统计
    		   LoginStatistics   stat = new LoginStatistics(barrier,mock.getFiles()[i] ,result[i]);
    		   Thread  thread = new Thread(stat,"thread "+i);
    		   thread.start();
    	   }
	   }
}
