package com.smart;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
//@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement //启动事务管理,自动装配了事务支持
public class Application extends SpringBootServletInitializer{

    @RequestMapping("/") //对应关系：URL<-->Function
    public String index(){
        return "欢迎光临小春论坛！";
    }

    @Bean  //自定义事务管理器，
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    //@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //return super.configure(builder); //自动生成代码
        return builder.sources(Application.class);//看不懂！！！！
    }
}
