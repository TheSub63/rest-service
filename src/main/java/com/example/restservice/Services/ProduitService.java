package com.example.restservice.Services;

import com.example.restservice.Models.Produit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.client.RestTemplate;

/**
 * Classe du service qui crée les Produits à partir des requêtes vers l'API OpenFoodFacts
 */
public class ProduitService {
    /**
     * Methode servant a obtenir la data d'une requête et à préparer les translateurs JSON. Si tout se passe bien, la
     * méthode generer est appelée.
     * @param code Code Barre du produit
     * @return le résultat de generer ou null si une erreur survient
     */
    public static Produit getInfos(String code)
    {
        final String uri = "https://world.openfoodfacts.org/api/v0/product/"+code+".json";
        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.getForObject(uri, Object.class);
        ObjectMapper om = new ObjectMapper();
        ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(result);
            ObjectNode on = om.readValue(json, ObjectNode.class);

            if (on.has("status_verbose") &&
                    !on.get("status_verbose").toString().equals("\"no code or invalid code\"")) {
                return generer(ow, on);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Methode récupérant les champs qui nous intéressent pour la construction d'un produit.
     * Les données peuvent être récupérée dans nutriscore_data et/ou dans nutriments suivant les produits, les deux
     * champs sont donc parcourus. Certains produits n'ont aucune données de nutrition (soit des erreurs, soit des cas
     * particuliers, comme le thé). Un booleen est donc utilisé pour repérer dès leur lecture ces produits afin qu'ils
     * ne viennent pas fausser le calcul du score d'un panier.
     * @return Le produit construit avec les informations récupérées ou null si une erreur survient
     */
    public static Produit generer(ObjectWriter ow, ObjectNode on) throws Exception {
        try{
            String name ="", name_fr="";
            double fiber=0, sugar=0, fat=0, prot=0, salt=0;
            boolean ok = true;
            long energy=0;

            if(on.get("product").get("nutriments").has("energy"))
                energy = Long.parseLong(on.get("product").get("nutriments").get("energy").toPrettyString());
            if(on.get("product").has("nutriscore_data"))
            {
                fiber = d(on.get("product").get("nutriscore_data").get("fiber").toPrettyString());
                prot = d(on.get("product").get("nutriscore_data").get("proteins").toPrettyString());
                sugar = d(on.get("product").get("nutriscore_data").get("sugars").toPrettyString());
                fat = d(on.get("product").get("nutriscore_data").get("saturated_fat").toPrettyString());
                energy = Long.parseLong(on.get("product").get("nutriscore_data").get("energy_value").toPrettyString());
                salt = d(on.get("product").get("nutriments").get("salt_100g").toPrettyString());
            }
            else if(on.get("product").has("nutriments"))
            {
                if(on.get("product").get("nutriments").has("fiber"))
                    fiber = d(on.get("product").get("nutriments").get("fiber").toPrettyString());
                if(on.get("product").get("nutriments").has("proteins"))
                    prot = d(on.get("product").get("nutriments").get("proteins").toPrettyString());
                if(on.get("product").get("nutriments").has("saturated-fat"))
                    fat = d(on.get("product").get("nutriments").get("saturated-fat").toPrettyString());
                if(on.get("product").get("nutriments").has("sugars"))
                    sugar = d(on.get("product").get("nutriments").get("sugars").toPrettyString());
                if(on.get("product").get("nutriments").has("salt_100g"))
                    salt = d(on.get("product").get("nutriments").get("salt_100g").toPrettyString());

            }
            else ok = false;

            if((on.get("product").has("product_name"))) name = ow.writeValueAsString(on.get("product")
                    .get("product_name").toString().replace("\"",""));
            if((on.get("product").has("product_name_fr"))) name_fr = ow.writeValueAsString(on.get("product")
                    .get("product_name_fr").toString().replace("\"",""));
            return new Produit(name, name_fr, energy,
                    on.get("code").toPrettyString().replace("\"",""), fat, sugar, salt, fiber, prot, ok);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fonction de conversion en double permettant d'écconomiser quelques caractères
     * @param s La chaine a convertir
     * @return la valeur en double
     */
    private static double d(String s)
    {
        return Double.valueOf(s).doubleValue();
    }
}
