package Persistens;
import java.sql.*;

public class dbConnect {

    static Connection connection = null;

    public static void main(String[] args) {
        //Create connection to database
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Project",
                    "postgres",
                    "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
/*
        //Insert user (data) into the database
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Person(fname, lname, mail, phonenumber, description) VALUES (?,?,?,?,?)");
            insertStatement.setString(1, "John"); //"INSERT INTO users(name, cpr) VALUES ('John Doe', ?)"
            insertStatement.setString(2, "Doe"); //"INSERT INTO users(name, cpr) VALUES ('John Doe', '1111111111')"
            insertStatement.setString(3, "johnDoe@hardcore.now");
            insertStatement.setInt(4,12345678 );
            insertStatement.setString(5, "I'm a hardcore motherfucker");
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();

        }

 */



        //Query database for users with CPR X
        try {
            //Retrieve data but cant see the data
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM Person WHERE uID = ?");
            queryStatement.setInt(1,1);
            ResultSet queryResultSet = queryStatement.executeQuery();

            //Does so we can see the retrieved data
            System.out.println("The following users matched the query:");
            while(queryResultSet.next()){
                System.out.println("The users name was "
                        + queryResultSet.getString("lname")
                        + " and his CPR number was "
                        + queryResultSet.getString("uID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
