package com.bme.syx.common.mybatis;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Mybatis配置类
 * @author yutyi
 */
public class MybatisManyDataSourceConfig {

	@Bean(name = "mysql")
	@ConfigurationProperties(prefix = "spring.datasource.druid.db1")
	public DataSource dataSource1() {

		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "sqlServer")
	@ConfigurationProperties(prefix = "spring.datasource.druid.db2")
	public DataSource dataSource2() {

		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "tiDb")
	@ConfigurationProperties(prefix = "spring.datasource.druid.db3")
	public DataSource dataSource3() {

		return DruidDataSourceBuilder.create().build();
	}

	@Bean
	@Primary
	public DataSource multipleDataSource(@Qualifier("mysql") DataSource mysql, @Qualifier("sqlServer") DataSource sqlServer, @Qualifier("tiDb") DataSource tiDb) {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<>(3);
		targetDataSources.put(DBTypeEnum.db1.getValue(), mysql);
		targetDataSources.put(DBTypeEnum.db2.getValue(), sqlServer);
		targetDataSources.put(DBTypeEnum.db3.getValue(), tiDb);
		dynamicDataSource.setTargetDataSources(targetDataSources);
		dynamicDataSource.setDefaultTargetDataSource(tiDb);
		return dynamicDataSource;
	}

    @Bean("sqlSessionFactory")
    @ConfigurationProperties(prefix = "mybatis")
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(multipleDataSource(dataSource1(), dataSource2(),dataSource3()));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();

    }
}
