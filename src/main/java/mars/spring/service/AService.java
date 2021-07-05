package mars.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AService {

    @Autowired
    private BService bService;

    public void test() {
        log.info("test");
    }

}
