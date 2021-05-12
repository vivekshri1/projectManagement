package com.jrp.pma.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class ApplicationLoggerAspect {
    private final Logger log= (Logger) LoggerFactory.getLogger(this.getClass());
    //location where cross cutting concerns (where) ..* (class, method, everything else)
//    @Pointcut("within(com.jrp.pma.controllers..*)" +
//            ("within(com.jrp.pma.dao..*))"))
    @Pointcut("within(com.jrp.pma.controllers..*)")
    public void identifyPointCut(){

    }

    //JoinPoint - a point during the execution taking place
    //@After
 //   @Before("identifyPointCut()")
//    public void logBefore(JoinPoint jp){
//        log.debug("-------------------Before Method Execution --------------- \n " +
//                "{}.{} () with arguments[s] ={}", jp.getSignature().getDeclaringTypeName()
//        , jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
//
//        log.debug("------------------------------------------------------\n \n ");
//    }

    @Around("identifyPointCut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        log.debug("-------------------Before Method Execution --------------- \n " +
                        "{}.{} () with arguments[s] ={}", jp.getSignature().getDeclaringTypeName()
                , jp.getSignature().getName(), Arrays.toString(jp.getArgs()));

        log.debug("------------------------------------------------------\n \n ");
        Object proceed = jp.proceed();
        log.debug("-------------------After Method Execution --------------- \n " +
                        "{}.{} () with arguments[s] ={}", jp.getSignature().getDeclaringTypeName()
                , jp.getSignature().getName(), Arrays.toString(jp.getArgs()));

        log.debug("------------------------------------------------------\n \n ");

return proceed;

    }

}
