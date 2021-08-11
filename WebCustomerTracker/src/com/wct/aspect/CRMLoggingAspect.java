package com.wct.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CRMLoggingAspect {
	
	//Set up Logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	//Set up point cut declaration
	@Pointcut("execution(* com.wct.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.wct.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.wct.DAO.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	//add @Before Advice
	@Before("forAppFlow()")
	public void beforeAdvice(JoinPoint theJoinPoint){
		//display the method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====> In @Before, Calling Method: "+theMethod);
		
		//display the arguments to the method
		Object args[] = theJoinPoint.getArgs();
		
		for(Object temp: args) {
			myLogger.info("===> argument: "+ temp);
		}
		
		
	}
	
	
	//add @Afterreturning Advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="result")
	public void afterReturningAdvice(JoinPoint theJoinPoint, Object result){
		//display the method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====> In @afterReturning, from Method: "+theMethod);
		
		//display the arguments from the method
		myLogger.info("===> The data returned: "+ result);
	
	}
	
}
