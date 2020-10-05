package Debt;

public class User {
   // private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirtname() {
        return firtname;
    }

    public void setFirtname(String firtname) {
        this.firtname = firtname;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }
    private int id;
    private String lastname;

    public User(int id, String lastname, String firtname, int debt) {
        this.id = id;
        this.lastname = lastname;
        this.firtname = firtname;
        this.debt = debt;
    }

    private String firtname;
    private int debt;

    @Override
    public String toString(){
        return firtname;
    }
    }


