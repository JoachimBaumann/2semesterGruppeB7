package Domain;

import Domain.Catalog.Catalog;
import Domain.Catalog.Person;
import Domain.Catalog.Production;
import Persistens.PersistanceHandler;

import java.util.Date;

public class Facade implements CreditManager {

    private PersistanceHandler persistanceHandler = PersistanceHandler.getInstance();
    private Catalog catalog;


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

    @Override
    public void confirmChanges() {

    }



    @Override
    public int addPerson(String mail, String fName, String lName, int phonenumber, String description) {
        int tempID = persistanceHandler.addPerson(mail,fName,lName,phonenumber,description);
        if(tempID != -1) {
            catalog.addPerson(tempID, new Person(mail, fName, lName, phonenumber, tempID, description));
        }

        System.out.println(catalog.getPersons());
        return -1;
    }

    @Override
    public void findProduction() {

    }

    @Override
    public void generateReport(int productionID, Date releaseDate) {

    }

    @Override
    public void saveReport() {

    }
}
