package com.zhengyun.mytx;

import com.zhengyun.util.SpringContextUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * Created by 听风 on 2018/2/10.
 */
public class TxFactory {

    public static TxHandler createTxHandler(ProceedingJoinPoint pjp){
        TxHandler txHandler = new TxHandler();
        try {
            String methodName=pjp.getSignature().getName();
            Class<?> classTarget=pjp.getTarget().getClass();
            Class<?>[] par=((MethodSignature) pjp.getSignature()).getParameterTypes();
            Method objMethod=classTarget.getMethod(methodName, par);

            Business business=objMethod.getAnnotation(Business.class);
            if(business == null){
                txHandler.setNessary(false);
            }else {
                txHandler.setNessary(true);
                txHandler.setPjp(pjp);
                txHandler.setConnection(getConnection());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return txHandler;
    }

    private static Connection getConnection(){
        DataSource dataSource = (DataSource)SpringContextUtil.getBean("dataSource");
        return DataSourceUtils.getConnection(dataSource);
    }
}
