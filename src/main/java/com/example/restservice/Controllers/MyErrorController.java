package com.example.restservice.Controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * Controleur permettant d'afficher l'erreur concernée par une redirection sur la page d'erreur de l'API
 */
@RestController
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return String.format("<html><body><h2>Zut, quelqu'un a mangé cette page!</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception==null? "N/A": exception.getCause().getCause().getMessage());

    }

    /**
     * Override nécessaire par l'implementation d'ErrorController mais méthode dépréciée
     * @return null
     */
    @Override
    public String getErrorPath() {
        return null;
    }
}
