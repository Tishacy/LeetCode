package offer;

/**
 * 面试 2. 实现 Singleton 模式
 */
public class SingletonPattern_2 {
    public static void main(String[] args) {
        System.out.println(Singleton1.getInstance() == Singleton1.getInstance());
        System.out.println(Singleton2.getInstance() == Singleton2.getInstance());
        System.out.println(Singleton3.getInstance() == Singleton3.getInstance());
        System.out.println(Singleton4.getInstance() == Singleton4.getInstance());
        System.out.println(Singleton5.getInstance() == Singleton5.getInstance());
    }
}

/**
 * 1. 懒汉式 singleton 线程不安全
 *  线程不安全：因为当两个线程同时使用 getInstance 方法时，如果都判断当前
 *  instance 不存在，那么就都会去创建一个对象，从而不是单例。
 */
class Singleton1 {
    private static Singleton1 instance = null;

    private Singleton1() {}

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}

/**
 * 2. 饿汉式 singleton 线程安全
 */
class Singleton2 {
    private static final Singleton2 instance = new Singleton2();

    private Singleton2() {}

    public static Singleton2 getInstance() {
        return instance;
    }
}

/**
 * 3. 懒汉式 singleton 同步锁实现线程安全
 * 由于加锁释放锁过程比较耗时，因此性能不佳
 */
class Singleton3 {
    private static Singleton3 instance = null;

    private Singleton3() {}

    synchronized public static Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}

/**
 * 4. 懒汉式 singleton 双重验证所机制-线程安全
 * 不每次都加锁，而是当 instance == null 的时候才加锁，
 * 解决了 3 中的性能问题，但是代码结构相对繁琐。
 */
class Singleton4 {
    private static Singleton4 instance = null;

    private Singleton4() {}

    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}

/**
 * 5. 懒汉式 singleton 内部静态类-线程安全（推荐）
 */
class Singleton5 {
    private static class InnerSingleton {
        public static Singleton5 instance = new Singleton5();
    }

    private Singleton5() {}

    public static Singleton5 getInstance() {
        return InnerSingleton.instance;
    }
}
