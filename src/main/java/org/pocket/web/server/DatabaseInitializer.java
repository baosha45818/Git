package org.pocket.web.server;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Component
public class DatabaseInitializer {

    @Value("${database.url}")
    private String databaseUrl;

    @PostConstruct
    public void initDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection(databaseUrl);
        System.out.println("成功建立数据库连接" + databaseUrl);
    }
}
