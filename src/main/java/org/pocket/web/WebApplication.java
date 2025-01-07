package org.pocket.web;

import org.pocket.web.server.Edit;
import org.pocket.web.server.EditImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication(scanBasePackages="org.pocket.web.server")
public class WebApplication {

    public static void main(String[] args) throws SQLException, IOException {
        // 启动优先级最高
        ApplicationContext context = SpringApplication.run(WebApplication.class, args);

        // 从 Spring 容器中获取 Edit 实例
        Edit edit = context.getBean(EditImpl.class);

        // 调用 createNewFile 方法
        System.out.println(edit.readFile("baosha.txt"));
    }
}
