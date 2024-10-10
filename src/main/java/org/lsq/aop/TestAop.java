package org.lsq.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TestAop {

    @Before("@annotation(org.lsq.annotation.AnnotationTest)")
    public void beforeTest(){
        log.info("记录信息！");
    }
}
