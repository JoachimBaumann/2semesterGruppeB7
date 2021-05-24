package Presentation;

import Domain.Catalog.Production;

public final class Informationholder {

private Production production;
private final static Informationholder INSTANCE = new Informationholder();


public static Informationholder getInstance(){
    return INSTANCE;
}

public void setProduction(Production production){
    this.production = production;
}

public Production getProduction(){
    return this.production;
}

}
