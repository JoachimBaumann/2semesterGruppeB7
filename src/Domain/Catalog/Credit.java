package Domain.Catalog;

public class Credit {
    private String jobTitle;
    Person p;

    public Credit(String jobTitle, Person p) {
        this.jobTitle = jobTitle;
        this.p = p;
    }

    public void updateCredit() {
        throw new UnsupportedOperationException();
    }
}
