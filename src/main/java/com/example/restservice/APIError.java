package com.example.restservice;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * Classe d'erreur pouvant être lancée lors de problèmes liés à l'accès aux données de l'API
 */
public class APIError extends Throwable {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public APIError(HttpStatus status, String message, String error) {
        super(message);
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
