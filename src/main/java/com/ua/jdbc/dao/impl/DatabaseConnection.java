package com.ua.jdbc.dao.impl;

import lombok.SneakyThrows;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
    public Connection connectionPostgreSQL;
    public Statement statementPostgreSQL;
    String sqlCreateTable = "CREATE TABLE  students" +
            "(id INTEGER not NULL," +
            "first_name VARCHAR(255)," +
            "last_name VARCHAR(255)," +
            "age INTEGER," +
            "City VARCHAR(255)," +
            "PRIMARY KEY (id))";

    @SneakyThrows
    public void connectToPostgre() {
        System.out.println("Connection to databases...");
        PropertiesConfiguration config = getProperties();
        Class.forName(config.getString("spring.datasource.driver-class-namePostgres"));
        connectionPostgreSQL = DriverManager.getConnection(
                config.getString("spring.datasource.urlPostgres"),
                config.getString("spring.datasource.usernamePostgres"),
                config.getString("spring.datasource.passwordPostgres"));
        statementPostgreSQL = connectionPostgreSQL.createStatement();
        statementPostgreSQL.executeUpdate(sqlCreateTable);
    }

    public PropertiesConfiguration getProperties() throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration();
        config.load("application.properties");
        return config;
    }
}