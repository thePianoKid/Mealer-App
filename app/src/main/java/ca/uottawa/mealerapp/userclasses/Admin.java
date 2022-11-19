package ca.uottawa.mealerapp.userclasses;

public class Admin implements User{
    private static final UserType type = UserType.ADMIN;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
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
