package com.felipeinocencio.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    /** PostgreSQL */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");

            /** Localhost */
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/feliperichter", "feliperichter", "552178");

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
