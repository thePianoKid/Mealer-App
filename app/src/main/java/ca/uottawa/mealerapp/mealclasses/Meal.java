package ca.uottawa.mealerapp.mealclasses;

public class Meal {
    String name;
    // Ex: main dish, soup, desert
    String type;
    // Ex: Italian, Canadian
    String cuisine;
    String listOfIngredients;
    String allergens;
    String price;
    String description;
    boolean isOffered;

    public Meal(String name, String type, String cuisine, String listOfIngredients,
                String allergens, String price, String description, boolean isOffered) {
        this.name = name;
        this.type = type;
        this.cuisine = cuisine;
        this.listOfIngredients = listOfIngredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
        this.isOffered = isOffered;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getListOfIngredients() {
        return listOfIngredients;
    }

    public String getAllergens() {
        return allergens;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOffered() {
        return isOffered;
    }
}
