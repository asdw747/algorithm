package mars.design.singleModel;

//懒汉式
public class SingleObjectB {

    private static SingleObjectB instance;

    private SingleObjectB() {}

    /**
     * 懒汉式
     * 优点：第一次调用才初始化，避免内存浪费。
     * 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
     */
    public synchronized static SingleObjectB getSyncInstance() {
        if (instance == null) {
            instance = new SingleObjectB();
        }

        return instance;
    }

    /**
     * 双检锁/双重校验锁
     * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
     */
    public static SingleObjectB getInstance() {
        if (instance == null) {
            synchronized (SingleObjectB.class) {
                if (instance == null) {
                    instance = new SingleObjectB();
                }
            }
        }

        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }

}
