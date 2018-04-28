package com.example.demo.common.util;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Winby
 * @date 2017/6/28  11:25
 * @update
 * @since v1.0.0
 */
@Configuration
public class FlywayUtil {
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.url2}")
    String url2;
    @Value("${spring.datasource.username2}")
    String username2;
    @Value("${spring.datasource.password2}")
    String password2;
    public void migrate(){
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, username, password);
        flyway.setLocations("classpath:popd");
        flyway.repair();
        flyway.migrate();
//        Flyway flyway = new Flyway();
        flyway.setDataSource(url2, username2, password2);
        flyway.setLocations("classpath:popidQuartz");
        flyway.repair();
        flyway.migrate();
    }
    public void clean(){
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, username, password);
        flyway.setLocations("classpath:popd");
        flyway.clean();
    }

    public static void main(String[] args) {
        String url = null;
        String username = null;
        String password = null;

        Properties prop = System.getProperties();
//        Properties prop = new Properties();
        InputStream in = Object.class.getResourceAsStream("/application-mysql.yml");
        try {
            prop.load(in);
            url = prop.getProperty("url").trim();
            username = prop.getProperty("username").trim();
            password = prop.getProperty("password").trim();
            // 创建Flyway实例
            Flyway flyway = new Flyway();

            // 设置数据库
//        flyway.setDataSource("jdbc:mysql://192.168.1.102:3306/mytest", "root", "root");
            flyway.setDataSource(url, username, password);
            flyway.setEncoding("UTF-8");
            flyway.setLocations("classpath:popidQuartz");

            // 开始迁移
//        flyway.info();
        flyway.clean();
//            flyway.repair();
//            flyway.migrate();
            url = prop.getProperty("url2").trim();
            username = prop.getProperty("username2").trim();
            password = prop.getProperty("password2").trim();
            Flyway f = new Flyway();
            f.setDataSource(url, username, password);
            f.setEncoding("UTF-8");
            f.setLocations("classpath:popidQuartz");

            // 开始迁移
//        f.info();
        f.clean();
//            f.repair();
//            f.migrate();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
