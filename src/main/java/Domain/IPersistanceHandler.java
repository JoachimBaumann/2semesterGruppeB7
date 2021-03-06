package Domain;

import Domain.Catalog.Person;
import Domain.Catalog.Production;

import Domain.Catalog.Credit;

import java.sql.ResultSet;
import java.util.List;

public interface IPersistanceHandler {

    //Production
    public List<Production> getProductions();
    public Production getProduction(int productionID);
    public ResultSet searchProduction(String name);
    public boolean updateProduction(int productionID, String releaseDate, String productionName,String description);
    public boolean deleteProduction(int productionID);
    public int addProduction(String releaseDate, String productionName, String description);

    //Credit
    public List<Credit> getCredits();
    public Credit getCredit(int creditID);
    public boolean updateCredit(int creditID, String jobTitle);
    public boolean deleteCredit(int creditID);
    public int addCredit(int productionID,int personID,String jobrole);


    //Person
    public List<Person> getPersons();
    public Person getPerson(int personID);
    public boolean updatePerson(String mail, String fName, String lName, int phoneNumber, int uID, String description);
    public boolean deletePerson(int personID);
    public int addPerson(String mail, String fname, String lname, int phonenumber,String description);



}
