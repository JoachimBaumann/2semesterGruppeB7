package Domain.Catalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Catalog {
    private HashMap<String,Production> productions = new HashMap<>();

    public void getProductions(int productionID, Date releasedate) {
        throw new UnsupportedOperationException();
    }

    public void editCredit() {
        throw new UnsupportedOperationException();
    }

    public void searchID(String name, int productionID) {
        throw new UnsupportedOperationException();

    }

    public Production getProduction(int productionID) {
        return productions.get(productionID);
    }

    public Production getProduction(String name) {
        throw new UnsupportedOperationException();
    }

    public void addToProduction(int productionID, Credit credit){
        productions.get(productionID).addToProduction(credit);
    }

}



