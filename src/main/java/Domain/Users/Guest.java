package Domain.Users;

public class Guest extends User {
    @Override
    public String checkRole() {
        return "Guest";
        }
    public Guest(String username, String password){
        super(username, password);
    }

}
