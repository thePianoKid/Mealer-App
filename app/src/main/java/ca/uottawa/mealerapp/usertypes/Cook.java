package ca.uottawa.mealerapp.usertypes;

public class Cook implements User {
    private static final UserType type = UserType.COOK;
    private String username;
    private String password;

    public Cook(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String toString(){
        return this.username;
    }

    // -------- Getters --------
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type.toString();
    }

    // -------- Setters --------
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
