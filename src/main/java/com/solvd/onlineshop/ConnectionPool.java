package com.solvd.onlineshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static ConnectionPool pool;


    private static List<Connection> connectionPool = new ArrayList<>(5);

    private ConnectionPool(){
    }

    public static ConnectionPool getInstance(){
        if(pool == null) {
            pool = new ConnectionPool();
            for (int i = 0; i < 5; i++) {
                try {
                    connectionPool.add(createConnection("jdbc:mysql://localhost:3306/OnlineShop", "root", "munif"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return pool;
    }

    public synchronized Connection getConnection() {
        if (connectionPool.isEmpty()) {
            throw new RuntimeException("There are no more available connections!");
            }
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        return connection;
    }

    public synchronized void returnConnection(Connection connection) {
        connectionPool.add(connection);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
