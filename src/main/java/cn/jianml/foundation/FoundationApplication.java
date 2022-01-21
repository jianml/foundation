package cn.jianml.foundation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 启动类
 *
 * @author wujian
 * @date 2022年01月17日
 */
@SpringBootApplication
public class FoundationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FoundationApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        System.out.println("服务启动成功，端口:" + environment.getProperty("server.port"));
        System.out.println("enjoy it ☕");
        System.out.println("===========================================");
    }

}
