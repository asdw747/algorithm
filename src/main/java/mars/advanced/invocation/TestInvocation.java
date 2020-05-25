package mars.advanced.invocation;

import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestInvocation {

    public static void main(String [] args) {
//        useJDKProxy();

        useCglibProxy();
    }

    private static void useJDKProxy() {
        Car car = new Car();
        InvocationHandler invocationHandler = new CarInvocation(car);
        Class cls = car.getClass();
        Moveable m = (Moveable)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), invocationHandler);
        m.move();
    }

    //TODO 不知为何抛异常了
    private static void useCglibProxy() {
        BikeInterceptor carInterceptor = new BikeInterceptor();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Bike.class);
        enhancer.setCallback(carInterceptor);

        Bike bike = (Bike) enhancer.create();
        bike.move();
    }

}
