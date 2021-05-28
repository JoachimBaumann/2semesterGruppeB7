package Domain.Catalog;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {
    Catalog catalog = new Catalog();
    Production production = new Production(1,"20-05-1999","Testfilm","Filmen tester shit");
    ArrayList<Production> productions = new ArrayList<Production>();
    void makeList(){
        for (int i = 0; i < 20; i++) {
            Production production = new Production(i,String.valueOf(i),String.valueOf(i),String.valueOf(i));
            productions.add(production);
        }
    }

    @Test
    void getProductionList() {
        makeList();
        assertEquals(20, productions.size());
    }

    @Test
    void createProduction() {
    }

    @Test
    void testaddProductionToCatalog() {
    catalog.addProductionToCatalog(this.production.getProductionID(),this.production);
    assertNotNull(catalog.getProduction(1));
    }

    @Test
    void getPersonsList() {

    }

    @Test
    void getProduction() {
    }

    @Test
    void addCreditToProduction() {
    }
}