package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

import static model.User.*;

// Represents a List to store many Food items in it.
public class FoodListing implements Writable {
    private final String name2;
    private final Map<String, ArrayList<String>> foodOptions = new HashMap<>();
    private final List<Food> foodS;     // creates a list to hold the foods user enters
    private final List<User> info;
    private double totalCalories;           // sum of total calories entered

    /*
     * EFFECTS: List<Food> foodS is set to arraylist.
     *          totalCalories on FoodListing is set to 0.
     */
    public FoodListing(String name2) {
        this.name2 = name2;
        foodS = new ArrayList<>();
        info = new ArrayList<>();
        totalCalories = 0;
    }

    // Accessors
    public double getTotalCalories() {
        return totalCalories;
    }

    public String getName() {
        return name2;
    }

    /* REQUIRES: newFood isn't null
     * MODIFIES: this
     * EFFECTS: adds a new Food item in the list.
     */
    public void addFoodToList(Food newFood) {
        foodS.add(newFood);
        totalCalories += newFood.getCalories();
    }

    /*
     * MODIFIES:this
     * EFFECTS: add a user item in the info list
     */
    public void addUser(User user) {
        info.add(user);
    }

    /*
     * REQUIRES: newFood exists in the list
     * MODIFIES: this
     * EFFECTS: removes the newFood object from the list.
     */
    public void removeFoodFromList(Food newFood) {
        totalCalories -= newFood.getCalories();
        foodS.remove(newFood);
    }

    // EFFECTS: returns an unmodifiable list of foods in this diet
    public List<Food> getFoods() {
        return Collections.unmodifiableList(foodS);
    }

    // EFFECTS: returns the size of the list.
    public int returnSize() {
        return foodS.size();
    }

    // EFFECTS: returns the size of the list.
    public int returnSize2() {
        return info.size();
    }

    /*
     * REQUIRES: i > 0 or i >= foods.size()
     * EFFECTS: returns the Food object at index i of the list.
    */
    public Food returnItem(int i) {
        return foodS.get(i);
    }

    // EFFECTS: returns the index of the chosen item
    public int find(String name) {
        for (int i = 0; i < foodS.size(); i++) {
            if (foodS.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * EFFECTS: returns true if the list contains the food object which named as foodName.
     *          returns false if the list doesn't contain the food object which named as foodName.
     */
    public boolean isContainsName(String foodName) {
        for (Food food : foodS) {
            if (food.getName().equalsIgnoreCase(foodName)) {
                return true;
            }
        }
        return false;
    }

    /*
     * EFFECTS: returns a string representation of the total calories that been taken and
     *          all the food objects in the list with its information.
     */
    public String displayAllFoods() {
        StringBuilder print = new StringBuilder();
        for (Food food : foodS) {
            print.append(food.toString());
        }
        return "\nTotal calories taken: " + totalCalories + "kcal\n" + print;
    }

    // Referenced by JsonSerializationDemo
    // EFFECTS: Creating a Json object to store needed data to a file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name2);
        json.put("The Diet Context", foodsToJson());
        json.put("Total calories", getTotalCalories());
        json.put("User Info", userInfoToJson());

        return json;
    }

    /*
     * Referenced by JsonSerializationDemo
     * EFFECTS: returns foods in this diet as a JSON array
     */
    private JSONArray foodsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food t : foodS) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    /*
     * Referenced by JsonSerializationDemo
     * EFFECTS: returns user's info in this diet as a JSON array
     */
    private JSONArray userInfoToJson() {
        JSONArray jsonArray = new JSONArray();

        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Gender", gender);
        json.put("Age", age);
        json.put("Height", height);
        json.put("Weight", weight);
        json.put("Activity level", activityStatus);

        jsonArray.put(json);

        return jsonArray;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a type of food to the hashMap
     */
    public void addFoodType(String foodType) {
        foodOptions.put(foodType, new ArrayList<>());
    }

    /*
     * MODIFIES: this
     * EFFECTS: add food options for the specific food type
     */
    public void addFoodOptions(String foodType, String name) {
        ArrayList<String> options = foodOptions.get(foodType);
        options.add(name);
    }

    // EFFECTS: returns the size of the hash map
    public int getHashMapSize() {
        return foodOptions.size();
    }

    // EFFECTS: returns true if hash map contains the specific key, otherwise returns false
    public boolean isHashMapContainsKey(String key) {
        return foodOptions.containsKey(key);
    }

    // EFFECTS: returns a string representation of the suggestions for the specific food type
    public String foodOptionsToString(String foodType) {
        StringBuilder print = new StringBuilder();
        for (String s: foodOptions.get(foodType)) {
            print.append(s).append("\n");
        }
        return ("Suggestions:\n\n" + print);
    }
}
