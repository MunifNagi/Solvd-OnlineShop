package com.solvd.onlineshop;

import com.solvd.onlineshop.dao.mysql.AddressDAO;
import com.solvd.onlineshop.exception.InvalidDataBaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.IIOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool pool;
    private static List<Connection> connectionPool = new ArrayList<>(5);

    private ConnectionPool() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/sqlconfig.properties"));
            String url = properties.getProperty("url");
            String user = properties.getProperty("username");
            String password = properties.getProperty("password");
            for (int i = 0; i < 5; i++) {
                connectionPool.add(DriverManager.getConnection(url, user, password));
            }
        } catch (SQLException e) {
            logger.error("connection to database failed", e);
        } catch (FileNotFoundException fnfe) {
            logger.error("connection to database failed, could not find properties file", fnfe);
        } catch (IOException ioe) {
            logger.error("connection to database failed, could not load properties file", ioe);
        }
    }


    public static ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public synchronized Connection getConnection() {
        if (connectionPool.isEmpty()) {
            throw new InvalidDataBaseConnection("There are no more available connections!");
        }
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        return connection;
    }

    public synchronized void returnConnection(Connection connection) {
        connectionPool.add(connection);
    }

}
