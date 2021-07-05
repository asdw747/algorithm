package mars.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BService {

    @Autowired
    private AService aService;

    public void test() {
        aService.test();
        System.currentTimeMillis();
    }

}
