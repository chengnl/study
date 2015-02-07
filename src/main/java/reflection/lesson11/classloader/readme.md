Java允许你在运行期动态加载和重载类，但是这个功能并没有像人们希望的那么简单直接。这篇文章将阐述在Java中如何加载以及重载类。
你可能会质疑为什么Java动态类加载特性是Java反射机制的一部分而不是Java核心平台的一部分。不管怎样，这篇文章被放到了Java反射系列里面而且也没有更好的系列来包含它了。


类加载器
==
所有Java应用中的类都是被java.lang.ClassLoader类的一系列子类加载的。因此要想动态加载类的话也必须使用java.lang.ClassLoader的子类。

一个类一旦被加载时，这个类引用的所有类也同时会被加载。类加载过程是一个递归的模式，所有相关的类都会被加载。但并不一定是一个应用里面所有类都会被加载，与这个被加载类的引用链无关的类是不会被加载的，直到有引用关系的时候它们才会被加载。

类加载体系
==
在Java中类加载是一个有序的体系。当你新创建一个标准的Java类加载器时你必须提供它的父加载器。当一个类加载器被调用来加载一个类的时候，首先会调用这个加载器的父加载器来加载。如果父加载器无法找到这个类，这时候这个加载器才会尝试去加载这个类。

类加载
==
类加载器加载类的顺序如下：
1、检查这个类是否已经被加载。
2、如果没有被加载，则首先调用父加载器加载。
3、如果父加载器不能加载这个类，则尝试加载这个类。

当你实现一个有重载类功能的类加载器，它的顺序与上述会有些不同。类重载不会请求的他的父加载器来进行加载。在后面的段落会进行讲解。

动态类加载
==
动态加载一个类十分简单。你要做的就是获取一个类加载器然后调用它的loadClass()方法。下面是个例子：

<code>

	public class MainClass {
	  public static void main(String[] args){
	    ClassLoader classLoader = MainClass.class.getClassLoader();
	    try {
	        Class aClass = classLoader.loadClass("com.jenkov.MyClass");
	        System.out.println("aClass.getName() = " + aClass.getName());
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	
</code>

动态类重载
==
动态类重载有一点复杂。Java内置的类加载器在加载一个类之前会检查它是否已经被加载。因此重载一个类是无法使用Java内置的类加载器的，如果想要重载一个类你需要手动继承ClassLoader。

在你定制ClassLoader的子类之后，你还有一些事需要做。所有被加载的类都需要被链接。这个过程是通过ClassLoader.resolve()方法来完成的。由于这是一个final方法，因此这个方法在ClassLoader的子类中是无法被重写的。resolve()方法是不会允许给定的ClassLoader实例链接一个类两次。所以每当你想要重载一个类的时候你都需要使用一个新的ClassLoader的子类。你在设计类重载功能的时候这是必要的条件。


自定义类重载
==
在前面已经说过你不能使用已经加载过类的类加载器来重载一个类。因此你需要其他的ClassLoader实例来重载这个类。但是这又带来了一些新的挑战。

所有被加载到Java应用中的类都以类的全名（包名 + 类名）作为一个唯一标识来让ClassLoader实例来加载。这意味着，类MyObject被类加载器A加载，如果类加载器B又加载了MyObject类，那么两个加载器加载出来的类是不同的。看看下面的代码：

<code>

	MyObject object = (MyObject)
	myClassReloadingFactory.newInstance("com.jenkov.MyObject");

</code>

MyObject类在上面那段代码中被引用，它的变量名是object。这就导致了MyObject这个类会被这段代码所在类的类加载器所加载。

如果myClassReloadingFactory工厂对象使用不同的类加载器重载MyObject类，你不能把重载的MyObject类的实例转换（cast）到类型为MyObject的对象变量。一旦MyObject类分别被两个类加载器加载，那么它就会被认为是两个不同的类，尽管它们的类的全名是完全一样的。你如果尝试把这两个类的实例进行转换就会报ClassCastException。
你可以解决这个限制，不过你需要从以下两个方面修改你的代码：
1、标记这个变量类型为一个接口，然后只重载这个接口的实现类。
2、标记这个变量类型为一个超类，然后只重载这个超类的子类。

请看下面这两个例子：

<code>

	MyObjectInterface object = (MyObjectInterface)
	myClassReloadingFactory.newInstance("com.jenkov.MyObject");

</code>

<code>

	MyObjectSuperclass object = (MyObjectSuperclass)
	myClassReloadingFactory.newInstance("com.jenkov.MyObject");
	
</code>

只要保证变量的类型是超类或者接口，这两个方法就可以正常运行，当它们的子类或是实现类被重载的时候超类跟接口是不会被重载的。

为了保证这种方式可以运行你需要手动实现类加载器然后使得这些接口或超类可以被它的父加载器加载。当你的类加载器加载MyObject类时，超类MyObjectSuperclass或者接口MyObjectSuperclass也会被加载，因为它们是MyObject的依赖。你的类加载器必须要代理这些类的加载到同一个类加载器，这个类加载器加载这个包括接口或者超类的类。

类加载/重载示例
光说不练假把式。让我们看看一个简单的例子。下面这个例子是一个类加载器的子类。注意在这个类不想被重载的情况下它是如何把对一个类的加载代理到它的父加载器上的。如果一个类被它的父加载器加载，这个类以后将不能被重载。记住，一个类只能被同一个ClassLoader实例加载一次。
就像我之前说的那样，这仅仅是一个简单的例子，通过这个例子会向你展示类加载器的基本行为。这并不是一个可以让你直接用于设计你项目中类加载器的模板。你自己设计的类加载器应该不仅仅只有一个，如果你想用来重载类的话你可能会设计很多加载器。并且你也不会像下面这样将需要加载的类的路径硬编码（hardcore）到你的代码中。

<code>

  public class MyClassLoader extends ClassLoader{

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class loadClass(String name) throws ClassNotFoundException {
        if(!"reflection.MyObject".equals(name))
                return super.loadClass(name);

        try {
            String url = "file:C:/data/projects/tutorials/web/WEB-INF/" +
                            "classes/reflection/MyObject.class";
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while(data != -1){
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            return defineClass("reflection.MyObject",
                    classData, 0, classData.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

</code>

下面是使用MyClassLoader的例子：
<code>

	public static void main(String[] args) throws
	    ClassNotFoundException,
	    IllegalAccessException,
	    InstantiationException {
	
	    ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
	    MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
	    Class myObjectClass = classLoader.loadClass("reflection.MyObject");
	
	    AnInterface2       object1 =
	            (AnInterface2) myObjectClass.newInstance();
	
	    MyObjectSuperClass object2 =
	            (MyObjectSuperClass) myObjectClass.newInstance();
	
	    //create new class loader so classes can be reloaded.
	    classLoader = new MyClassLoader(parentClassLoader);
	    myObjectClass = classLoader.loadClass("reflection.MyObject");
	
	    object1 = (AnInterface2)       myObjectClass.newInstance();
	    object2 = (MyObjectSuperClass) myObjectClass.newInstance();
	
	}
</code>
