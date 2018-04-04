package com.liqihua.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = ConfigConstance.DATASOURCE1_BASEPACKS, sqlSessionFactoryRef = "sqlSessionFactory1")
public class DataSourceConfig1 {

    @Value("${datasource.db1.url}")
    private String url;
    @Value("${datasource.db1.username}")
    private String username;
    @Value("${datasource.db1.password}")
    private String password;
    @Value("${datasource.db1.driverClassName}")
    private String driverClass;


    @Bean(name = "dataSource1")
    @Primary
    public DataSource dataSource1() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "transactionManager1")
    @Primary
    public DataSourceTransactionManager transactionManager1() {
        return new DataSourceTransactionManager(dataSource1());
    }


    @Bean(name = "sqlSessionFactory1")
    @Primary
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource1") DataSource dataSource1) throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource1);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ConfigConstance.DATASOURCE1_MAPPER_LOACTION));
        return bean.getObject();
    }




}
