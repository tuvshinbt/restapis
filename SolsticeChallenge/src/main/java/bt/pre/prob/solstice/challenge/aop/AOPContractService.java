/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.aop;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 *
 * @author tuvshuu
 */
@Aspect
@Component
public class AOPContractService {

    private static final Logger logger = LogManager.getLogger(AOPContractService.class);

    @Before("execution(* bt.pre.prob.solstice.challenge.service.*.*(..))")
    public void aopBeforeExam(JoinPoint joinPoint) {
        logger.info("# AOP (1) - BEFORE #  is called on "
                + joinPoint.getSignature().toShortString()
                + " " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* bt.pre.prob.solstice.challenge.service.*.*(..))",
            returning = "retVal")
    public void aopAfterReturningExam(JoinPoint joinPoint, Object retVal) {
        logger.info("# AOP (2) - AFTER RETURN #  is called on "
                + joinPoint.getSignature().toShortString()
                + " " + Arrays.toString(joinPoint.getArgs())
                + " returnValue-" + (retVal != null ? retVal.toString() : null));
    }

    @AfterThrowing("execution(* bt.pre.prob.solstice.challenge.service.*.*(..))")
    public void aopAfterThrowingExam(JoinPoint joinPoint) {
        logger.info("# AOP (3) - AFTER THROWING #  is called on "
                + joinPoint.getSignature().toShortString()
                + " " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("execution(* bt.pre.prob.solstice.challenge.service.*.*(..))")
    public void aopAfterExam(JoinPoint joinPoint) {
        logger.info("# AOP (4) - AFTER #  is called on "
                + joinPoint.getSignature().toShortString()
                + " " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("execution(* bt.pre.prob.solstice.challenge.service.*.*(..))")
    public Object aopAroundExam(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("# AOP (5) - AROUND -> BEFORE #  is called on "
                + pjp.getSignature().toShortString()
                + " " + Arrays.toString(pjp.getArgs()));
        Object retVal = pjp.proceed();
        logger.info("# AOP (5) - AROUND -> AFTER RETURN #  is called on "
                + pjp.getSignature().toShortString()
                + " " + Arrays.toString(pjp.getArgs())
                + " returnValue-" + (retVal != null ? retVal.toString() : null));
        return retVal;
    }
}
