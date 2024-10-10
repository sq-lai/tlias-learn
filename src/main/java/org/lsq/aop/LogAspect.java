package org.lsq.aop;


import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.lsq.mapper.OperateLogMapper;
import org.lsq.pojo.OperateLog;
import org.lsq.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect //切面类
public class LogAspect {

    @Autowired
    HttpServletRequest request;

    @Autowired
    OperateLogMapper operateLogMapper;

    //使用around通知
    @Around("@annotation(org.lsq.annotation.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人ID - 当前登录员工ID.目前是增删改获取id，登录完成后，考虑jwt令牌

        String token = request.getHeader("token");

        Claims claim = JwtUtils.parseJWT(token);

        Integer operateUser = (Integer) claim.get("id");

        //操作时间
        LocalDateTime  operateTime = LocalDateTime.now();



        //操作类名

        String className = joinPoint.getTarget().getClass().getName();



        //操作方法名

        String methodName = joinPoint.getSignature().getName();


        //操作方法参数

        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);


        //调用原始目标方法运行
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();



        //方法返回值

        String returnValue = JSONObject.toJSONString(result);



        //操作耗时
        Long costTime = end - begin;


        //记录操作日志

        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志: {}" , operateLog);
        return result;
    }
}
