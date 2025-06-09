package com.aop.basic_aop_example.aop;

import java.time.LocalTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AdviceName;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect // Aspect
@Component
public class AlienAOP {

    // ! I have briefly written code here please check for AOP

    // . -> matches only the exact package specified.
    // .. -> matches the specified package and all its sub-packages.
    // * -> any return type.
    // execution -> when any method is executed.
    // (*) -> Single argument accepted
    // (..) -> Any no. of arguments accepted

    // Inside basic_aop_example all its subpackages and classes has all the methods
    // with any arguments
    @AdviceName(value = "AlienAOP") // Advice
    // PointCut -> execution(* com.aop.basic_aop_example..*.*(..))
    @Before(value = "execution(* com.aop.basic_aop_example..*.*(..))")
    public void beforeEveryMethodInThisPackage(JoinPoint joinPoint) {
        System.out.println(
                "In Package Before Every Method: Request made before to " + joinPoint.getSignature() + " at "
                        + LocalTime.now());
    }

    @Before(value = "execution(* com.aop.basic_aop_example.rest.AlienController.*(..))")
    public void beforeEveryMethodInAlienControllerClass(JoinPoint joinPoint) {
        System.out.println(
                "Before Method: Request made before to " + joinPoint.getSignature() + " at " + LocalTime.now());
    }

    @After(value = "execution(* com.aop.basic_aop_example.rest.AlienController.*(..))")
    public void afterEveryMethodInAlienControllerClass(JoinPoint joinPoint) {
        System.out
                .println("After Method: Request made after to " + joinPoint.getSignature() + " at " + LocalTime.now());
    }

    @Before(value = "execution(* com.aop.basic_aop_example.service.AlienService.*(..))")
    public void beforeEveryMethodInAlienServiceClass(JoinPoint joinPoint) {
        System.out.println(
                "Before Method: Request made before to " + joinPoint.getSignature() + " at " + LocalTime.now());
    }

}
