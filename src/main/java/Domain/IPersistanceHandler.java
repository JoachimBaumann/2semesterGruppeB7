package Domain;

import Domain.Catalog.Person;
import Domain.Catalog.Production;

import Domain.Catalog.Credit;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IPersistanceHandler {

    //Production
    public void getProductions();
    public Production getProduction(int productionID);
    public boolean setProduction();
    public void searchProduction(String name);
    public void updateProduction();
    public boolean deleteProduction(int productionID);
    public boolean addProduction(Timestamp date, String jobtitle);

    //Credit
    public List<Credit> getCredits();
    public Credit getCredit(int creditID);
    public void updateCredit();
    public boolean deleteCredit(int creditID);
    public void addCredit(int creditID, String JobTitle);


    //Person
    public void getPersons();
    public Person getPerson(int personID);
    public void updatePerson();
    public boolean deletePerson(int personID);
    public boolean addPerson(Person person);



}
