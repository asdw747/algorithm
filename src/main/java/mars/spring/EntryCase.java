package mars.spring;

import mars.spring.service.AService;
import mars.spring.service.ConfigScan;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EntryCase {

    @Test
    public void test() {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ConfigScan.class);
        applicationContext.refresh();

        System.out.println(applicationContext.getBean(AService.class));

    }

}
