package Domain.Users;

import java.sql.Connection;

public class User {
    private String username;
    private String password;
    private String role;
    private int ID;

    public User(String username, String password, String role, int ID) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getID() {
        return ID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}


