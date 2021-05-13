package persistence;

import model.User;
import model.Food;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkDietContext(String Name, double Calories, double Carbohydrates, double Fats, double Proteins,
                                    Food food) {
        assertEquals(Name, food.getName());
        assertEquals(Calories, food.getCalories());
        assertEquals(Carbohydrates, food.getCarbohydrates());
        assertEquals(Fats, food.getFats());
        assertEquals(Proteins, food.getProteins());
    }

    protected void checkUserInfo(User user, String Name, String Gender, String activityStatus, double Age, double Height,
                                  double Weight) {
        assertEquals(Name, user.getName());
        assertEquals(Gender, user.getGender());
        assertEquals(activityStatus, user.getActivityStatus());
        assertEquals(Age, user.getAge());
        assertEquals(Height, user.getHeight());
        assertEquals(Weight, user.getWeight());
    }
}
