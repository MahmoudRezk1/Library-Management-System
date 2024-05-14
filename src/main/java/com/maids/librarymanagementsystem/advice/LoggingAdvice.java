package com.maids.librarymanagementsystem.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {

    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value = "execution(* com.maids.librarymanagementsystem.service.BookServiceImpl.*(..))")
    public void bookPointCut(){

    }
    @Pointcut(value = "execution(* com.maids.librarymanagementsystem.service.PatronServiceImpl.*(..))}")
    public void patronPointCut(){

    }

    @Around("bookPointCut() || patronPointCut()")
    public Object applicationLogger(final ProceedingJoinPoint joinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();
        log.info("Method invoked {} : {}() arguments : {}", className, methodName, mapper.writeValueAsString(args));

        Object object = joinPoint.proceed();
        log.info("Method invoked {} : {}() response : {}", className, methodName, object);
        return object;
    }
}
