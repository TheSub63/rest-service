package com.example.restservice;

import com.example.restservice.Models.Produit;
import com.example.restservice.Services.ProduitService;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestProduitService {
    @Test
    public void verifService() {
        Produit test = ProduitService.getInfos("7613031443338");
        Produit comp = new Produit("NESTLE FITNESS Chocolat Noir Céréales","",(long)1688,
                "7613031443338", 4.6,16.7, 0.71, 8,8.9,true);

        assertEquals(test.getBarcode(),comp.getBarcode());
        assertEquals(test.getClasse(),comp.getClasse());
        assertEquals(test.getScore(),comp.getScore());
        assertEquals(test.getName(),comp.getName());
        assertEquals(test.getColor(),comp.getColor());
        assertEquals(test.getEnergy(),comp.getEnergy());
        assertEquals(test.getFat(),comp.getFat());
        assertEquals(test.getSuc(),comp.getSuc());
        assertEquals(test.getSel(),comp.getSel());
        assertEquals(test.getPro(),comp.getPro());
        assertEquals(test.getFib(),comp.getFib());
    }

    @Test
    public void verifServiceObjetSansValeur() {
        Produit test = ProduitService.getInfos("3398984505657");
        Produit comp = new Produit("Thé noir bio - Fruits rouges - Grenade Framboise","",(long)0,
                "3398984505657", 0,0, 0, 0,0,false);

        assertEquals(test.getBarcode(),comp.getBarcode());
        assertEquals(test.getClasse(),comp.getClasse());
        assertEquals(test.getScore(),comp.getScore());
        assertEquals(test.getName(),comp.getName());
        assertEquals(test.getColor(),comp.getColor());
        assertEquals(test.getEnergy(),comp.getEnergy());
        assertEquals(test.getFat(),comp.getFat());
        assertEquals(test.getSuc(),comp.getSuc());
        assertEquals(test.getSel(),comp.getSel());
        assertEquals(test.getPro(),comp.getPro());
        assertEquals(test.getFib(),comp.getFib());
    }

    @Test
    public void verifServiceObjetSansNutriscore() {
        Produit test = ProduitService.getInfos("75032814");
        Produit comp = new Produit("Corona Extra","",(long)138,
                "75032814", 0,20, 0, 6,10,false);

        assertEquals(test.getBarcode(),comp.getBarcode());
        assertEquals(test.getClasse(),comp.getClasse());
        assertEquals(test.getScore(),comp.getScore());
        assertEquals(test.getName(),comp.getName());
        assertEquals(test.getColor(),comp.getColor());
        assertEquals(test.getEnergy(),comp.getEnergy());
        assertEquals(test.getFat(),comp.getFat());
        assertEquals(test.getSuc(),comp.getSuc());
        assertEquals(test.getSel(),comp.getSel());
        assertEquals(test.getPro(),comp.getPro());
        assertEquals(test.getFib(),comp.getFib());
    }
}
