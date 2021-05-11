package Domain.Catalog;

import Persistens.PersistanceHandler;

import java.util.Date;

public class Maintest {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();


        PersistanceHandler p = PersistanceHandler.getInstance();

        System.out.println(p.getPerson(1));
        System.out.println(p.getProduction(1));

    }


}
