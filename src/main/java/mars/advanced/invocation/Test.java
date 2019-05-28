package mars.advanced.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String [] args) {
        Car car = new Car();
        InvocationHandler invocationHandler = new TravelHandler(car);
        Class cls = car.getClass();
        Moveable m = (Moveable)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), invocationHandler);

        m.move();
    }

}
