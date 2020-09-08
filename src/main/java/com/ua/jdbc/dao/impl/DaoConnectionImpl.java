package com.ua.jdbc.dao.impl;

import com.ua.jdbc.dao.DaoConnection;
import lombok.SneakyThrows;

import static com.ua.jdbc.database.Operations.*;

public class DaoConnectionImpl implements DaoConnection {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    @SneakyThrows
    public void connectToPostgreSQL() {
        databaseConnection.connectToPostgre();
        System.out.println("Table 'STUDENTS' created.");
        insert(databaseConnection.statementPostgreSQL);
        select(databaseConnection.statementPostgreSQL);
        orderByAge(databaseConnection.statementPostgreSQL);
        countOfRecords(databaseConnection.statementPostgreSQL);
        searchByName(databaseConnection.statementPostgreSQL);
        desiredAge(databaseConnection.statementPostgreSQL);
        databaseConnection.statementPostgreSQL.close();
        databaseConnection.connectionPostgreSQL.close();
    }
}