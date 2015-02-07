利用Java反射机制你可以在运行期动态的创建接口的实现。java.lang.reflect.Proxy类就可以实现这一功能。这个类的名字（译者注：Proxy意思为代理）就是为什么把动态接口实现叫做动态代理。动态的代理的用途十分广泛，比如数据库连接和事物管理（transaction management）还有单元测试时用到的动态mock对象以及AOP中的方法拦截功能等等都使用到了动态代理。


创建代理
==
你可以通过使用Proxy.newProxyInstance()方法创建动态代理。newProxyInstance()方法有三个参数：
1、类加载器（ClassLoader）用来加载动态代理类。
2、一个要实现的接口的数组。
3、一个InvocationHandler把所有方法的调用都转到代理上。


常见用例
==
动态代理常被应用到以下几种情况中

数据库连接以及事物管理
单元测试中的动态Mock对象
自定义工厂与依赖注入（DI）容器之间的适配器
类似AOP的方法拦截器


数据库连接以及事物管理
==

Spring框架中有一个事物代理可以让你提交/回滚一个事物。它的具体原理在 Advanced Connection and Transaction Demarcation and Propagation一文中有详细描述，所以在这里我就简短的描述一下，方法调用序列如下：

<code java>
web controller --> proxy.execute(...);
  proxy --> connection.setAutoCommit(false);
  proxy --> realAction.execute();
    realAction does database work
  proxy --> connection.commit();

</code>

自定义工厂与依赖注入（DI）容器之间的适配器
==

依赖注入容器Butterfly Container有一个非常强大的特性可以让你把整个容器注入到这个容器生成的bean中。但是，如果你不想依赖这个容器的接口，这个容器可以适配你自己定义的工厂接口。你仅仅需要这个接口而不是接口的实现，这样这个工厂接口和你的类看起来就像这样：

<code java>

public interface IMyFactory {
  Bean   bean1();
  Person person();
  ...
}

</code>



<code java>

public class MyAction{

  protected IMyFactory myFactory= null;

  public MyAction(IMyFactory factory){
    this.myFactory = factory;
  }

  public void execute(){
    Bean bean = this.myFactory.bean();
    Person person = this.myFactory.person();
  }

}

</code>

当MyAction类调用通过容器注入到构造方法中的IMyFactory实例的方法时，这个方法调用实际先调用了IContainer.instance()方法，这个方法可以让你从容器中获取实例。这样这个对象可以把Butterfly Container容器在运行期当成一个工厂使用，比起在创建这个类的时候进行注入，这种方式显然更好。而且这种方法没有依赖到Butterfly Container中的任何接口。

类似AOP的方法拦截器
==

Spring框架可以拦截指定bean的方法调用，你只需提供这个bean继承的接口。Spring使用动态代理来包装bean。所有对bean中方法的调用都会被代理拦截。代理可以判断在调用实际方法之前是否需要调用其他方法或者调用其他对象的方法，还可以在bean的方法调用完毕之后再调用其他的代理方法。