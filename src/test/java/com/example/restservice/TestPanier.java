package com.example.restservice;

import com.example.restservice.Models.Panier;
import com.example.restservice.Models.Produit;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestPanier {
    @Test
    public void verifPanier() {
        Produit prod1 = new Produit("test", "test", (long) 135, "117", 0, 0, 0, 0, 0, true);
        Produit prod2 = new Produit("test", "test", (long) 3400, "42", 11, 46, 1, 0.5, 1.5, true);
        Produit prod3 = new Produit("test", "test", (long) 1340, "1", 4, 18, 0.36, 4.7, 8, true);

        assertEquals(0, prod1.getScore());
        assertEquals(40, prod2.getScore());
        assertEquals(4, prod3.getScore());

        Panier pVide = new Panier(0);
        assertTrue(pVide.getNbProduits()==0 && pVide.getScore()==0 && pVide.getId()==0);

        Panier p = new Panier(0);

        p.addProduit(prod1);
        p.addProduit(prod2);
        p.addProduit(prod3);

        assertEquals(3,p.getNbProduits());
        assertEquals(15,p.getScore());
        assertEquals(0,p.getId());

        ArrayList<Produit> list = new ArrayList<Produit>();
        list.add(prod1);
        list.add(prod2);
        list.add(prod3);

        assertEquals(p.getPanier(),list);
    }
}
