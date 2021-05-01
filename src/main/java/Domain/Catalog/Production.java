package Domain.Catalog;

import java.util.ArrayList;
import java.util.Date;

public class Production {

    private int productionID;
    private Date releaseDate;
    private String productionName;
    private ArrayList<Credit> creditList;

    public Production(int productionID, Date releaseDate, String productionName) {
        this.productionID = productionID;
        this.releaseDate = releaseDate;
        this.productionName = productionName;
        this.creditList = new ArrayList<>();
    }


    public void addToProduction(String job,Person p){
        creditList.add(new Credit(job,p));

    }

    public void updateProduction(int productionID) {
        throw new UnsupportedOperationException();
    }

    public int getProductionID() {
        return productionID;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getProductionName() {
        return productionName;
    }

    public ArrayList<Credit> getCreditList() {
        return creditList;
    }
}
