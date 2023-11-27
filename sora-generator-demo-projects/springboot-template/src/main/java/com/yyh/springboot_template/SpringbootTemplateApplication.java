package com.yyh.springboot_template;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
@MapperScan("com.yyh.springboot_template.module.*.dao")
public class SpringbootTemplateApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(SpringbootTemplateApplication.class, args);
        Environment env = application.getEnvironment();
        String port = env.getProperty("server.port");

        log.info("\n----------------------------------------------------------\n\t" +
                "Application is running! Access URLs:\n\t" +
                "Doc文档: \thttp://" + "localhost" + ":" + port + "/doc.html\n" +
                "----------------------------------------------------------");
    }

}
