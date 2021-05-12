package Domain;

import Domain.Catalog.Person;
import Domain.Catalog.Production;

import Domain.Catalog.Credit;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IPersistanceHandler {

    //Production
    public List<Production> getProductions();
    public Production getProduction(int productionID);
    public boolean setProduction();
    public void searchProduction(String name);
    public void updateProduction();
    public boolean deleteProduction(int productionID);
    public boolean addProduction(Timestamp date, String jobtitle);

    //Credit
    public List<Credit> getCredits();
    public Credit getCredit(int creditID);
    public boolean updateCredit(int creditID, String jobTitle);
    public boolean deleteCredit(int creditID);
    public boolean addCredit(int creditID, String JobTitle);


    //Person
    public void getPersons();
    public Person getPerson(int personID);
    public boolean updatePerson(String mail, String fName, String lName, int phoneNumber, int uID, String description);
    public boolean deletePerson(int personID);
    public boolean addPerson(Person person);



}
