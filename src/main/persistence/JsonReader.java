package persistence;

import model.Food;
import model.FoodListing;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.User;
import org.json.*;

/*
 * Referenced by JsonSerializationDemo
 * Represents a reader that reads diet from JSON data stored in file
 */
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the loaded diet from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FoodListing read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFoodListing(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses foodList from JSON object and returns it
    private FoodListing parseFoodListing(JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        FoodListing newList = new FoodListing(name);
        addFoods(newList, jsonObject);
        addInfo(newList, jsonObject);
        return newList;
    }

    // MODIFIES: newList
    // EFFECTS: parses foods from JSON object and adds them to FoodListing
    private void addFoods(FoodListing newList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("The Diet Context");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(newList, nextFood);
        }
    }

    // MODIFIES: newList
    // EFFECTS: parses food from JSON object and adds it to FoodListing
    private void addFood(FoodListing newList, JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        double calories = jsonObject.getDouble("Calories");
        double carbohydrates = jsonObject.getDouble("Carbohydrates");
        double fats = jsonObject.getDouble("Fats");
        double proteins = jsonObject.getDouble("Proteins");
        Food food = new Food(name, calories, carbohydrates, fats, proteins);
        newList.addFoodToList(food);
    }

    // MODIFIES: newList
    // EFFECTS: parses user info from JSON object and adds them to FoodListing
    private void addInfo(FoodListing newList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("User Info");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addInformation(newList, nextFood);
        }
    }

    // MODIFIES: newList
    // EFFECTS: parses information from JSON object and adds it to FoodListing
    private void addInformation(FoodListing wr, JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        String gender = jsonObject.getString("Gender");
        String activityStatus = jsonObject.getString("Activity level");
        double age = jsonObject.getDouble("Age");
        double height = jsonObject.getDouble("Height");
        double weight = jsonObject.getDouble("Weight");

        User user = new User(name, gender, age, height, weight, activityStatus);

        wr.addUser(user);
    }

}
