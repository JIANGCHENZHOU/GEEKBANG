package com.rexxar.week05.pojo;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class School {
    
    @Autowired
    private HikariDataSource dataSource;

    public void queryStudent100() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //使用 JDBC 原生接口，实现数据库的增删改查操作。
            // String url = "jdbc:mysql://localhost:3306/test?user=root&password=123456";
            // Connection con = DriverManager.getConnection(url);

            //Hikari datasource
            Connection con = dataSource.getConnection();

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Student where ID = '100'");

            //PrepareStatement方式
            // PreparedStatement ps = con.prepareStatement("select * from Student where ID=?");
            // ps.setString(1, "100");
            // ResutSet resultSet = ps.executeQuery();

            while(resultSet.next()) {
                Student student = new Student( Integer.parseInt(resultSet.getString("id")), resultSet.getString("name"));
                System.out.println(student);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
