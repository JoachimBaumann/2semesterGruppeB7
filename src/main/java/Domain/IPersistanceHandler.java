package Domain;

import Domain.Catalog.Person;
import Domain.Catalog.Production;

public interface IPersistanceHandler {

    //Production
    public void getProductions();
    public Production getProduction(int productionID);
    public boolean setProduction();
    public void searchProduction(String name);
    public void updateProduction();
    public void deleteProduction(int productionID);
    public void addProduction();

    //Credit
    public void getCredits();
    public void getCredit(int creditID);
    public void updateCredit();
    public void deleteCredit(int creditID);
    public void addCredit();


    //Person
    public void getPersons();
    public Person getPerson(int personID);
    public void updatePerson();
    public void deletePerson(int PersonID);
    public void addPerson();



}
