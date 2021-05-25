package Domain;

import Domain.Catalog.Production;

import java.util.Date;
import java.util.List;

public interface CreditManager {

    public List<Production> viewProductions();

    public Production chooseProduction(int productionID);

    public void addCredit(int productionID, int personID, String jobrole);

    public int addPerson(String mail, String fName, String lName, int phonenumber, String description);

    public int addProduction(String releaseDate, String title, String description);

    public void findProduction();

    public void generateReport(int productionID, Date releaseDate);

    public void saveReport();

    public void updateCatalog();

    public boolean updateProduction(int productionID, String releaseDate, String productionName, String description);

    public boolean updateCredit(int creditID, String jobtitle,int productionID);

    public boolean deleteCredit(int creditID);

    public boolean deletePerson(int personID);

    public boolean deleteProduction(int productionID);
}

