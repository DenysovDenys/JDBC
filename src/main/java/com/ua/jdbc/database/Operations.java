package com.ua.jdbc.database;

import com.ua.jdbc.dao.impl.DaoConnectionImpl;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.Statement;

public class Operations {
    private static String sql;

    @SneakyThrows
    public void createDB() {
        new DaoConnectionImpl().connectToPostgreSQL();
    }

    @SneakyThrows
    public static void insert(Statement statement) {
        sql = "INSERT INTO students (id,first_name,last_name,age,City)" + "VALUES (1, 'Denys', 'Denysov', 26, 'Kyiv')";
        statement.executeUpdate(sql);
        sql = "INSERT INTO students " + " VALUES (2, 'Andriy', 'Barabash', 23, 'Kyiv')";
        statement.executeUpdate(sql);
        sql = "INSERT INTO students " + " VALUES (3, 'Jack', 'Jackson', 15, 'Moscow')";
        statement.executeUpdate(sql);
        sql = "INSERT INTO students " + " VALUES (4, 'Jay', 'Jayson', 50, 'Moscow')";
        statement.executeUpdate(sql);
        System.out.println("Records inserted:");
    }

    @SneakyThrows
    public static void select(Statement statement) {
        sql = "SELECT id, first_name, last_name, age, City FROM students";
        ResultSet resultSet = statement.executeQuery(sql);
        printResultSet(resultSet);
        System.out.println();
    }

    @SneakyThrows
    public static void countOfRecords(Statement statement) {
        sql = "SELECT COUNT(*) AS total FROM students";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.print("Count of records: ");
        while (resultSet.next()) {
            int count = resultSet.getInt("total");
            System.out.println(count + "\n");
        }
    }

    @SneakyThrows
    public static void searchByName(Statement statement) {
        sql = "SELECT * FROM students WHERE first_name LIKE 'J%'";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("Name starts with 'J': ");
        printResultSet(resultSet);
        System.out.println();
    }

    @SneakyThrows
    public static void orderByAge(Statement statement) {
        sql = "SELECT * FROM students ORDER BY age DESC";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("Order by age Desc:");
        printResultSet(resultSet);
        System.out.println();
    }

    @SneakyThrows
    private static void printResultSet(ResultSet resultSet) {
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int age = resultSet.getInt("age");
            String first = resultSet.getString("first_name");
            String last = resultSet.getString("last_name");
            String city = resultSet.getString("City");

            System.out.print("Id: " + id);
            System.out.print("   first_name: " + first);
            System.out.print(" \tlast_name: " + last);
            System.out.print("\tage: " + age);
            System.out.println("  \tcity: " + city);
        }
    }

    @SneakyThrows
    public static void desiredAge(Statement statement) {
        sql = "DELETE FROM students WHERE age BETWEEN 20 AND 45";
        statement.executeUpdate(sql);

        System.out.println("Records with age between 20 and 45 deleted.");
        countOfRecords(statement);
        System.out.println("Remaining records:");
        select(statement);
    }
}