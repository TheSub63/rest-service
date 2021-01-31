package com.example.restservice.Models;

/**
 * Classe représentant un produit OpenFoodFact pour notre api. Il est composé de :
 *      - son nom, soit le nom générique soit le nom français
 *      - une valeur d'energie en kj
 *      - un code barre
 *      - un score, calculé par l'Evaluateur
 *      - une classe et une couleur, dépendantes du score
 *      - des valeurs nutritionelles (gras, sucre, sel, fibres et proteines)
 *      - un booleen faux si le produit n'a pas de valeurs nutritionelles renseignées
 */
public class Produit {

    public static int compteur = 0;

    private int id;
    private String generic_name;
    private Long energy;
    private String barcode;
    private int score;
    private String classe;
    private String color;
    private double graisses;
    private double sucres;
    private double sel;
    private double fibres;
    private double proteine;
    private boolean comptePourScorePanier;


    public Produit(String name, String french_name, Long energy, String code, double g, double suc, double sel, double f,
                   double p, boolean ok) {
        generic_name = name;
        if(generic_name.equals("\"\"") || generic_name.equals("")) generic_name = french_name.replace("\"","");
        this.energy = energy;/*(long)4.184; //kcal to kj*/
        barcode = code;
        graisses = g;
        sucres = suc;
        this.sel = sel;
        fibres = f;
        proteine = p;

        compteur++;
        id=compteur;

        Evaluateur e = new Evaluateur();
        score = e.getScore(this);
        setClasse(score);

        comptePourScorePanier = ok;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.generic_name;
    }

    public String getBarcode(){
        return barcode;
    }

    public Long getEnergy() {
        return this.energy;
    }

    public String getClasse() {
        return classe;
    }

    public String getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }

    public double getFat() {
        return this.graisses;
    }

    public double getSel() {
        return this.sel;
    }

    public double getSuc() {
        return this.sucres;
    }

    public double getFib() {
        return this.fibres;
    }

    public double getPro() {
        return this.proteine;
    }

    public boolean isComptePourScorePanier() {
        return comptePourScorePanier;
    }

    private void setClasse(int score)
    {
        if(score>18)
        {
            color="red";
            classe="Degueu";
        }
        else if(score>10)
        {
            color="orange";
            classe="Mouai";
        }
        else if(score>2)
        {
            color="yellow";
            classe="Mangeable";
        }
        else if(score>-1)
        {
            color="light green";
            classe="Bon";
        }
        else
        {
            color="green";
            classe="Trop Bon";
        }
    }
}