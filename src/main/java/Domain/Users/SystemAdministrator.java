package Domain.Users;

public class SystemAdministrator extends User {
    @Override
    public String checkRole() {
        return "SystemAdministrator";
    }
    private int sID;
    public SystemAdministrator(int sID, String username, String password){
        super(username, password);
        this.sID = sID;
    }
}

