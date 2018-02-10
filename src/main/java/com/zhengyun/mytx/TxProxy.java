package com.zhengyun.mytx;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by 听风 on 2018/2/10.
 */
@Aspect
@Component
public class TxProxy  {

    @Around("execution(* com.zhengyun.service.impl.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint jp) throws Throwable {
        Object result = null;
        TxHandler txHandler = TxFactory.createTxHandler(jp);
        try {
            txHandler.openTxIfNessary();
            Object[] args = jp.getArgs() ;
            result = jp.proceed(args);
            txHandler.commitTxIfNessary();
        }catch (Throwable e){
            txHandler.rollbackIfNessary();
            throw e;
        }
        return result;
    }
}
