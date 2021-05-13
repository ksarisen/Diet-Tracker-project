package model;

import org.json.JSONObject;
import persistence.Writable;
/*
 * Represents a food or a nutrition object showing its name, calories, and macronutrients of
 * its nutrition facts (in grams)
 */

public class Food implements Writable {

    public static final int BY_SERVING_OF = 100;    // constant variable to store fixed serving size

    private String name;                            // name of the nutrition

    private double calories;                           // calories that nutrition contains per 100gr
    private double carbohydrates;                   // amount of carbohydrates that nutrition contains per 100gr
    private double fats;                            // amount of fats that nutrition contains per 100gr
    private double proteins;                        // amount of proteins that nutrition contains per 100gr

    // Filled constructor
    // EFFECTS: Creates a food item with given parameters.
    public Food(String name, double calories, double carbohydrates, double fats, double proteins) {
        this.name = name;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.proteins = proteins;
    }

    // Accessors
    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    // Mutators
    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public double getFats() {
        return fats;
    }

    public double getProteins() {
        return proteins;
    }

    // EFFECTS: returns a string representation of Food
    @Override
    public String toString() {
        return "\nName: " + name + ", Calories: "
                + calories + " kcal, Carbohydrates: " + carbohydrates
                + " g, Fats: " + fats + " g, Proteins: " + proteins + " g";
    }

    // Referenced by JsonSerializationDemo
    // EFFECTS: Creating a Json object to store needed data to a file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Calories", calories);
        json.put("Carbohydrates", carbohydrates);
        json.put("Fats", fats);
        json.put("Proteins", proteins);
        return json;
    }
}
