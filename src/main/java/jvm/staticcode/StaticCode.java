package jvm.staticcode;
/**
 *@author chengnl
 *@date 2014年12月26日 下午2:27:02
 *@version 1.0
 *@Description:测试
 */
public class StaticCode {
    
    static {
        System.out.println("静态块");
    }
    {
        System.out.println("构造块，在类中定义");
    }

    public StaticCode() {
        System.out.println("构造方法执行");
    }

    public static void main(String[] args) {
        new StaticCode();
        new StaticCode();
    }
}
