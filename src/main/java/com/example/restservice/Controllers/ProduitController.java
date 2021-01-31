package com.example.restservice.Controllers;


import com.example.restservice.APIError;
import com.example.restservice.Models.Panier;
import com.example.restservice.Models.Produit;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.restservice.Services.ProduitService.getInfos;

/**
 * Classe définissant les endpoints de l'API
 * Son seul attribut et un tableau de panier, memoire, permettant à l'utilisateur de conserver jusqu'à
 * 100 paniers différents par session
 */
@RestController
public class ProduitController {

    Panier[] memoire = new Panier[100];

    /**
     * Endpoint de test pour verifier le format de retour de l'API
     * @return un produit prédéterminé
     */
    @GetMapping("/test")
    public Produit test(){
        return new Produit("Nibbon ni mauvais","Baguette",(long)670,"117",2,9,
                (long)0.18,(long)1.9,(long)3.2,true);
    }

    /**
     * Endpoint de base de la recherche de produits OpenFoodFacts
     * @return string indiquant comment l'api doit être appelée
     */
    @GetMapping("/off")
    public String tuto(){
        return "Pour utiliser l'api, tapez /off/[codeBarre]";
    }

    /**
     * End point affichant un produit à partir d'un code barre
     * @param code Code barre du produit
     * @return Les informations du produit correspondant au code barre
     */
    @RequestMapping(path="/off/{code}",method = RequestMethod.GET)
    public Produit produit(@PathVariable String code){
        return getInfos(code);
    }

    /**
     * Endpoint permettant à l'utilisateur de remplir un panier avec son id et ses produits scannés. Si le panier existe,
     * le produit est ajouté, sinon un nouveau panier est créé et stocké en mémoire.
     * @param id L'id du panier a remplir
     * @param code le code barre du produit a ajouter.
     * @return le panier a afficher
     * @throws APIError si l'id est en dehors des limites, une erreur est levée.
     */
    @RequestMapping(path = "/panier/{id}/{code}", method = RequestMethod.GET)
    public Panier panier(@PathVariable int id, @PathVariable String code) throws APIError {
        if(!code.equals("")) {
            if (id >= 100) throw new APIError(HttpStatus.resolve(404),"Panier indisponible", "APIError");
            Panier p;
            if (memoire[id] != null)
                p = memoire[id];
            else {
                p = new Panier(id);
                memoire[id] = p;
            }
            p.addProduit(getInfos(code));
            return p;
        }
        return memoire[id];
    }
}

