package com.example.api.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages="com.example.api.service.mapper")
@EnableTransactionManagement
public class DataSourceConfig {

     @Autowired
     private DataSourceProperties properties;

     @Bean(destroyMethod="close")
     public DataSource dataSource() {
    	 HikariConfig config = new HikariConfig();
         config.setMaximumPoolSize(5); //나중에 변경확인
         config.setDriverClassName(properties.getDriverClassName());
         config.setJdbcUrl(properties.getUrl());
         config.setUsername(properties.getUsername());
         config.setPassword(properties.getPassword());

         config.setPoolName("api-Common-HikariCP-Pool");

//         config.addDataSourceProperty("useServerPrepStmts", "true");
//         config.addDataSourceProperty("cachePrepStmts", "true");
//         config.addDataSourceProperty("prepStmtCacheSize", "250");
//         config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

         return new HikariDataSource(config);
     }

     @Bean
     public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {

    	 SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
    	 sqlSessionFactory.setDataSource(ds);
         //Mapper 설정
         sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sqlmap/*Sql.xml"));
         //Config 설정
         sqlSessionFactory.setConfigLocation( new PathMatchingResourcePatternResolver().getResource("classpath:config/mybatis-config.xml"));

    	 return (SqlSessionFactory) sqlSessionFactory.getObject();
     }

     @Bean
     public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {

    	 return new SqlSessionTemplate(sqlSessionFactory);
     }
}
