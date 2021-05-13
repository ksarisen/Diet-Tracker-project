package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FoodTest {
    private Food food;

    @BeforeEach
    void runBefore() {
        food = new Food("Not given", 0, 0, 0, 0);
    }

    @Test
    void testConstructor() {
        assertEquals("Not given", food.getName());
        assertEquals(0, food.getCalories());
        assertEquals(0, food.getCarbohydrates());
        assertEquals(0, food.getFats());
        assertEquals(0, food.getProteins());
    }

    @Test
    void testMutators() {
        food.setName("Chicken");
        food.setCalories(500);
        food.setCarbohydrates(10.5);
        food.setFats(0.2);
        food.setProteins(40.2);

        assertEquals("Chicken", food.getName());
        assertEquals(500, food.getCalories());
        assertEquals(10.5, food.getCarbohydrates());
        assertEquals(0.2, food.getFats());
        assertEquals(40.2, food.getProteins());
    }

    @Test
    void testToString() {
        assertEquals("\nName: " + food.getName() + ", Calories: " + food.getCalories() + " kcal, Carbohydrates: "
                + food.getCarbohydrates() + " g, Fats: " + food.getFats() + " g, Proteins: " + food.getProteins() + " g", food.toString());
    }
}
