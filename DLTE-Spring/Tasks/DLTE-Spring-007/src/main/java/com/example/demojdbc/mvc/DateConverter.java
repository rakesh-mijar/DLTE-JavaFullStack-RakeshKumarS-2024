//package com.example.demojdbc.mvc;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class DateConverter implements Converter<String, Date> {
//    private SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
//
//
//    @Override
//    public Date convert(String source) {
//        try{
//            return dateFormat.parse(source);
//        } catch (ParseException e) {
//            throw new IllegalArgumentException("Invalid date format");
//        }
//    }
//}
