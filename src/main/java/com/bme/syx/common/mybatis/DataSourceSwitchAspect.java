package com.bme.syx.common.mybatis;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 数据库切换
 * @author yutyi
 */
@Component
@Aspect
@Order(-100) //这是为了保证AOP在事务注解之前生效,Order的值越小,优先级越高
public class DataSourceSwitchAspect {

    @Pointcut("execution(* com.bme.syx.*.mysql..*.*(..))")
    private void mysqlAspect() {
    }

    @Pointcut("execution(* com.bme.syx.*.sqlserver..*.*(..))")
    private void sqlServerAspect() {
    }

    @Pointcut("execution(* com.bme.syx.*.tidb..*.*(..))")
    private void tiDbAspect() {
    }

    @Before( "mysqlAspect()" )
    public void mysql() {
        System.out.println("切换到mysql 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db1);
    }

    @Before("sqlServerAspect()" )
    public void sqlServer () {
        System.out.println("切换到sqlServer 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db2);
    }

    @Before("tiDbAspect()" )
    public void tiDb () {
        System.out.println("切换到tib 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db3);
    }
}
