package reflection.lesson10.proxy;
/**
 *@author chengnl
 *@date 2015年2月7日 下午10:21:43
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		DyProxy  proxy = new DyProxy(new IWorkImpl());
		IWork iwork=proxy.getProxyObj();
		iwork.see();

	}

}
