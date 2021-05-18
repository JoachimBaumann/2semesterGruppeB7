package Domain.Catalog;

public class Credit {
    private String jobTitle;
    private int creditID;
    Person p;

    public Credit(String jobTitle, int creditID, Person p) {
        this.jobTitle = jobTitle;
        this.creditID = creditID;
        this.p = p;
    }

    public int getCreditID(){
        return creditID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Person getPerson() {
        return p;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "jobTitle='" + jobTitle + '\'' +
                ", p=" + p +
                '}';
    }
}
