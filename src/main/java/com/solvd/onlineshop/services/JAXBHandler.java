package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.util.List;

public class JAXBHandler implements IParseXML {
    private static final Logger logger = LogManager.getLogger(JAXBHandler.class);

    public <T> void writeXML(T entity, String filePath) {
        GenericClass<T> instance = new GenericClass(entity.getClass());
        Class<T> clazz = instance.getType();
        EntityList<T> entityList = new EntityList<T>();
        entityList.addEntity(entity);
        marshall(entityList.getEntities(), clazz, filePath);
    }

    public <T> List<T> readXML(String xmlPath, Class<T> classRef) {

        Source source = new StreamSource(new File(xmlPath));
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classRef, EntityList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<EntityList> jaxbElement = unmarshaller.unmarshal(source, EntityList.class);
            List<T> entityList = jaxbElement.getValue().getEntities();
            return entityList;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> boolean validate(String xmlFile, String xsdFile, Class<T> classRef) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classRef);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(xsdFile));
            unmarshaller.setSchema(schema);
            unmarshaller.unmarshal(new StreamSource(xmlFile), classRef);
            return true;
        } catch (JAXBException e) {
            logger.error("JAXBException error", e.getMessage());
        } catch (SAXException e) {
            logger.error("SAXException error", e.getMessage());
        }
        return false;
    }


    public <T> void writeXML(List<T> entityList, Class<T> classRef, String xmlFile) {
        marshall(entityList, classRef, xmlFile);
    }

    private static <T> void marshall(List<T> entityList, Class<T> classRef, String xmlFile) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classRef, EntityList.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            String rootElementName = classRef.getSimpleName() + "List";
            JAXBElement<EntityList> jaxbElement = new JAXBElement<EntityList>(new QName(null, rootElementName), EntityList.class, new EntityList<>(entityList));
            marshaller.marshal(jaxbElement, new File(xmlFile));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
