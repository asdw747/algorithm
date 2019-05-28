package mars.advanced.invocation;

import org.apache.commons.lang.math.RandomUtils;

public class Car implements Moveable {

    @Override
    public void move() {
        try {
            Thread.sleep(RandomUtils.nextInt(1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
