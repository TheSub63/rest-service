package com.example.restservice;

import java.util.Objects;

public class Produit {

    private Long id;
    private String generic_name;
    private Long energy;
    private String barcode;
    private int score;
    private String classe;
    private String color;
    private float graisses;
    private float sucres;
    private float sel;
    private float fibres;
    private float proteine;

    Produit() {}

    Produit(String name, String french_name,Long energy, String code, float g, float suc, float sel, float f, float p) {
        generic_name = name;
        if(generic_name.equals("\"\"") || generic_name.equals("")) generic_name = french_name;
        this.energy = energy;/*(long)4.184; //kcal to kj*/
        barcode = code;
        graisses = g;
        sucres = suc;
        this.sel = sel;
        fibres = f;
        proteine = p;

        Evaluateur e = new Evaluateur();
        score = e.getScore(this);

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

    public Long getId() {
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

    public float getFat() {
        return this.graisses;
    }

    public void setFat(float f) {
        graisses = f;
    }

    public float getSel() {
        return this.sel;
    }

    public void setSalt(float f) {
        sel = f;
    }

    public float getSuc() {
        return this.sucres;
    }

    public float getFib() {
        return this.fibres;
    }

    public float getPro() {
        return this.proteine;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        generic_name = name;
    }

    public void setEnergy(Long Energy) {
        this.energy = energy;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Produit))
            return false;
        Produit f = (Produit) o;
        return Objects.equals(this.id, f.id) && Objects.equals(this.generic_name, f.generic_name)
                && Objects.equals(this.energy, f.energy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.generic_name, this.energy);
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + this.id + ", name='" + this.generic_name + '\'' + ", kcal='" + this.energy + '\'' + '}';
    }
}