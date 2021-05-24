package Presentation;

import Domain.Catalog.Person;
import Domain.Catalog.Production;
import Domain.Users.User;

public final class Informationholder {

private Production production;
private final static Informationholder INSTANCE = new Informationholder();
private User user = new User("systemadmin", "5678","Systemadministrator",200);
private Person person;



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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
