package Persistens;

import Domain.Catalog.Person;
import Domain.Catalog.Production;
import Domain.CreditManager;
import Domain.IPersistanceHandler;

import java.sql.*;
import java.util.Date;

public class PersistanceHandler implements IPersistanceHandler {
    private static PersistanceHandler instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "CreditmanagementDB";
    private String username = "postgres";
    private String password = "1234";
    private Connection connection = null;

    public PersistanceHandler(){
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
    public Person getPerson(int personID) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Person WHERE uid = ?");
            stmt.setInt(1, personID);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next()) {
                return null;
            }
            return new Person(sqlReturnValues.getString(2), sqlReturnValues.getString(3), sqlReturnValues.getInt(4), sqlReturnValues.getInt(5));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
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
