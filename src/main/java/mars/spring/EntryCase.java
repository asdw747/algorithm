package mars.spring;

import mars.spring.service.AService;
import mars.spring.config.Config;
import mars.spring.config.Scan;
import mars.spring.service.BService;
import mars.spring.service.NBAspect;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class EntryCase {

    @Test
    public void test() {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Scan.class);
        applicationContext.register(Config.class);
        applicationContext.refresh();

        AService aService = (AService)applicationContext.getBean("AService");
        aService.test();

        AService aService1 = (AService)applicationContext.getBean("AService");

        System.out.println(applicationContext.getBean(AService.class));

    }

    @Test
    public void testXml() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("mars/applicationContext.xml");
        applicationContext.refresh();

        AService aService = applicationContext.getBean(AService.class);
        aService.test();

        BService bService = applicationContext.getBean(BService.class);
        bService.test();

        System.currentTimeMillis();
    }

}
