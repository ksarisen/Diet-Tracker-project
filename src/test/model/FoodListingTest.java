package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FoodListingTest {
    private FoodListing newList;
    private Food item;
    private Food item2;
    private User profile1;

    @BeforeEach
    void runBefore() {
        newList = new FoodListing("User's Diet");
        item = new Food("Food1", 50, 10, 20, 30);
        item2 = new Food("Food2", 30, 4, 20, 40);
        profile1 = new User();
    }

    @Test
    void testConstructor() {
        assertEquals(newList.returnSize(), 0);
        assertEquals(newList.getName(), "User's Diet");
        assertEquals(newList.getTotalCalories(), 0);
    }

    @Test
    void testAddFoodToList() {
        newList.addFoodToList(item);

        assertTrue(newList.isContainsName("Food1"));

        List<Food> foods = newList.getFoods();
        assertEquals(1, foods.size());
    }

    @Test
    void testAddUser() {
        profile1.setName("Kerem");
        newList.addUser(profile1);
        assertEquals(newList.returnSize2(), 1);
    }

    @Test
    void testIsContainsName() {
        newList.addFoodToList(item);
        newList.addFoodToList(item2);
        assertEquals(newList.find("Food1"), 0);
        assertEquals(newList.find("Sdfsdf"), -1);

        assertFalse(newList.isContainsName("random"));
    }

    @Test
    void testRemoveFoodFromList() {
        newList.addFoodToList(item);
        assertEquals(newList.returnSize(), 1);

        newList.removeFoodFromList(item);
        assertEquals(newList.returnSize(), 0);
    }

    @Test
    void testReturnItem() {
        newList.addFoodToList(item);
        newList.addFoodToList(item2);

        assertSame(newList.returnItem(0), item);

        newList.removeFoodFromList(item);

        assertSame(newList.returnItem(0), item2);
    }

    @Test
    void testDisplayAllFoods() {
        newList.addFoodToList(item);
        newList.addFoodToList(item2);

        assertEquals("\nTotal calories taken: " + newList.getTotalCalories() + "kcal\n" + item.toString()
                + item2.toString(), newList.displayAllFoods());
    }

    @Test
    void testHashMap() {
        newList.addFoodType("Carbs");
        assertEquals(1, newList.getHashMapSize());
        assertTrue(newList.isHashMapContainsKey("Carbs"));

        newList.addFoodType("Fats");
        assertEquals(2, newList.getHashMapSize());
        assertTrue(newList.isHashMapContainsKey("Fats"));

        newList.addFoodOptions("Carbs", "Rice");

        assertEquals(newList.foodOptionsToString("Carbs"), "Suggestions:\n\nRice\n");
    }
}
