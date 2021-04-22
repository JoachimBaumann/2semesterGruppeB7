package Domain.Catalog;

import java.util.ArrayList;
import java.util.Date;

public class Production {

    private int productionID;
    private Date releaseDate;
    private String productionName;
    private ArrayList<Credit> creditList;

    public Production(int productionID, Date releaseDate, String productionName, Credit credits) {
        this.productionID = productionID;
        this.releaseDate = releaseDate;
        this.productionName = productionName;
        this.creditList = new ArrayList<>();
    }

    public Production getProduction(int productionID){
        throw new UnsupportedOperationException();
    }
    public Production getProduction(String name){
        throw new UnsupportedOperationException();
    }

    /*
    public void addToProduction(int productionID, Credit credit){
        for (Production p: Catalog) { //TODO update catalog to pass a list to Production.
            if(p.productionID == productionID) {
                creditList.add(credit);
            }
        }
    }

     */

    public void updateProduction(int productionID) {
        throw new UnsupportedOperationException();
    }

}
