package com.example.restservice;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Long.parseLong;

@RestController
public class ProduitController {

    @GetMapping("/produit")
    public Produit produit(@RequestParam(value="name", defaultValue = "test")String name){
        return new Produit();
    }

    @GetMapping("/")
    public Produit welcome(){
        return new Produit();
    }

    @GetMapping("/test")
    public Produit test(){
        return new Produit("Nibbon ni mauvais","Baguette",(long)670,"117",2,9,(float)0.18,(float)1.9,(float)3.2);
    }

    @GetMapping("/off")
    public String tuto(){
        return "Pour utiliser l'api, tapez /off/produit?id=[codeBarre]";
    }

    @GetMapping("/off/produit")
    public Produit produit(@RequestParam(value="id", defaultValue = "0")long id){
        final String uri = "https://world.openfoodfacts.org/api/v0/product/"+id+".json";
        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.getForObject(uri, Object.class);
        ObjectMapper om = new ObjectMapper();
        ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(result);
            ObjectNode on = om.readValue(json, ObjectNode.class);
            if (on.has("status_verbose")) {
                return generer(ow, on);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Produit();
    }

    private Produit generer(ObjectWriter ow, ObjectNode on)
    {
        try{
            String name ="";
            float fiber=0;
            long energy=0;
            if(on.get("product").get("generic_name")!=null) name = ow.writeValueAsString(on.get("product").get("generic_name"));
            if(on.get("product").get("nutriments").get("energy")!=null) energy = Long.parseLong(on.get("product").get("nutriments").get("energy").toPrettyString());
            if(on.get("product").get("nutriscore_data")!=null)
            {
                fiber = Float.parseFloat(on.get("product").get("nutriscore_data").get("fiber").toPrettyString());
                energy = Long.parseLong(on.get("product").get("nutriscore_data").get("energy_value").toPrettyString());
            }
            else fiber = Float.parseFloat(on.get("product").get("nutriments").get("fiber").toPrettyString());
            return new Produit(name,
                    ow.writeValueAsString(on.get("product").get("product_name_fr")),
                    energy,
                    on.get("code").toPrettyString(),
                    Float.parseFloat(on.get("product").get("nutriments").get("saturated-fat").toPrettyString()),
                    Float.parseFloat(on.get("product").get("nutriments").get("sugars").toPrettyString()),
                    Float.parseFloat(on.get("product").get("nutriments").get("salt").toPrettyString()),
                    fiber,
                    Float.parseFloat(on.get("product").get("nutriments").get("proteins").toPrettyString()));
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return new Produit();
    }

}

