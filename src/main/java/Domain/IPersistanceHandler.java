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
    public void searchProduction(String name);
    public boolean updateProduction(int productionID, String releaseDate, String productionName);
    public boolean deleteProduction(int productionID);
    public boolean addProduction(int productionID, String releaseDate, String productionName);

    //Credit
    public List<Credit> getCredits();
    public Credit getCredit(int creditID);
    public boolean updateCredit(int creditID, String jobTitle);
    public boolean deleteCredit(int creditID);
    public boolean addCredit(int personID, String JobTitle);


    //Person
    public List<Person> getPersons();
    public Person getPerson(int personID);
    public boolean updatePerson(String mail, String fName, String lName, int phoneNumber, int uID, String description);
    public boolean deletePerson(int personID);
    public boolean addPerson(Person person);



}
