package com.maxi.pago.city.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class GenericDAO {

    @Autowired
    private Environment env;

    public GenericDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        String dabaseUrl =  env.getProperty("database.url");

        return DriverManager.getConnection(dabaseUrl, env.getProperty("database.user"), env.getProperty("database.password"));
    }

}
