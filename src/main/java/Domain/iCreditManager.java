package Domain;

import Domain.Catalog.Production;

import java.util.Date;
import java.util.List;

public interface iCreditManager {

    List<Production> viewProductions();

    Production chooseProduction(int productionID);

    void addCredit(int productionID, int personID, String jobrole);

    int addPerson(String mail, String fName, String lName, int phonenumber, String description);

    int addProduction(String releaseDate, String title, String description);

    void findProduction();

    void generateReport(int productionID, Date releaseDate);

    void saveReport();

    void updateCatalog();

    boolean updateProduction(int productionID, String releaseDate, String productionName, String description);

    boolean updateCredit(int creditID, String jobtitle, int productionID);

    public boolean deleteCredit(int creditID);

    boolean deletePerson(int personID);

    boolean deleteProduction(int productionID);
}

