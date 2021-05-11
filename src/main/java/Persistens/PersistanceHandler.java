package Persistens;

import Domain.Catalog.Production;
import Domain.CreditManager;
import Domain.IPersistanceHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class PersistanceHandler implements IPersistanceHandler {
    private static PersistanceHandler instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "Projekt";
    private String username = "postgres";
    private String password = "skriv din kode";
    private Connection connection = null;

    private PersistanceHandler(){
        initializePostgresqlDatabase();
    }

    public static PersistanceHandler getInstance(){
        if (instance == null) {
            instance = new PersistanceHandler();
        }
        return instance;
    }

    private void initializePostgresqlDatabase() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) System.exit(-1);
        }
    }

    @Override
    public void getProductions() {

    }

    @Override
    public void getProduction(int productionID) {

    }

    @Override
    public boolean setProduction() {
        return false;
    }

    @Override
    public void searchProduction(String name) {

    }

    @Override
    public void updateProduction() {

    }

    @Override
    public void deleteProduction(int productionID) {

    }

    @Override
    public void addProduction() {

    }

    @Override
    public void getCredits() {

    }

    @Override
    public void getCredit(int creditID) {

    }

    @Override
    public void updateCredit() {

    }

    @Override
    public void deleteCredit(int creditID) {

    }

    @Override
    public void addCredit() {

    }

    @Override
    public void getPersons() {

    }

    @Override
    public void getPerson(int personID) {

    }

    @Override
    public void updatePerson() {

    }

    @Override
    public void deletePerson(int PersonID) {

    }

    @Override
    public void addPerson() {

    }
}
