package Domain.Catalog;

import java.util.ArrayList;
import java.util.Date;

public class Catalog {
    private ArrayList<Production> productions;

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
        throw new UnsupportedOperationException();
    }

    public Production getProduction(String name) {
        for (Production p: productions) {
            if(name.equals(p.getProductionName())) {
                return p;
            }
        }
        return null;
    }
}



