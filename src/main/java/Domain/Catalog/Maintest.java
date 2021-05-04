package Domain.Catalog;

import java.util.Date;

public class Maintest {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        catalog.createProduction(1,new Date(21,05,01),"Batman vs Superman");
        catalog.getProduction(1).addToProduction(new Credit("Superman",new Person("Joachim","Baumann",42606930,1)));
        catalog.getProduction(1).addToProduction(new Credit("Lex Luther",new Person("Phillip","Vincent",12345678,2)));

        System.out.println(catalog.getProduction(1));
        System.out.println(catalog.getProduction(1).getProductionName());
        System.out.println(catalog.getProduction(1).getCreditList().get(2));

    }


}
