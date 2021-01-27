package com.example.restservice;

public class Evaluateur {

    public int getScore(Produit p)
    {
        return getNegative(p)-getPositive(p);
    }

    private int getNegative(Produit p)
    {
        int res = 0, temp = 0;
        double offset = 0.001;

        res = (int)((p.getEnergy()-offset)/335);
        if(res>10) res = 10;

        temp = (int)(p.getFat()-offset);
        if(temp>10) temp = 10;
        res += temp;


        double sucres = p.getSuc()-offset;
        if(sucres<31) temp = (int)(sucres/4.5);
        else {
            if(sucres>45) temp=10;
            else if(sucres>40) temp = 9;
            else if(sucres>36) temp = 8;
            else temp=7;
        }
        res+=temp;


        temp = (int)((p.getSel()*1000-offset)/90);
        if(temp>10) temp = 10;
        res+=temp;


        return res;
    }

    private int getPositive(Produit p)
    {
        float fibres = p.getFib();
        float prot = p.getPro();
        int res = 0;

        if(fibres>=0.9)
        {
            if(fibres<1.91) res = 1;
            else if(fibres<2.81) res = 2;
            else if(fibres<3.71) res = 3;
            else if(fibres<4.71) res = 4;
            else res = 5;
        }

        if(prot>=1.6)
        {
            if(prot<3.21) res += 1;
            else if(prot<4.81) res += 2;
            else if(prot<6.41) res += 3;
            else if(prot<8.01) res += 4;
            else res += 5;
        }

        return res;
    }
}
