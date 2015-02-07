package reflection.lesson10.proxy.cglib;
/**
 *@author chengnl
 *@date 2015年2月7日 下午11:13:02
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		IWorkCglib cglib=new IWorkCglib();
		IWorkImpl impl = (IWorkImpl) cglib.getInstance(new IWorkImpl());
		impl.see();

	}

}
