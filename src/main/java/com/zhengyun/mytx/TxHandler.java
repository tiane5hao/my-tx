package com.zhengyun.mytx;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务处理类
 * Created by 听风 on 2018/2/10.
 */
public class TxHandler {

    private boolean isNessary;

    private ProceedingJoinPoint pjp;

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ProceedingJoinPoint getPjp() {
        return pjp;
    }

    public void setPjp(ProceedingJoinPoint pjp) {
        this.pjp = pjp;
    }

    public void setNessary(boolean nessary) {
        isNessary = nessary;
        if(isNessary){
            TransactionSynchronizationManager.setActualTransactionActive(true);
            TransactionSynchronizationManager.initSynchronization();
        }
    }

    public void openTxIfNessary()throws SQLException{
        if(!isNessary){
            return;
        }
        connection.setAutoCommit(false);
        System.out.println("开启事务");
    }

    public void commitTxIfNessary() throws SQLException {
        if(!isNessary){
            return;
        }
        connection.commit();
    }

    public void rollbackIfNessary()  {
        if(!isNessary){
            return;
        }
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
