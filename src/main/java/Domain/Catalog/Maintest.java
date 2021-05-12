package Domain.Catalog;

import Persistens.PersistanceHandler;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Maintest {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());


        PersistanceHandler p = PersistanceHandler.getInstance();
/*
        System.out.println(p.getPerson(1));
        System.out.println(p.getProduction(1));
        System.out.println(p.deletePerson(1));
        System.out.println(p.addPerson(new Person("nadian@bla.dk", "Nadia", "Niemier", 12345678, 2, "Her er en person")));
        System.out.println(p.getCredit(1));
        System.out.println(p.getCredits());


 */
        p.addProduction(timestamp2,"Langt ned i halsen");
        System.out.println(p.getProduction(2));
    }


}
