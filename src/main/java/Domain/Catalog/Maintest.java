package Domain.Catalog;

import Persistens.PersistanceHandler;

import java.util.Date;

public class Maintest {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();


        PersistanceHandler p = PersistanceHandler.getInstance();

        System.out.println(p.getPerson(1));
        System.out.println(p.getProduction(1));
        System.out.println(p.deletePerson(1));
        //System.out.println(p.addPerson(new Person("nadian@bla.dk", "Nadia", "Niemier", 12345678, 2, "Her er en person")));
        System.out.println(p.addPerson(new Person("Oskar@bla.dk", "Oskar", "ThePriest", 12345678, 3, "Her er en priest")));
        System.out.println(p.getPersons());
        System.out.println(p.getCredit(1));
        System.out.println(p.getCredits());

    }


}
