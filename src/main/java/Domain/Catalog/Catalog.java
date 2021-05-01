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
        Production temp = null;
        for (Production p : productions) {
            if (p.getProductionID() == productionID) {
                temp = p;
            }
        }
        return temp;
    }

    public Production getProduction(String name) {
        throw new UnsupportedOperationException();
    }

}



