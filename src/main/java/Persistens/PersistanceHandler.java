package Persistens;

import Domain.Catalog.Production;
import Domain.CreditManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class PersistanceHandler implements CreditManager {
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
    public void ViewAllProductions() {

    }

    @Override
    public Production chooseProduction(int productionID) {
        return null;
    }

    @Override
    public void addCredit(String fName, String lName, String jobTitle, int phoneNumber) {

    }

    @Override
    public void confirmChanges() {

    }

    @Override
    public void findProduction() {

    }

    @Override
    public void generateReport(int productionID, Date releaseDate) {

    }

    @Override
    public void saveReport() {

    }
}
