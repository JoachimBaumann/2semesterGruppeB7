package Presentation;

import Domain.Catalog.Production;
import Domain.Users.User;

public final class Informationholder {

private Production production;
private final static Informationholder INSTANCE = new Informationholder();
private User user;



public static Informationholder getInstance(){
    return INSTANCE;
}

public void setProduction(Production production){
    this.production = production;
}

public Production getProduction(){
    return this.production;
}

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
