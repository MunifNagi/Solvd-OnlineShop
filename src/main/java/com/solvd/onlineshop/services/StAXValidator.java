package com.solvd.onlineshop.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StAXValidator {
    private static final Logger logger = LogManager.getLogger(StAXValidator.class);
    public static boolean validate (String xmlPath, String xsdPath) {

        XMLStreamReader reader = null;
        try {
            reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(xmlPath));
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = null;
            schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StAXSource(reader));
            return true;
        } catch (XMLStreamException e) {
            logger.error("XMLStream error");
        } catch (FileNotFoundException e) {
            logger.error("FNFE error");
        } catch (SAXException e) {
            logger.error("SAXException error",e.getMessage());
        } catch (IOException e) {
            logger.error("IO error");;
        }
        return false;
    }
}
