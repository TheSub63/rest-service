package com.example.restservice.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Panier représente une aggrégation de produits et un score moyen, arrondi à l'entier le plus proche
 * Les produits n'ayant pas de valeurs nutritionelles relevées sont considérés comme des produits ignorés et ne sont pas
 * comptés dans le calcul du score de panier
 */
public class Panier {
    private ArrayList<Produit> panier;
    private int id;
    private int nbProduits;
    private int score;
    private int nbProduitsIgnores;

    public Panier(int id){
        nbProduits=0;
        score=0;
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void addProduit(Produit p)
    {
        if(panier == null) panier = new ArrayList<Produit>();
        if(p != null)
        {
            panier.add(p);
            if(!p.isComptePourScorePanier())
                nbProduitsIgnores++;
            int newScore=0;
            for (Produit prod: panier) {
                if(prod.isComptePourScorePanier())
                    newScore+=prod.getScore();
            }
            nbProduits++;
            score = Math.round((float)newScore/(nbProduits-nbProduitsIgnores));
        }
    }

    public int getId() {
        return id;
    }

    public List<Produit> getPanier() {
        return panier;
    }

    public int getNbProduits() {
        return nbProduits;
    }
}
