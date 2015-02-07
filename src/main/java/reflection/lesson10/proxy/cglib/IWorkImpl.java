package reflection.lesson10.proxy.cglib;
/**
 *@author chengnl
 *@date 2015年2月7日 下午10:27:43
 *@version 1.0
 *@Description:测试实现类
 */
public class IWorkImpl{

	public String see() {
		System.out.println("iwork call see method");
		return "see";
	}

}
