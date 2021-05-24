package Domain;

import Domain.Catalog.Production;

import java.util.Date;
import java.util.List;

public interface CreditManager {

    public List<Production> viewProductions();

    public void ViewAllProductions();

    public Production chooseProduction(int productionID);

    public void addCredit(int personID, String jobtitle);

    public int addPerson(String mail, String fName, String lName, int phonenumber, String description);

    public int addProduction(String releaseDate, String title,String description);

    public void findProduction();

    public void generateReport(int productionID, Date releaseDate);

    public void saveReport();

    public void updateCatalog();

    public boolean updateProduction(int productionID, String releaseDate, String productionName,String description);
}

