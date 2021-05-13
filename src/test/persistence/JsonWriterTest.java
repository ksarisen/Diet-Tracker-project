package persistence;

import model.User;
import model.Food;
import model.FoodListing;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced by JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            FoodListing newList = new FoodListing("My diet`");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWDiet() {
        try {
            FoodListing newList = new FoodListing("My diet");
            User newUser = new User("Kerem", "Male", 24, 184, 79.9, "Very Active");
            newList.addUser(newUser);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDiet.json");
            writer.open();
            writer.write(newList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDiet.json");
            newList = reader.read();
            assertEquals("My diet", newList.getName());
            assertEquals(0, newList.returnSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDiet() {
        try {
            FoodListing newList = new FoodListing("My diet");
            newList.addFoodToList(new Food("Egg", 50, 0.5, 3, 6));
            newList.addFoodToList(new Food("Rice", 100, 55, 6, 11));
            User newUser = new User("Kerem", "Male", 24, 184, 79.9, "Very Active");
            newList.addUser(newUser);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDiet.json");
            writer.open();
            writer.write(newList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDiet.json");
            newList = reader.read();
            assertEquals("My diet", newList.getName());
            List<Food> foods = newList.getFoods();
            assertEquals(2, foods.size());
            checkDietContext("Egg", 50, 0.5, 3, 6, foods.get(0));
            checkDietContext("Rice", 100, 55, 6, 11, foods.get(1));
            checkUserInfo(newUser, "Kerem", "Male", "Very Active", 24, 184, 79.9);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}