package Domain;

import Domain.Catalog.Catalog;
import Domain.Catalog.Person;
import Domain.Catalog.Production;
import Domain.Users.User;
import Persistens.PersistanceHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Facade implements CreditManager {

    private PersistanceHandler persistanceHandler = PersistanceHandler.getInstance();
    private Catalog catalog = new Catalog();

    @Override
    public void updateCatalog() {
        //update production
        List<Production> tempProduction = persistanceHandler.getProductions();

        catalog.getProductionList().clear();

        for (Production p : tempProduction){
            catalog.getProductionList().put(p.getProductionID(), p);
        }
        //update persons
        List<Person> pers = persistanceHandler.getPersons();

        catalog.getPersons().clear();

        for (Person person:pers) {
            catalog.getPersons().put(person.getuID(), person);
        }


    }

    @Override
    public boolean updateProduction(String productionName, String description, String releaseDate) {
        return false;
    }

    @Override
    public void ViewAllProductions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Production chooseProduction(int productionID) {
        return catalog.getProduction(productionID);
    }

    @Override
    public void addCredit(int personID, String jobtitle) {
    persistanceHandler.addCredit(personID, jobtitle);
    }

    /**
     *
     * @param mail Email
     * @param fName First name
     * @param lName Last name
     * @param phonenumber Phonenumber
     * @param description Description
     * @return Returns uID from Database && returns -1 if error.
     *
     */
    @Override
    public int addPerson(String mail, String fName, String lName, int phonenumber, String description) {
        int tempID = persistanceHandler.addPerson(mail,fName,lName,phonenumber,description);
        if(tempID != -1) {
            catalog.addPerson(tempID, new Person(mail, fName, lName, phonenumber, tempID, description));
        }
        return -1;
    }

    @Override
    public int addProduction(String releaseDate, String productionName, String description) {
        int tempID = persistanceHandler.addProduction(releaseDate,productionName,description);
        if(tempID != -1) {
            catalog.addProduction(tempID,new Production(tempID,releaseDate,productionName, description));
        }

        return -1;
    }

    @Override
    public void findProduction() {

    }

    public Catalog getCatalog() {
        return catalog;
    }

    @Override
    public void generateReport(int productionID, Date releaseDate) {

    }

    @Override
    public void saveReport() {

    }

    @Override
    public List<Production> viewProductions() {
        List<Production> tempList = new ArrayList<>();
        tempList.addAll(catalog.getProductionList().values());
        return tempList;
    }

    public User getUser(String username){
      ResultSet resultSet = persistanceHandler.getLogin(username);

        try {
            resultSet.next();
            User user = new User(resultSet.getString("username"),resultSet.getString("password"),resultSet.getString("role"),resultSet.getInt("userID"));
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

}
