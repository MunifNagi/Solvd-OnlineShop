package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.entities.Product;
import com.solvd.onlineshop.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLParser implements IParseXML {
    private static String userSchema = "src/main/resources/xsd/user.xsd";
    private static String addressSchema = "src/main/resources/xsd/address.xsd";
    private static String productSchema = "src/main/resources/xsd/product.xsd";
    private static String orderSchema = "src/main/resources/xsd/order.xsd";

    private static final Logger logger = LogManager.getLogger(XMLParser.class);


    private static List<User> readUserXML(String filePath) {
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
//                            user.getId((Integer. parseInt(characters.getData())));
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
        return usersList;
    }
    private static List<Order> readOrderXML(String filePath) {
        List<Order> orderList = new ArrayList<>();
        Order order = null;
        boolean id = false;
        boolean totalPrice = false;
        boolean productsQuantity = false;
        boolean date = false;
        boolean shippingAddressId = false;
        boolean orderStatusId= false;
        boolean paymentId= false;
        boolean shipmentId= false;
        DateAdaptor dateAdaptor = new DateAdaptor();


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

                        if (nodeName.equalsIgnoreCase("order")) {
                            order = new Order();
                        } else if (nodeName.equalsIgnoreCase("id")) {
                            id = true;
                        } else if (nodeName.equalsIgnoreCase("total_price")) {
                            totalPrice = true;
                        } else if (nodeName.equalsIgnoreCase("products_quantity")) {
                            productsQuantity = true;
                        } else if (nodeName.equalsIgnoreCase("date")) {
                            date = true;
                        }
                        else if (nodeName.equalsIgnoreCase("shipping_address_id")) {
                            shippingAddressId = true;
                        }
                        else if (nodeName.equalsIgnoreCase("status_id")) {
                            orderStatusId = true;
                        }
                        else if (nodeName.equalsIgnoreCase("payment_id")) {
                            paymentId = true;
                        }
                        else if (nodeName.equalsIgnoreCase("shipment_id")) {
                            shipmentId = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (id) {
                            order.setOrderId((Integer. parseInt(characters.getData())));
                            id = false;
                        }
                        if(totalPrice) {
                            order.setTotalPrice(Double.parseDouble(characters.getData()));
                            totalPrice = false;
                        }
                        if(productsQuantity) {
                            order.setProductsQuantity((Integer. parseInt(characters.getData())));
                            productsQuantity = false;
                        }
                        if(date) {
                            order.setOrderDate(dateAdaptor.unmarshal(characters.getData()));
                            date = false;
                        }
                        if(shippingAddressId) {
                            order.setShippingAddressId((Integer. parseInt(characters.getData())));
                            shippingAddressId = false;
                        }
                        if(orderStatusId) {
                            order.setOrderStatusId((Integer. parseInt(characters.getData())));
                            orderStatusId = false;
                        }
                        if(paymentId) {
                            order.setPaymentId((Integer. parseInt(characters.getData())));
                            paymentId = false;
                        }
                        if(shipmentId) {
                            order.setShipmentId((Integer. parseInt(characters.getData())));
                            shipmentId = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if(endElement.getName().getLocalPart().equalsIgnoreCase("order")) {
                            orderList.add(order);
                            order= null;
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        orderList.forEach((parsedUser -> logger.info(parsedUser)));
        return orderList;
    }

    private static List<Address> readAddressXML(String filePath) {
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
        return addressList;
    }

    private static List<Product> readProductXML(String filePath) {
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
        return productList;
    }

    @Override
    public <T> List<T> readXML(String xmlFile, Class<T> classRef) {
        switch(classRef.getSimpleName()) {
            case "Product":
                if(!validate(xmlFile,productSchema,Product.class)) {
                    logger.error("The document provided is not valid");
                    return new ArrayList<>();
                }
                return (List<T>) readProductXML(xmlFile);

            case "Address":
                if(!validate(xmlFile,addressSchema,Address.class)) {
                    logger.error("The document provided is not valid");
                    return new ArrayList<>();
                }
                return (List<T>) readAddressXML(xmlFile);
            case "User":
                if(!validate(xmlFile,userSchema,User.class)) {
                    logger.error("The document provided is not valid");
                    return new ArrayList<>();
                }
                return (List<T>) readUserXML(xmlFile);
            case "Order":
                if(!validate(xmlFile,orderSchema,Order.class)) {
                    logger.error("The document provided is not valid");
                    return new ArrayList<>();
                }
                return (List<T>) readOrderXML(xmlFile);
            default:
                logger.error("The implementation for the class reference provided have not been implemented");
                return new ArrayList<>();
        }
    }

    @Override
    public <T> boolean validate(String xmlFile, String xsdFile, Class<T> classRef) {
        XMLStreamReader reader = null;
        try {
            reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(xmlFile));
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = null;
            schema = factory.newSchema(new File(xsdFile));
            Validator validator = schema.newValidator();
            validator.validate(new StAXSource(reader));
            logger.info("The Document provided is Valid");
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

