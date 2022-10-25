package com.solvd.onlineshop.services;

import com.solvd.onlineshop.Main;
import com.solvd.onlineshop.exception.InvalidDataBaseConnection;
import com.solvd.onlineshop.exception.InvalidDateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdaptor extends XmlAdapter<String, Date> {
    private static final Logger logger = LogManager.getLogger(DateAdaptor.class);
    private static final String CUSTOM_FORMAT_STRING = "yyyy-MM-dd";
    @Override
    public Date unmarshal(String s) {
        try {
            return new SimpleDateFormat(CUSTOM_FORMAT_STRING).parse(s);
        } catch (ParseException e) {
            logger.error("Couldn't paras Date, check Date Format.");
            throw new InvalidDateException();
        }
    }

    @Override
    public String marshal(Date date) {
        return new SimpleDateFormat(CUSTOM_FORMAT_STRING).format(date);
    }

    public static Date StringToDate(String s) {

        Date result = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return result;
    }
}
