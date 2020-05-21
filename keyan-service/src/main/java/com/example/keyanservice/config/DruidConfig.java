package com.example.keyanservice.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cj
 * @DateTime: 2020/3/17 11:38
 * @Description: TODO
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.druid")
    @Bean
    public DruidDataSource dataSource(Filter starfilter){
        DruidDataSource druidDataSource = new DruidDataSource();
        //添加慢日志功能
        druidDataSource.setProxyFilters(Lists.newArrayList(starfilter));
        return druidDataSource;
    }

    @Bean
    public Filter starFilter(){
        StatFilter filter=new StatFilter();
        filter.setSlowSqlMillis(3000);
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
    }

}
