package com.example.restservice;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "Zut, quelqu'un a mangé cette page!";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
