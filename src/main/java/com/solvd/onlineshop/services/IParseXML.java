package com.solvd.onlineshop.services;

import java.util.List;

public interface IParseXML {
    <T> List<T> readXML(String filePath, Class<T> classRef);

    <T> boolean validate(String filePath, String schemaPath, Class<T> classRef);
}
