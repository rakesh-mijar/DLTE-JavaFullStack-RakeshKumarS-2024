package com.example.backend.mvc;

import com.project.dao.security.MyBankCustomersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ResourceBundle;

@Controller
@RequestMapping("/customer")
public class MyBankWebController {

        @Autowired
        MyBankCustomersService myBankCustomersService;

        Logger logger= LoggerFactory.getLogger(MyBankWebController.class);

        ResourceBundle bundle=ResourceBundle.getBundle("accounts");

        @GetMapping("/")
        public String landing(){
                return "index";
        }

        @RequestMapping(value="/index", method = RequestMethod.GET)
        public String homePage(){
            return "dashboard";
        }
}

