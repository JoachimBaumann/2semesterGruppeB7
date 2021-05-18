package Domain;

import Domain.Catalog.Production;

import java.util.Date;

public interface CreditManager {

    public void ViewAllProductions();

    public Production chooseProduction(int productionID);

    public void addCredit(int personID, String jobtitle);

    public void confirmChanges();

    public void findProduction();

    public void generateReport(int productionID, Date releaseDate);

    public void saveReport();
}
