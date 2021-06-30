package mars.spring;

import mars.spring.service.AService;
import mars.spring.config.Config;
import mars.spring.config.Scan;
import org.junit.Test;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class EntryCase {

    @Test
    public void test() {

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(Scan.class);
        applicationContext.register(Config.class);
        applicationContext.refresh();

        System.out.println(applicationContext.getBean(AService.class));

    }

}
