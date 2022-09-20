package com.solvd.onlineshop.services;

public interface IParseXML {
    void readUserXML(String xmlPath);
    void readAddressXML(String xmlPath);
    void readProductXML(String xmlPath);
}
