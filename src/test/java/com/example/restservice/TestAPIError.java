package com.example.restservice;

import org.junit.Test;
import org.springframework.http.HttpStatus;

public class TestAPIError {
    @Test
    public void throwExeptionIdUndefined(){
        try {
            throw new APIError(HttpStatus.resolve(500),"Une erreur est survenue lors d'un appel à l'API","APIError");
        }catch(APIError apie){
            assert(apie.getMessage().contains("API"));
        }
    }
}
