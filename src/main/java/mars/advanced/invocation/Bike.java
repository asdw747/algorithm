package mars.advanced.invocation;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Bike {

    public void move() {
        try {
            Thread.sleep(RandomUtils.nextInt(1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
