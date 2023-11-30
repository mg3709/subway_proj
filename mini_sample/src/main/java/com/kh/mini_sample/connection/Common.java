package com.kh.mini_sample.connection;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class Common extends DriverManagerDataSource {
    // 오라클 설정 정보 (JDBC 연결)

    public Common(){
        this.setDriverClassName("com.mysql.cj.jdbc.Driver");
        this.setUrl("jdbc:mysql://localhost:3306/subway");
        this.setUsername("root");
        this.setPassword("gusehd255");
    }

}