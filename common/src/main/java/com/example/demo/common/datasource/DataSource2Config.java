package com.example.demo.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.demo.dao.persistence2", sqlSessionFactoryRef = "sqlSessionFactory2")
public class DataSource2Config {
    @Value("${spring.datasource.driver-class-name}")
    String driver;
    @Value("${spring.datasource.url2}")
    String url;
    @Value("${spring.datasource.username2}")
    String username;
    @Value("${spring.datasource.password2}")
    String password;
    @Value("${spring.datasource.minIdle}")
    int minIdle;
    @Value("${spring.datasource.maxActive}")
    int maxActive;
    @Value("${spring.datasource.initialSize}")
    int initialSize;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    long timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    long minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    boolean testOnReturn;

//    @Bean(name = "dataSource2")
    @Bean(name = "dataSource2", initMethod = "init", destroyMethod = "close")
    public DataSource dataSource2() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        return druidDataSource;
    }

    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2") DataSource dataSource2) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource2);
        return sessionFactory.getObject();
    }

    @Bean(name = "transactionManager2")
    public DataSourceTransactionManager transactionManager2(@Qualifier("dataSource2") DataSource dataSource2) {
        return new DataSourceTransactionManager(dataSource2);
    }

}