package com.liqihua.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
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
@MapperScan(basePackages = ConfigConstance.DATASOURCE2_BASEPACKS, sqlSessionFactoryRef = "sqlSessionFactory2")
public class DataSourceConfig2 {

    @Value("${datasource.db2.url}")
    private String url;
    @Value("${datasource.db2.username}")
    private String username;
    @Value("${datasource.db2.password}")
    private String password;
    @Value("${datasource.db2.driverClassName}")
    private String driverClass;


    @Bean(name = "dataSource2")
    public DataSource dataSource2() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "transactionManager2")
    public DataSourceTransactionManager transactionManager2() {
        return new DataSourceTransactionManager(dataSource2());
    }


    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2") DataSource dataSource2) throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource2);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ConfigConstance.DATASOURCE2_MAPPER_LOACTION));
        return bean.getObject();
    }




}
