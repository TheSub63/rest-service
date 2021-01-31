package com.example.restservice;

import com.example.restservice.Models.Produit;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestProduit {
    @Test
    public void verifScore() {
        Produit testProduit = new Produit("test", "test", (long) 135, "117", 0, 32, 0, 10, 10, true);
        assertTrue(testProduit.getClasse().equals("Trop Bon") && testProduit.getColor().equals("green"));

        assertEquals(testProduit.getId(),Produit.compteur);
        assertEquals(testProduit.getName(),"test");
        assertEquals(testProduit.getBarcode(),"117");
    }

    @Test
    public void verifInfos(){
        Produit testProduit = new Produit("test", "test", (long) 4000, "117", 5, 0, 0, 0, 0, true);
        assertEquals(testProduit.getId(),Produit.compteur);
        assertEquals(testProduit.getName(),"test");
        assertEquals(testProduit.getBarcode(),"117");

        Produit test2 = new Produit("", "Baguette", (long) 135, "118", 0, 0, 0, 0, 0, true);
        assertEquals(test2.getId(),Produit.compteur);
        assertEquals(test2.getName(),"Baguette");
    }
}
