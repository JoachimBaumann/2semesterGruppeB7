package Domain.Catalog;

import Persistens.PersistanceHandler;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Maintest {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        PersistanceHandler p = PersistanceHandler.getInstance();

/*
    //Alle nedenstående virker og er testet ---


        System.out.println(p.getProduction(1));

        //System.out.println(p.addPerson(new Person("nadian@bla.dk", "Nadia", "Niemier", 12345678, 2, "Her er en person")));
        // System.out.println(p.addPerson(new Person("Oskar@bla.dk", "Oskar", "ThePriest", 12345678, 3, "Her er en priest")));
        System.out.println(p.getPersons());
        System.out.println(p.getCredit(1));
        System.out.println(p.getCredits());
        System.out.println(p.getProduction(2));
        System.out.println(p.getProductions());
        // System.out.println(p.addPerson(new Person("noget@hotmail.dk", "hr","noget", 45678912, 4, "Endnu en person")));
        System.out.println(p.updatePerson("hansen@hotmail.dk","hr","hansen", 45678912, 4, "data er opdateret"));
        System.out.println(p.addProduction(1,"04.05.98","The Matrix"));
        System.out.println(p.updateProduction(1,"04.05.99","The Looner"));


        //Hvis updateProduction ikke virker for jer er det fordi at databasen inde i pgadmin skal laves om,
        //her skal releasedate værer en VARCHAR(255) og ikke DATE

*/
    }

}
