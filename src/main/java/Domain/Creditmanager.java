package Domain;

import Domain.Catalog.Catalog;
import Domain.Catalog.Credit;
import Domain.Catalog.Person;
import Domain.Catalog.Production;
import Domain.Users.User;
import Persistens.PersistanceHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Creditmanager implements iCreditManager {

    private final PersistanceHandler persistanceHandler = PersistanceHandler.getInstance();
    private final static Creditmanager INSTANCE = new Creditmanager();
    private Catalog catalog;


    public static Creditmanager getInstance() {
        return INSTANCE;
    }

    private Creditmanager() {
        catalog = new Catalog();
    }

    @Override
    public boolean deleteCredit(int creditID) {
        return false;
    }

    @Override
    public void updateCatalog() {
        //update production
        List<Production> tempProduction = persistanceHandler.getProductions();

        catalog.getProductionList().clear();

        for (Production p : tempProduction) {
            catalog.getProductionList().put(p.getProductionID(), p);
        }
        //update persons
        List<Person> pers = persistanceHandler.getPersons();

        catalog.getPersonsList().clear();

        for (Person person : pers) {
            catalog.getPersonsList().put(person.getuID(), person);
        }

        List<Credit> templist = persistanceHandler.getCredits();
        for (Credit c : templist) {
            catalog.addCreditToProduction(c.getProductionID(), c);

        }


    }

    @Override
    public boolean updateProduction(int productionID, String releaseDate, String productionName, String description) {
        if (!persistanceHandler.updateProduction(productionID, releaseDate, productionName, description)) {
            catalog.getProduction(productionID).setDescription(description);
            catalog.getProduction(productionID).setReleaseDate(releaseDate);
            catalog.getProduction(productionID).setProductionName(productionName);
            return true;
        } else return false;
    }

    @Override
    public boolean deletePerson(int uID) {
        if (!persistanceHandler.deletePerson(uID)) {
            catalog.getPersonsList().remove(uID);
            return true;
        } else return false;
    }

    @Override
    public Production chooseProduction(int productionID) {
        return catalog.getProduction(productionID);
    }

    @Override
    public void addCredit(int productionID, int personID, String jobRole) {
        int tempID = persistanceHandler.addCredit(productionID, personID, jobRole);

        if (tempID != -1) {
            catalog.addCreditToProduction(productionID, new Credit(tempID, jobRole, productionID, personID));
        }
    }

    /**
     * @param mail        Email
     * @param fName       First name
     * @param lName       Last name
     * @param phonenumber Phonenumber
     * @param description Description
     * @return Returns uID from Database && returns -1 if error.
     */
    @Override
    public int addPerson(String mail, String fName, String lName, int phonenumber, String description) {
        int tempID = persistanceHandler.addPerson(mail, fName, lName, phonenumber, description);
        if (tempID != -1) {
            catalog.addPersonToCatalog(tempID, new Person(mail, fName, lName, phonenumber, tempID, description));
        }
        return -1;
    }

    @Override
    public int addProduction(String releaseDate, String productionName, String description) {
        int tempID = persistanceHandler.addProduction(releaseDate, productionName, description);
        if (tempID != -1) {
            catalog.addProductionToCatalog(tempID, new Production(tempID, releaseDate, productionName, description));
        }

        return -1;
    }

    @Override
    public void findProduction() {

    }

    @Override
    public boolean updateCredit(int creditID, String jobtitle, int productionID) {
        if (persistanceHandler.updateCredit(creditID, jobtitle)) {
            catalog.getProduction(productionID).getCreditList().get(creditID).setJobTitle(jobtitle);
            return true;
        } else return false;
    }


    public List<Person> viewPersons() {

        List<Person> templist = new ArrayList<>();
        for (Person p : catalog.getPersonsList().values()) {
            templist.add(p);
        }
        return templist;
    }

    public Person getPerson(int personID) {
        return catalog.getPersonsList().get(personID);
    }

    public Catalog getCatalog() {
        return catalog;
    }

    @Override
    public void generateReport(int productionID, Date releaseDate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveReport() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Production> viewProductions() {
        List<Production> tempList = new ArrayList<>(catalog.getProductionList().values());
        return tempList;
    }

    public User login(String username, String password) {
        ResultSet resultSet = persistanceHandler.getLogin(username);

        try {
            resultSet.next();
            User user = new User(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("role"), resultSet.getInt("userID"));
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return user;
            }

        } catch (SQLException throwables) {
            return null;
        }
        return null;

    }

    @Override
    public boolean deleteProduction(int productionID) {
        if (!persistanceHandler.deleteProduction(productionID)) {
            catalog.getProductionList().remove(productionID);
            return true;
        } else
            return false;
    }
    }



