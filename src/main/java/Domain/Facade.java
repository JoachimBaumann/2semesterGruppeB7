package Domain;

import Domain.Catalog.Catalog;
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
    public void findProduction() {

    }

    @Override
    public void generateReport(int productionID, Date releaseDate) {

    }

    @Override
    public void saveReport() {

    }
}
