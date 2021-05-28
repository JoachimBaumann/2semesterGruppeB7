package Domain.Catalog;

import java.util.HashMap;

public class Production {

    private int productionID;
    private String releaseDate;
    private String productionName;
    private String description;
    private HashMap<Integer,Credit> creditList;

    public Production(int productionID, String releaseDate, String productionName, String description) {
        this.productionID = productionID;
        this.releaseDate = releaseDate;
        this.productionName = productionName;
        this.creditList = new HashMap<>();
        this.description = description;
    }


    public void addToProduction(Credit credit){
        creditList.put(credit.getCreditID(),credit);

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

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
