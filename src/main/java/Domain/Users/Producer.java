package Domain.Users;

public class Producer extends User {
    @Override
    public String checkRole() {
        return "Producer";
    }
    public int uID;


    public Producer(int uID, String username, String password){
        super(username, password);
        this.uID = uID;
    }

}
