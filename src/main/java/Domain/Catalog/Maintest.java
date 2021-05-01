package Domain.Catalog;

import java.util.Date;

public class Maintest {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        catalog.createProduction(1,new Date(01052021),"Bat-Jogge returns");
        catalog.getProduction(1).addToProduction(new Credit("Batman",new Person("Joachim","Baumann",42606930,1)));
        catalog.getProduction(1).addToProduction(new Credit("Robin",new Person("Phillip","Vincent",12345678,2)));

        System.out.println(catalog.getProduction(1));

    }


}
