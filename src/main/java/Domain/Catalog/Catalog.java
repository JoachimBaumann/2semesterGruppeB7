package Domain.Catalog;

import java.util.Date;
import java.util.HashMap;

public class Catalog {
    private HashMap<Integer, Production> productions;
    private HashMap<Integer, Person> persons;

    public Catalog() {
        this.productions = new HashMap<>();
        this.persons = new HashMap<>();
    }

    public HashMap<Integer, Production> getProductionList() {
        return productions;
    }

    public void createProduction(Integer productionID, String date, String productionName, String description) {
        productions.put(productionID, new Production(productionID, date, productionName, description));
    }

    public void getProductions(int productionID, Date releasedate) {
        throw new UnsupportedOperationException();
    }

    public void editPerson(String mail, String fName, String lName, int phoneNumber, int uID, String description) {
        persons.get(uID).setDescription(description);
        persons.get(uID).setfName(fName);
        persons.get(uID).setlName(lName);
        persons.get(uID).setMail(mail);
        persons.get(uID).setPhoneNumber(phoneNumber);

    }


    public void addPerson(int personID, Person person) {
        persons.put(personID, person);
    }

    public void addProduction(int productionID, Production production) {
        productions.put(productionID, production);
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

    public void addToProduction(int productionID, Credit credit) {
        productions.get(productionID).addToProduction(credit);
    }

}



