package com.example.restservice.Models;

import com.example.restservice.Models.Produit;

/**
 * L'évaluateur est l'objet chargé de calculer le score nutritionel d'un produit. Ce choix a été fait pour avoir un
 * calcul plus accessible qu'une requête en base. Il est ainsi possible de l'étendre sans modifier la base de données.
 * Un offset est utilisé pour résoudre les problèmes d'égalité sans affecter les valeurs
 */
public class Evaluateur {

    double offset = 0.001;

    /**
     * Calcule le score d'un produit
     * @param p le produit dont le score doit être calculé
     * @return le score de ce produit
     */
    public int getScore(Produit p)
    {
        return getNegative(p)-getPositive(p);
    }

    /**
     * Calcule la partie négative du score d'un produit
     * @param p Le produit dont le score doit être calculé
     * @return Le score négatif (points d'énergie, de gras, de sucre et de sel du produit)
     */
    private int getNegative(Produit p)
    {
        int res = 0;
        res = getScoreEnergie(p) + getScoreGras(p) + getScoreSucre(p) + getScoreSel(p);
        return res;
    }

    /**
     * Calcule les points apportés par la quantité de KJ
     * @param p Le produit dont le score doit être calculé
     * @return Le score négatif apporté par les KJ
     */
    private int getScoreEnergie(Produit p)
    {
        int temp;
        temp = (int)((p.getEnergy()-offset)/335);
        if(temp>10) temp = 10;
        return temp;
    }

    /**
     * Calcule les points apportés par la teneur en gras
     * @param p Le produit dont le score doit être calculé
     * @return Le score négatif apporté par le gras
     */
    private int getScoreGras(Produit p)
    {
        int temp;
        temp = (int)(p.getFat()-offset);
        if(temp>10) temp = 10;
        return temp;
    }

    /**
     * Calcule les points apportés par la teneur en sucres
     * @param p Le produit dont le score doit être calculé
     * @return Le score négatif apporté par le sucres
     */
    private int getScoreSucre(Produit p)
    {
        int temp;
        double sucres = p.getSuc()-offset;
        if(sucres<31) temp = (int)(sucres/4.5);
        else {
            if(sucres>45) temp=10;
            else if(sucres>40) temp = 9;
            else if(sucres>36) temp = 8;
            else temp=7;
        }
        return temp;
    }

    /**
     * Calcule les points apportés par la teneur en sel
     * @param p Le produit dont le score doit être calculé
     * @return Le score négatif apporté par le sel
     */
    private int getScoreSel(Produit p)
    {
        int temp;
        temp = (int)((p.getSel()*1000-offset)/90);
        if(temp>10) temp = 10;
        return temp;
    }

    /**
     * Calcule la partie positive du score d'un produit
     * @param p Le produit dont le score doit être calculé
     * @return Le score positif (points de protéine et de fibre du produit)
     */
    private int getPositive(Produit p)
    {
        return getScoreFibre(p) + getScoreProteine(p);
    }

    /**
     * Calcule les points apportés par la teneur en protéine
     * @param p Le produit dont le score doit être calculé
     * @return Le score positif apporté par la protéine
     */
    private int getScoreProteine(Produit p)
    {
        int res = 0;
        double prot = p.getPro();

        if(prot>=1.6)
        {
            if(prot<3.2) res += 1;
            else if(prot<4.8) res += 2;
            else if(prot<6.4) res += 3;
            else if(prot<=8) res += 4;
            else res += 5;
        }
        return res;
    }

    /**
     * Calcule les points apportés par la teneur en fibres
     * @param p Le produit dont le score doit être calculé
     * @return Le score positif apporté par la fibre
     */
    private int getScoreFibre(Produit p)
    {
        double fibres = p.getFib();
        int res = 0;

        if(fibres>=0.9)
        {
            if(fibres<1.9) res = 1;
            else if(fibres<2.8) res = 2;
            else if(fibres<3.7) res = 3;
            else if(fibres<=4.7) res = 4;
            else res = 5;
        }
        return res;
    }
}
