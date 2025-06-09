package com.aop.basic_aop_example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.aop.basic_aop_example.model.Alien;

@Aspect
@Component
public class CustomAOP {

    // Shown below two ways both are correct

    // ! ==================> WAY 1 ( Separating Pointcut, Advice and LINKING BOTH )

    // * POINTCUT
    @Pointcut("execution(* com.aop.basic_aop_example.service.AlienService.registerAlien(*))")
    public void registerMethod() {
    }

    // * ADVICE ( @Before(<Pointcut_Exp>) )
    @Before("registerMethod()") // Pointcut applied to Advice
    public void beforeRegister(JoinPoint joinPoint) { // Using joinpoint we can get arguments before it is going into
                                                      // method and do what we want
        Alien alien = (Alien) joinPoint.getArgs()[0]; // Get the method argument
        System.out.println("alien is u know:::" + alien);
        System.out.println("Before Alien registered");
    }

    // ! ==================> WAY 2 ( Embedding Pointcut in Advice )

    /*
     * @Before(value=
     * "execution(*com.aop.basic_aop_example.service.AlienService.registerAlien(*))")
     * public void beforeRegister(JoinPoint joinPoint) {
     * Object alien = (Alien) joinPoint.getArgs(); // Get the method argument
     * System.out.println("alien is :::" + alien);
     * System.out.println("Before Alien registered");
     * }
     */

}
