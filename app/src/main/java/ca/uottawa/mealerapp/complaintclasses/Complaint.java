package ca.uottawa.mealerapp.complaintclasses;

public class Complaint {
    String title;
    String description;
    String cookUsername;
    String clientUsername;

    public Complaint(String title, String description, String cookUsername, String clientUsername) {
        this.title = title;
        this.description = description;
        this.cookUsername = cookUsername;
        this.clientUsername = clientUsername;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCookUsername() {
        return cookUsername;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCookUsername(String cookUsername) {
        this.cookUsername = cookUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
}
