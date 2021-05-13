package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User profile;

    @BeforeEach
    void runBefore() {
        profile = new User();
        profile.setName("Kerem");
        profile.setGender("Male");
        profile.setAge(23);
        profile.setHeight(184);
        profile.setWeight(79.9);
        profile.setActivityChoice('d');

    }

    @Test
    void testConstructor() {
        profile = new User();
    }

    @Test
    void testConstructor2() {
        profile = new User("bora", "Male", 22, 180, 60, "Sedentary");
        assertEquals(profile.getName(), "bora");
        assertEquals(profile.getGender(), "Male");
        assertEquals(profile.getAge(), 22);
        assertEquals(profile.getHeight(), 180);
        assertEquals(profile.getWeight(), 60);
        assertEquals(profile.getActivityStatus(), "Sedentary");
    }
    @Test
    void testMutators() {
        profile.setName("Haluk");
        profile.setGender("Male");
        profile.setGenderChoice("a");
        profile.setAge(54);
        profile.setHeight(180);
        profile.setWeight(70);
        profile.setActivityStatus("Sedentary");
        profile.setActivityChoice('c');


        assertEquals("Haluk", profile.getName());
        assertEquals("Male", profile.getGender());
        assertEquals(54, profile.getAge());
        assertEquals(180, profile.getHeight());
        assertEquals(70, profile.getWeight());
        assertEquals("Sedentary", profile.getActivityStatus());
        assertEquals('c', profile.getActivityChoice());


        profile.setGenderChoice("");
        assertEquals("", profile.getGender());

        profile.setGenderChoice("A");
        assertEquals("Male", profile.getGender());
        profile.setGenderChoice("B");
        assertEquals("Female", profile.getGender());
        profile.setGenderChoice("a");
        assertEquals("Male", profile.getGender());
        profile.setGenderChoice("b");
        assertEquals("Female", profile.getGender());
    }

    @Test
    void testTdeeCalculator () {
        profile.fixUserActivityLevel(profile.getActivityChoice());

        assertEquals(3319.3, profile.tdeeCalculator());

        profile.setActivityChoice('a');
        profile.fixUserActivityLevel(profile.getActivityChoice());
        assertEquals(2309.08, profile.tdeeCalculator());

        profile.setActivityChoice('b');
        profile.fixUserActivityLevel(profile.getActivityChoice());
        assertEquals(2645.82, profile.tdeeCalculator());

        profile.setActivityChoice('c');
        profile.fixUserActivityLevel(profile.getActivityChoice());
        assertEquals(2982.56, profile.tdeeCalculator());

        profile.setActivityChoice('e');
        profile.fixUserActivityLevel(profile.getActivityChoice());
        assertEquals(3656.04, profile.tdeeCalculator());

        profile.setActivityChoice('x');
        profile.fixUserActivityLevel(profile.getActivityChoice());
        assertEquals(0, profile.tdeeCalculator());

        profile.setGenderChoice("b");
        profile.setActivityChoice('a');
        profile.fixUserActivityLevel(profile.getActivityChoice());
        assertEquals(1974.17, profile.tdeeCalculator());

        profile.setGender("FEMALE");
        profile.setActivityChoice('b');
        profile.fixUserActivityLevel(profile.getActivityChoice());
        assertEquals(2262.07, profile.tdeeCalculator());

        profile.setActivityChoice('c');
        profile.fixUserActivityLevel(profile.getActivityChoice());
        assertEquals(2549.97, profile.tdeeCalculator());

        profile.setActivityChoice('d');
        profile.fixUserActivityLevel(profile.getActivityChoice());
        assertEquals(2837.87, profile.tdeeCalculator());

        profile.setActivityChoice('e');
        profile.fixUserActivityLevel(profile.getActivityChoice());
        assertEquals(3125.77, profile.tdeeCalculator());

        profile.setActivityChoice('x');
        profile.fixUserActivityLevel(profile.getActivityChoice());
        assertEquals(0, profile.tdeeCalculator());

        profile.setGender("Not given");
        profile.fixUserActivityLevel(profile.getActivityChoice());
        assertEquals(0, profile.tdeeCalculator());
    }

    @Test
    void testSetGender() {
        profile.setActivityStatus("Very Active");

        profile.setGender("male");
        assertEquals(profile.getGender(), "Male");

        profile.setGender("Female");
        assertEquals(profile.getGender(), "Female");

        assertEquals(profile.tdeeCalculator(), 2837.87);

        profile.setGender("null");
        assertEquals(profile.tdeeCalculator(), 0);
    }

    @Test
    void testDisplay() {
        profile.fixUserActivityLevel(profile.getActivityChoice());

        assertEquals("\nName: " + profile.getName() + "\nGender: " + profile.getGender() + "\nAge: " + profile.getAge()
                + "\nHeight: " + profile.getHeight() + "\nWeight: " + profile.getWeight() + "\nActivity Level: "
                + profile.getActivityStatus(), profile.toString());
    }

    @Test
    void testPrintCalculation() {
        assertEquals("\nYou need " + profile.tdeeCalculator() + " calories per day to maintain your current weight.\nYou need "
                + (profile.tdeeCalculator() + 500) + " calories per day to bulk up which is +500 calories per day from "
                + "your maintenance of " + profile.tdeeCalculator() + " calories per day.\nYou need "
                + (profile.tdeeCalculator() - 500) + " calories per day to loose weight which is a "
                + "500 calorie per day deficit from your maintenance of 3,226 calories per day.", profile.printCalculation());
    }
}
