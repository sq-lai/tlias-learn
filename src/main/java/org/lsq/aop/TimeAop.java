package org.lsq.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect //aop类
@Slf4j
public class TimeAop {

    @Around("execution(* org.lsq.service.*.*(..))")
    public Object Time(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        log.info(joinPoint.getSignature() + "运行时长" + elapsedTime + "ms");
        return result;
    }

}
