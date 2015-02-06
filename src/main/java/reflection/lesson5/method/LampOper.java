package reflection.lesson5.method;
/**
 *@author chengnl
 *@date 2015年2月7日 上午11:19:18
 *@version 1.0
 *@Description:演示类
 */
public class LampOper {
    public int open(String color){
    	System.out.println("lamp open color="+color);
    	return 0;
    }
    public int close(){
    	System.out.println("lamp close ");
    	return 1;
    }
    private void sleep(){
    	System.out.println("lamp sleep ");
    }
}
