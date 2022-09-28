package com.solvd.cafe.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.io.IOException;

public class ConnectionUtil {
    private static final Logger logger = LogManager.getLogger(ConnectionUtil.class);

    public static Connection getConnection() {
        Connection connection = null;
        try (FileInputStream file = new FileInputStream("src/main/resources/db.properties")) {
            Properties properties = new Properties();
            properties.load(file);

            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException e) {
            logger.info(e.getMessage());
        }
        return connection;
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void close(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void close(Statement statement) {
        try {
            if (statement != null) statement.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

}
