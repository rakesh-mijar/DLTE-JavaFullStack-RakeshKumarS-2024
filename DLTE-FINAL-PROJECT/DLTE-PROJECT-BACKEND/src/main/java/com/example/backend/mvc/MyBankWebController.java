package com.example.backend.mvc;

import com.project.dao.entities.Customers;
import com.project.dao.security.MyBankCustomers;
import com.project.dao.security.MyBankCustomersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Null;
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

        @RequestMapping(value="/dashboard", method = RequestMethod.GET)
        public String homePage(){
            return "dashboard";
        }

        @GetMapping("/view")
        public String view(){
                return "viewAccounts";
        }
        @GetMapping("/update")
        public String update(){
                return "updateAccounts";
        }

        @GetMapping("/error")
        public String error(){
                return "errorpage";
        }

        @GetMapping("/name")
        @ResponseBody
        public String customerName(){
                try {
                        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                        String name = authentication.getName();
                        MyBankCustomers customer = myBankCustomersService.findByUsername(name);
                        return customer.getCustomerName();
                }catch (NullPointerException exception){
                        return "Error: Customer Name not availbale";
                }
        }
}

