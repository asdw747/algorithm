package mars.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//需要配置开启aop
@Aspect
@Component
@Slf4j
public class NBAspect {

    @Pointcut("execution(* mars.spring.service.*Service.*(..))")
    public void nbService() {
        System.currentTimeMillis();
        //do nothing
    }

    @Around("nbService()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        log.info("print exe time, {}", end-start);
        return result;
    }

}
