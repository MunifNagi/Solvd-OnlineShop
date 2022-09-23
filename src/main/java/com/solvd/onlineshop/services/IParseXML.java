package com.solvd.onlineshop.services;

import java.util.List;

public interface IParseXML {
    <T> List<T> readXML(String xmlFile, Class<T> classRef);
    <T> boolean validate (String xmlFile, String xsdFile, Class<T> classRef);
}
