package ca.uottawa.mealerapp.userclasses;

public interface User {
    // Getters
    String getUsername();
    String getPassword();
    String getType();

    // Setters
    void setUsername(String username);
    void setPassword(String password);
}
