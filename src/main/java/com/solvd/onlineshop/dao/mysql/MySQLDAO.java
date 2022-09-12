package com.solvd.onlineshop.dao.mysql;

public abstract class MySQLDAO {
     static {
         try {
             Class.forName("com.mysql.jdbc.Driver");
         } catch (ClassNotFoundException e) {
             throw new RuntimeException(e);
         }
     }

}