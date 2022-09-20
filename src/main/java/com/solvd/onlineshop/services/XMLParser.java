package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Product;
import com.solvd.onlineshop.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class XMLParser implements IParseXML{
    private static String userSchema = "src/main/resources/xsd/user.xsd";
    private static String addressSchema = "src/main/resources/xsd/address.xsd";
    private static String productSchema = "src/main/resources/xsd/product.xsd";
    private static final Logger logger = LogManager.getLogger(XMLParser.class);

    public void readUserXML(String filePath) {
        if(!StAXValidator.validate(filePath,userSchema)) {
            logger.error("The document provided is not valid");
            return;
        }
        List<User> usersList = new ArrayList<>();
        User user = null;
        boolean id = false;
        boolean firstName = false;
        boolean lastName = false;
        boolean middleName = false;
        boolean phone = false;
        boolean email= false;
        boolean password= false;

        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader =
                    xmlInputFactory.createXMLEventReader(new FileReader(filePath));

            while(reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                switch(event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String nodeName = startElement.getName().getLocalPart();

                        if (nodeName.equalsIgnoreCase("user")) {
                            user = new User();
                        } else if (nodeName.equalsIgnoreCase("id")) {
                            id = true;
                        } else if (nodeName.equalsIgnoreCase("first_name")) {
                            firstName = true;
                        } else if (nodeName.equalsIgnoreCase("last_name")) {
                            lastName = true;
                        } else if (nodeName.equalsIgnoreCase("middle_name")) {
                            middleName = true;
                        }
                        else if (nodeName.equalsIgnoreCase("phone")) {
                            phone = true;
                        }
                        else if (nodeName.equalsIgnoreCase("email")) {
                            email = true;
                        }
                        else if (nodeName.equalsIgnoreCase("password")) {
                            password = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (id) {
                            user.setUserId((Integer. parseInt(characters.getData())));
                            id = false;
                        }
                        if(firstName) {
                            user.setFirstName(characters.getData());
                            firstName = false;
                        }
                        if(lastName) {
                            user.setLastName(characters.getData());
                            lastName = false;
                        }
                        if(middleName) {
                            user.setMiddleName(characters.getData());
                            middleName = false;
                        }
                        if(phone) {
                            user.setPhone(characters.getData());
                            phone = false;
                        }
                        if(email) {
                            user.setEmail(characters.getData());
                            email = false;
                        }
                        if(password) {
                            user.setPassword(characters.getData());
                            password = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if(endElement.getName().getLocalPart().equalsIgnoreCase("user")) {
                            usersList.add(user);
                            user= null;
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        usersList.forEach((parsedUser -> logger.info(parsedUser)));
    }

    public void readAddressXML(String filePath) {
        if(!StAXValidator.validate(filePath,addressSchema)) {
            logger.error("The document provided is not valid");
            return;
        }
        List<Address> addressList = new ArrayList<>();
        Address address = null;
        boolean id = false;
        boolean country = false;
        boolean state = false;
        boolean city = false;
        boolean zipcode= false;
        boolean street= false;

        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader =
                    xmlInputFactory.createXMLEventReader(new FileReader(filePath));

            while(reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                switch(event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String nodeName = startElement.getName().getLocalPart();

                        if (nodeName.equalsIgnoreCase("address")) {
                            address = new Address();

                        } else if (nodeName.equalsIgnoreCase("id")) {
                            id = true;
                        } else if (nodeName.equalsIgnoreCase("country")) {
                            country = true;
                        } else if (nodeName.equalsIgnoreCase("state")) {
                            state = true;
                        }
                        else if (nodeName.equalsIgnoreCase("city")) {
                            city = true;
                        }
                        else if (nodeName.equalsIgnoreCase("zipcode")) {
                            zipcode = true;
                        }
                        else if (nodeName.equalsIgnoreCase("street")) {
                            street = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if(id) {
                            address.setaddressId(Integer.parseInt(characters.getData()));
                            id = false;
                        }
                        if(country) {
                            address.setCountry(characters.getData());
                            country = false;
                        }
                        if(state) {
                            address.setState(characters.getData());
                            state = false;
                        }
                        if(city) {
                            address.setCity(characters.getData());
                            city = false;
                        }
                        if(zipcode) {
                            address.setZipcode(characters.getData());
                            zipcode = false;
                        }
                        if(street) {
                            address.setStreet(characters.getData());
                            street = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if(endElement.getName().getLocalPart().equalsIgnoreCase("address")) {
                            addressList.add(address);
                            address= null;
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        addressList.forEach(parsedAddress -> logger.info(parsedAddress));
    }

    public void readProductXML(String filePath) {
        if(!StAXValidator.validate(filePath,productSchema)) {
            logger.error("The document provided is not valid");
            return;
        }
        List<Product> productList = new ArrayList<>();
        Product product = null;
        boolean id = false;
        boolean name = false;
        boolean price = false;
        boolean description = false;
        boolean categoryId= false;
        boolean weight= false;
        boolean inStock= false;
        boolean discountId= false;
        boolean manufacturerId= false;
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader =
                    xmlInputFactory.createXMLEventReader(new FileReader(filePath));

            while(reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                switch(event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String nodeName = startElement.getName().getLocalPart();

                        if (nodeName.equalsIgnoreCase("product")) {
                            product = new Product();
                        } else if (nodeName.equalsIgnoreCase("id")) {
                            id = true;
                        } else if (nodeName.equalsIgnoreCase("name")) {
                            name = true;
                        } else if (nodeName.equalsIgnoreCase("price")) {
                            price = true;
                        } else if (nodeName.equalsIgnoreCase("description")) {
                            description = true;
                        } else if (nodeName.equalsIgnoreCase("category_id")) {
                            categoryId = true;
                        } else if (nodeName.equalsIgnoreCase("weight")) {
                            weight = true;
                        } else if (nodeName.equalsIgnoreCase("in_stock")) {
                            inStock = true;
                        } else if (nodeName.equalsIgnoreCase("discount_id")) {
                            discountId = true;
                        } else if (nodeName.equalsIgnoreCase("manufacturer_id")) {
                            manufacturerId = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if(id) {
                            product.setProductId(Integer.parseInt(characters.getData()));
                            id = false;
                        }
                        if(name) {
                            product.setProductName(characters.getData());
                            name = false;
                        }
                        if(price) {
                            product.setPrice(Double.parseDouble(characters.getData()));
                            price = false;
                        }
                        if(description) {
                            product.setDescription(characters.getData());
                            description = false;
                        }
                        if(categoryId) {
                            product.setCategoryId(Integer.parseInt(characters.getData()));
                            categoryId = false;
                        }
                        if(weight) {
                            product.setWeight(Double.parseDouble(characters.getData()));
                            weight = false;
                        }
                        if(inStock) {
                            product.setInStock(Integer.parseInt(characters.getData()));
                            inStock = false;
                        }
                        if(discountId) {
                            product.setDiscountId(Integer.parseInt(characters.getData()));
                            discountId = false;
                        }
                        if(manufacturerId) {
                            product.setManufacturerId(Integer.parseInt(characters.getData()));
                            manufacturerId = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if(endElement.getName().getLocalPart().equalsIgnoreCase("product")) {
                            productList.add(product);
                            product= null;
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        productList.forEach(parsedProduct -> logger.info(parsedProduct));
    }
}

