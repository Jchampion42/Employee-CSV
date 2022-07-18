package com.sparta.AlphaTeam.DataManagement.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory implements AutoCloseable {
    public static Connection connection=null;
    private static final Logger LOG = LogManager.getLogger(ConnectionFactory.class);

    public static Connection getConnection(){
        Properties dbProps = new Properties();

        LOG.info("Loading \"src/main/resources/database.properties\"");
        try {
            dbProps.load(new FileReader("src/main/resources/database.properties"));
            LOG.info("\"src/main/resources/database.properties\" loaded.");
        } catch (IOException e) {
            LOG.error("Problem loading \"src/main/resources/database.properties\"");
        }


        if (connection == null) {
            try {
                LOG.info("Making database connection to " + dbProps.getProperty("db.url"));
                connection = DriverManager.getConnection(dbProps.getProperty("db.url"), dbProps.getProperty("db.username"), dbProps.getProperty("db.password"));
                LOG.info("Database Connection established.");
            } catch (SQLException e) {
                LOG.error("Encountered an error when connecting to database.");

            }
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        LOG.info("Closing database connection.");
        connection.close();
    }
}
