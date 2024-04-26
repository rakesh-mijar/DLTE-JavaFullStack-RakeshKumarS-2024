package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/dash")
    public String dash(){
        return "dash";
    }

    @GetMapping("/active/accounts")
    public String activeAccounts(){
        return "activeAccounts";
    }

    @GetMapping("/update")
    public String update(){
        return "update";
    }
}
