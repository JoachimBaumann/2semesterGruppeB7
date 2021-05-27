package Domain.Catalog;

import Domain.Creditmanager;

public class Credit {
    private int creditID;
    private String jobTitle;
    private int productionID;
    private int PersonID;
    Creditmanager creditmanager = Creditmanager.getInstance();

    public Credit(int creditID, String jobTitle, int productionID, int personID) {
        this.creditID = creditID;
        this.jobTitle = jobTitle;
        this.productionID = productionID;
        PersonID = personID;
    }

    public Person getPerson(int personID){
        return creditmanager.getPerson(personID);
    }

    public int getCreditID() {
        return creditID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public int getProductionID() {
        return productionID;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setProductionID(int productionID) {
        this.productionID = productionID;
    }

    public void setPersonID(int personID) {
        PersonID = personID;
    }
}
