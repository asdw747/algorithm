package mars.design.singleModel;

//静态内部类式
public class SingleModelC {

    private static SingleModelC instance;

    private SingleModelC() {}

    private static class SingletonHolder {
        private static final SingleModelC INSTANCE = new SingleModelC();
    }

    /**
     * 这种方式同样利用了 classloader 机制来保证初始化 instance 时只有一个线程
     * 只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance
     */
    public static SingleModelC getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
