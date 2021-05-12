package Persistens;

import Domain.Catalog.Credit;
import Domain.Catalog.Person;
import Domain.Catalog.Production;
import Domain.IPersistanceHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersistanceHandler implements IPersistanceHandler {
    private static PersistanceHandler instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "CreditmanagementDB";
    private String username = "postgres";
    private String password = "1234";
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

    @Override // få metoden til at loade credits ind i Productionslisten.
    public Production getProduction(int productionID) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Production WHERE productionID = ?");
            stmt.setInt(1, productionID);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next()) {
                return null;
            }
            return new Production(sqlReturnValues.getInt(1), sqlReturnValues.getDate(2), sqlReturnValues.getString(3));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

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
    public boolean deleteProduction(int productionID) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM production WHERE productionID = ?");
            statement.setInt(1, productionID);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void addProduction(Timestamp date, String jobtitle) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO production (releasedate,productionname)" + " VALUES (?,?)");
            statement.setTimestamp(1, date);
            statement.setString(2, jobtitle);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Credit> getCredits() {
        List<Credit> returnValue = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM credit");
            ResultSet sqlReturnCredits = statement.executeQuery();
            while(sqlReturnCredits.next()){
                returnValue.add(new Credit(sqlReturnCredits.getString(1), new Person(sqlReturnCredits.getString(1), sqlReturnCredits.getString(2),
                        sqlReturnCredits.getString(3),sqlReturnCredits.getInt(4),sqlReturnCredits.getInt(5), sqlReturnCredits.getString(6))));
            }
            return returnValue;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return returnValue;
    }

    @Override
    public Credit getCredit(int creditID) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM credit WHERE creditID = ?");
            statement.setInt(1,creditID);
            ResultSet sqlReturnCredit = statement.executeQuery();
            if(!sqlReturnCredit.next()){
                return null;
            }
            return new Credit(sqlReturnCredit.getString(1), new Person(sqlReturnCredit.getString(1),sqlReturnCredit.getString(2),
                   sqlReturnCredit.getString(3),sqlReturnCredit.getInt(4),sqlReturnCredit.getInt(5),sqlReturnCredit.getString(6)));
            //Er i tvivl om jeg har gjort ovenstående rigtigt...?
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateCredit() {

    }

    @Override
    public boolean deleteCredit(int creditID) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM credit WHERE creditID = ?");
            statement.setInt(1,creditID);
            return statement.execute();
        }   catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void addCredit(int creditID, String jobtitle) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO credit (creditid, jobtitle)" + " VALUES (?,?)");
            statement.setInt(1,creditID);
            statement.setString(2, jobtitle);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
            return new Person(sqlReturnValues.getString(1), sqlReturnValues.getString(2), sqlReturnValues.getString(3),
                    sqlReturnValues.getInt(4), sqlReturnValues.getInt(5), sqlReturnValues.getString(6));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }



    @Override
    public void updatePerson() {

    }

    @Override
    public boolean deletePerson(int personID) {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM person WHERE uid = ?");
            statement.setInt(1,personID);
            return statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addPerson(Person person) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO person (mail,fname,lname,phonenumber,uid,description)"
                    + " VALUES (?,?,?,?,?,?) ");
            statement.setString(1, person.getMail());
            statement.setString(2, person.getfName());
            statement.setString(3, person.getlName());
            statement.setInt(4, person.getPhoneNumber());
            statement.setInt(5, person.getuID());
            statement.setString(6, person.getDescription());
            statement.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
