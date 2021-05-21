package Domain.Catalog;

import java.util.HashMap;

public class Production {

    private int productionID;
    private String releaseDate;
    private String productionName;
    private HashMap<Integer,Credit> creditList;

    public Production(int productionID, String releaseDate, String productionName) {
        this.productionID = productionID;
        this.releaseDate = releaseDate;
        this.productionName = productionName;
        this.creditList = new HashMap<>();
    }


    public void addToProduction(Credit credit){
        creditList.put(credit.getPerson().getuID(),credit);

    }

    public void updateProduction(int productionID) {
        throw new UnsupportedOperationException();
    }

    public int getProductionID() {
        return productionID;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getProductionName() { return productionName;}

    public HashMap<Integer,Credit> getCreditList() {
        return creditList;
    }

    @Override
    public String toString() {
        return "Production{" +
                "productionID=" + productionID +
                ",\n releaseDate=" + releaseDate +
                ",\n productionName='" + productionName + '\'' +
                ",\n creditList=" + creditList + "\n" +
                '}';
    }
}
