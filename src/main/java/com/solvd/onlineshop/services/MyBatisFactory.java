package com.solvd.onlineshop.services;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisFactory{
    private static SqlSessionFactory sqlSessionFactory = null;

    public MyBatisFactory() {
    }
    public static SqlSessionFactory getSessionFactory(){
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        return sqlSessionFactory;
    }
}
