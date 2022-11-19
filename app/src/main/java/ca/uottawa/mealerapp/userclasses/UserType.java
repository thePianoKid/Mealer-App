package ca.uottawa.mealerapp.userclasses;

public enum UserType {
    CLIENT("Client"),
    COOK("Cook"),
    ADMIN("Administrator");

    private String string;
    UserType(String string) {
        this.string = string;
    }

    @Override
    public String toString() { return string; }
}
