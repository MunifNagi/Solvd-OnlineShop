package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.exception.InvalidDataBaseConnection;

public abstract class MySQLDAO {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new InvalidDataBaseConnection(e);
        }
    }

}
