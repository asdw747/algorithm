package mars.design.SingleModel;

public class SingleModel {

    public static void main(String [] args) {
        SingleObject object = SingleObject.getInstance();
        object.showMessage();
    }

}


class SingleObject {

    private SingleObject() {}

    private static SingleObject instance = new SingleObject();

    public static SingleObject getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }

}