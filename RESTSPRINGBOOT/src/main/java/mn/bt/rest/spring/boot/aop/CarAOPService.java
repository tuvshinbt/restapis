/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.bt.rest.spring.boot.aop;

import java.util.Arrays;
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
public class CarAOPService {

    @Before("execution(* mn.bt.rest.spring.boot.service.*.*(..))")
    public void aopBeforeExam(JoinPoint joinPoint) {
        System.out.println("# AOP (1) - BEFORE #  is called on "
                + joinPoint.getSignature().toShortString()
                + " " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* mn.bt.rest.spring.boot.service.*.*(..))",
            returning = "retVal")
    public void aopAfterReturningExam(JoinPoint joinPoint, Object retVal) {
        System.out.println("# AOP (2) - AFTER RETURN #  is called on "
                + joinPoint.getSignature().toShortString()
                + " " + Arrays.toString(joinPoint.getArgs())
                + " returnValue-" + (retVal != null ? retVal.toString() : null));
    }

    @AfterThrowing("execution(* mn.bt.rest.spring.boot.service.*.*(..))")
    public void aopAfterThrowingExam(JoinPoint joinPoint) {
        System.out.println("# AOP (3) - AFTER THROWING #  is called on "
                + joinPoint.getSignature().toShortString()
                + " " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("execution(* mn.bt.rest.spring.boot.service.*.*(..))")
    public void aopAfterExam(JoinPoint joinPoint) {
        System.out.println("# AOP (4) - AFTER #  is called on "
                + joinPoint.getSignature().toShortString()
                + " " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("execution(* mn.bt.rest.spring.boot.service.*.*(..))")
    public Object aopAroundExam(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("# AOP (5) - AROUND -> BEFORE #  is called on "
                + pjp.getSignature().toShortString()
                + " " + Arrays.toString(pjp.getArgs()));
        Object retVal = pjp.proceed();
        System.out.println("# AOP (5) - AROUND -> AFTER RETURN #  is called on "
                + pjp.getSignature().toShortString()
                + " " + Arrays.toString(pjp.getArgs())
                + " returnValue-" + (retVal != null ? retVal.toString() : null));
        return retVal;
    }
}
