package persistence;

import model.Food;
import model.FoodListing;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced by JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FoodListing wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDiet() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDiet.json");
        try {
            FoodListing wr = reader.read();
            assertEquals("My diet", wr.getName());
            assertEquals(0, wr.returnSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDiet() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDiet.json");
        try {
            FoodListing newList = reader.read();
            assertEquals("My diet", newList.getName());
            List<Food> foods = newList.getFoods();
            assertEquals(2, foods.size());
            checkDietContext("Cheese", 200, 0.5, 30, 10, foods.get(0));
            checkDietContext("Meatball", 100, 11, 20, 16.6, foods.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}