package Domain.Catalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Catalog {
    private HashMap<Integer,Production> productions;
    private HashMap<Integer,Person> persons;

    public Catalog() {
        this.productions = new HashMap<>();
        this.persons = new HashMap<>();
    }

    public void createProduction(Integer productionID,String date,String productionName){
        productions.put(productionID,new Production(productionID,date,productionName));
    }

    public void getProductions(int productionID, Date releasedate) {
        throw new UnsupportedOperationException();
    }

    public void editCredit() {
        throw new UnsupportedOperationException();
    }


    public void searchID(String name, int productionID) {
        throw new UnsupportedOperationException();

    }

    public void addPerson(int personID, Person person) {
        persons.put(personID, person);
    }

    public HashMap<Integer, Person> getPersons() {
        return persons;
    }

    public Production getProduction(int productionID) {
        return productions.get(productionID);
    }

    //ikke færdig
    public Production getProduction(String productionName) {
        return productions.get(productionName);
    }

    public void addToProduction(int productionID, Credit credit){
        productions.get(productionID).addToProduction(credit);
    }

}



