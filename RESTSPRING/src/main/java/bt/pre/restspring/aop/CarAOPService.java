/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restspring.aop;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 *
 * @author tuvshuu
 */
public class CarAOPService {

    public void aopBeforeExam(JoinPoint joinPoint) {
        System.out.println("# AOP (1) - BEFORE #  is called on "
                + joinPoint.getSignature().toShortString()
                + " " + Arrays.toString(joinPoint.getArgs()));
    }

    public void aopAfterReturningExam(JoinPoint joinPoint, Object retVal) {
        System.out.println("# AOP (2) - AFTER RETURN #  is called on "
                + joinPoint.getSignature().toShortString()
                + " " + Arrays.toString(joinPoint.getArgs())
                + " returnValue-" + (retVal != null ? retVal.toString() : null));
    }

    public void aopAfterThrowingExam(JoinPoint joinPoint) {
        System.out.println("# AOP (3) - AFTER THROWING #  is called on "
                + joinPoint.getSignature().toShortString()
                + " " + Arrays.toString(joinPoint.getArgs()));
    }

    public void aopAfterExam(JoinPoint joinPoint) {
        System.out.println("# AOP (4) - AFTER #  is called on "
                + joinPoint.getSignature().toShortString()
                + " " + Arrays.toString(joinPoint.getArgs()));
    }

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
