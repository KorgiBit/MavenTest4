package org.example;

import jakarta.servlet.ServletContext;

import java.sql.*;

public class DB {
    private static Connection connection;
    private static final String database = "AutoSaloon.db";
    private static final String carCatalog = "carCatalog";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void openConnection(ServletContext context) throws SQLException {
        String absolutePathToDB = context.getRealPath("/WEB-INF/classes/AutoSaloon.db");
        String connectionString = "jdbc:sqlite:" + absolutePathToDB;

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(connectionString);
        }
    }
    static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    static void addCar(String carMarkName, String carModelName, String year, int price) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Database connection is not established.");
        }
        String SQL = "INSERT INTO " + carCatalog + " (carMarkName, carModelName, year, price) VALUES (?, ?, ?, ?)";


        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, carMarkName);
        preparedStatement.setString(2, carModelName);
        preparedStatement.setString(3, year);
        preparedStatement.setInt(4, price);

        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            System.out.format("Добавлен автомобиль с ID: %d",
                    generatedKeys.getLong(1));
        }
    }
}
