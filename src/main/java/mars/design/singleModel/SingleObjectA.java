package mars.design.singleModel;

//饿汉式
public class SingleObjectA {

    private static SingleObjectA instance = new SingleObjectA();

    private SingleObjectA() {}

    public static SingleObjectA getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }
}
