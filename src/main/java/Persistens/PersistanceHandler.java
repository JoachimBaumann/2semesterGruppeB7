package Persistens;

import Domain.Catalog.Credit;
import Domain.Catalog.Person;
import Domain.Catalog.Production;
import Domain.IPersistanceHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersistanceHandler implements IPersistanceHandler {
    private static PersistanceHandler instance;
    private String url = "hattie.db.elephantsql.com";
    private int port = 5432;
    private String databaseName = "dpanlzms";
    private String username = "dpanlzms";
    private String password = "ZhCCYbMW-ey8nERpqtL4JzHbF8CVihSJ";
    private Connection connection = null;

    private PersistanceHandler() {
        initializePostgresqlDatabase();
    }

    public static PersistanceHandler getInstance() {
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
    public List<Production> getProductions() {
        List<Production> returnValue = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Production");
            ResultSet sqlReturnProductions = statement.executeQuery();
            while (sqlReturnProductions.next()) {
                returnValue.add(new Production(sqlReturnProductions.getInt(1), sqlReturnProductions.getString(2),
                        sqlReturnProductions.getString(3), sqlReturnProductions.getString(4)));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
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
            return new Production(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getString(3), sqlReturnValues.getString(4));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }


    @Override
    public ResultSet searchProduction(String name) {
/*          //TODO SKAL IMPLEMENTERES, udsættes for nu
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT REGEXP_MATCHES(productionname, '['?'].*') productionname,productionid FROM production;");
            preparedStatement.setString(1, name);
            return preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


 */

        return null;
    }

    @Override
    public boolean updateProduction(int productionID, String releaseDate, String productionName, String description) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE production SET releaseDate = ?, productionName = ?, Description = ?" +
                    "WHERE productionID = ?");
            statement.setString(1, releaseDate);
            statement.setString(2, productionName);
            statement.setString(3, description);
            statement.setInt(4, productionID);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public boolean deleteProduction(int productionID) {
        try {
            PreparedStatement statement1 = connection.prepareStatement("DELETE FROM Credit WHERE productionID = ?");
            statement1.setInt(1, productionID);
            statement1.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
    public int addProduction(String releaseDate, String productionName, String description) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO production (releasedate,productionName,description)" + " VALUES (?,?,?) RETURNING productionID;");
            statement.setString(1, releaseDate);
            statement.setString(2, productionName);
            statement.setString(3, description);
            statement.execute();
            ResultSet last_updated_person = statement.getResultSet();
            last_updated_person.next();
            int productionID = last_updated_person.getInt(1);
            return productionID;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }


    @Override
    public List<Credit> getCredits() {
        List<Credit> returnValue = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM credit");
            ResultSet sqlReturnCredits = statement.executeQuery();
            while (sqlReturnCredits.next()) {
                returnValue.add(new Credit(sqlReturnCredits.getInt("creditID"), sqlReturnCredits.getString("jobrole"), sqlReturnCredits.getInt("productionid"), sqlReturnCredits.getInt("personid")));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    @Override
    public Credit getCredit(int creditID) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM credit WHERE creditID = ?");
            statement.setInt(1, creditID);
            ResultSet sqlReturnCredit = statement.executeQuery();
            if (!sqlReturnCredit.next()) {
                return null;
            }
            return new Credit(sqlReturnCredit.getInt("creditID"), sqlReturnCredit.getString("jobtitle"), sqlReturnCredit.getInt("productionid"), sqlReturnCredit.getInt("personid"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateCredit(int creditID, String jobTitle) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE credit SET jobtitle = ? WHERE creditID = ? ");
            statement.setString(1, jobTitle);
            statement.setInt(2, creditID);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCredit(int creditID) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM credit WHERE creditID = ?");
            statement.setInt(1, creditID);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public int addCredit(int productionID, int personID, String jobrole) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO credit (productionid,personid,jobrole)" + " VALUES (?,?,?) RETURNING creditID;");
            statement.setInt(1, productionID);
            statement.setInt(2, personID);
            statement.setString(3, jobrole);
            statement.execute();
            ResultSet last_updated_credit = statement.getResultSet();
            last_updated_credit.next();
            int creditID = last_updated_credit.getInt(1);
            return creditID;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    @Override
    public List<Person> getPersons() {
        try {
            PreparedStatement state = connection.prepareStatement("SELECT * FROM Person");
            ResultSet sqlReturnValues = state.executeQuery();
            List<Person> returnValue = new ArrayList<>();

            while (sqlReturnValues.next()) {
                returnValue.add(new Person(sqlReturnValues.getString(1), sqlReturnValues.getString(2), sqlReturnValues.getString(3),
                        sqlReturnValues.getInt(4), sqlReturnValues.getInt(5), sqlReturnValues.getString(6)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
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
    public boolean updatePerson(String mail, String fName, String lName, int phoneNumber, int uID, String description) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE person SET mail = ?, fname = ?, lname = ?, phonenumber = ?, description = ?" +
                    "WHERE uid = ?");
            statement.setString(1, mail);
            statement.setString(2, fName);
            statement.setString(3, lName);
            statement.setInt(4, phoneNumber);
            statement.setString(5, description);
            statement.setInt(6, uID);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePerson(int personID) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM credit where personid = ?");
            preparedStatement.setInt(1, personID);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM person WHERE uid = ?");
            statement.setInt(1, personID);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int addPerson(String mail, String fname, String lname, int phonenumber, String description) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO person (mail,fname,lname,phonenumber,description)"
                    + " VALUES (?,?,?,?,?) RETURNING uid;");
            statement.setString(1, mail);
            statement.setString(2, fname);
            statement.setString(3, lname);
            statement.setInt(4, phonenumber);
            statement.setString(5, description);
            statement.execute();
            ResultSet last_updated_person = statement.getResultSet();
            last_updated_person.next();
            int personID = last_updated_person.getInt(1);
            return personID;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public ResultSet getLogin(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE Username = ?");
            statement.setString(1, username);
            statement.execute();
            ResultSet user = statement.getResultSet();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
