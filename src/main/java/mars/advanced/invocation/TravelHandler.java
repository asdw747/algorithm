package mars.advanced.invocation;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TravelHandler implements InvocationHandler {

    private Object object;

    public TravelHandler(Object o) {
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("汽车出发。。。");
        method.invoke(object);
        long endTime = System.currentTimeMillis();
        System.out.println("汽车停止。。。出行时间：" + (endTime - startTime));
        return null;
    }
}
