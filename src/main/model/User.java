package model;

// Represents the user who specifies a name, a gender, an age, weight, height and the activity level.
public class User {

    public static final double Sedentary = 1.2;               // little to no exercise + work a desk job
    public static final double LightlyActive = 1.375;         // light exercise 1-3 days / week
    public static final double ModeratelyActive = 1.55;       // moderate exercise 3-5 days / week
    public static final double VeryActive = 1.725;            // heavy exercise 6-7 days / week
    public static final double ExtremelyActive = 1.9;         // very heavy exercise, hard labor job, training 2x/day

    public static final String genderA = "Male";              // constant variable for the male gender
    public static final String genderB = "Female";            // constant variable for the female gender

    public static String name;                // user's name
    public static String gender;              // user's gender
    public static double age;                 // user's age
    public static double height;              // user's height in cm
    public static double weight;              // user's weight in kg
    public static String activityStatus;      // users' activity status
    private double activityLevel;       // user's activity level
    private char activityChoice;        // a, b, c, d or e

    // Default constructor
    // EFFECTS: Creates an empty user profile.
    public User() {
        name = "null";
        gender = "null";
        activityStatus = "null";
    }

    // Constructor with parameters
    // EFFECTS: Creates a food item with given parameters.
    public User(String name, String gender, double age, double height, double weight, String activityStatus) {
        User.name = name;
        User.gender = gender;
        User.age = age;
        User.height = height;
        User.weight = weight;
        User.activityStatus = activityStatus;
    }
    /*
     * REQUIRES: name, gender and activityStatus can't have number values.
     *           age, height and weight can't have letter values.
     * EFFECTS: name on User is set to name, gender on User is set to gender,
     *          age on User is set to age, height on User si set to height,
     *          activityStatus on User ise set to activityStatus
     */

    // Mutators

    public void setName(String name) {
        User.name = name;
    }

    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            User.gender = genderA;
        } else if (gender.equalsIgnoreCase("female")) {
            User.gender = genderB;
        } else {
            User.gender = gender;
        }
    }

    public void setAge(double age) {
        User.age = age;
    }

    public void setHeight(double height) {
        User.height = height;
    }

    public void setWeight(double weight) {
        User.weight = weight;
    }

    public void setActivityStatus(String activityStatus) {
        User.activityStatus = activityStatus;
    }

    public void setGenderChoice(String genderChoice) {
        if (genderChoice.equalsIgnoreCase("a")) {
            User.gender = genderA;
        } else if (genderChoice.equalsIgnoreCase("b")) {
            User.gender = genderB;
        } else {
            User.gender = "";
        }
    }

    public void setActivityChoice(char activityChoice) {
        this.activityChoice = activityChoice;
    }

    // Accessors

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public double getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public char getActivityChoice() {
        return activityChoice;
    }

    /*
     * EFFECTS: bmr will be calculated by the calculation of the local fields.
     *          tdee will be calculated by bmr and is returned.
     */

    public double tdeeCalculator() {

        fixUserActivityLevel(fixActivityChoice(activityStatus));
        // variable to store the value of the user's bmr in order to calculate tdee
        double bmr = 0;
        if (gender.equals(genderA)) {
            bmr = 66 + ((13.7 * weight) + (5 * height) - (6.8 * age));
        } else if (gender.equals(genderB)) {
            bmr = 655 + ((9.6 * weight) + (1.8 * height) - (4.7 * age));
        }

        // variable to store the amount of daily calories needed for the user
        double tdee = 0;
        if (activityLevel == Sedentary) {
            tdee = bmr * Sedentary;
        } else if (activityLevel == LightlyActive) {
            tdee = bmr * LightlyActive;
        } else if (activityLevel == ModeratelyActive) {
            tdee = bmr * ModeratelyActive;
        } else if (activityLevel == VeryActive) {
            tdee = bmr * VeryActive;
        } else if (activityLevel == ExtremelyActive) {
            tdee = bmr * ExtremelyActive;
        } else if (bmr == 0) {
            tdee = 0;
        }
        return (double) Math.round(tdee * 100) / 100;
    }

    /*
     * MODIFIES: this
     * EFFECTS: modifies the activityStatus and activityLevel according to user's answer
     *          for the question of how active he/she is.
     */
    public void fixUserActivityLevel(char userChoice) {
        if (userChoice == 'a') {
            activityStatus = "Sedentary";
            activityLevel = Sedentary;

        } else if (userChoice == 'b') {
            activityStatus = "Lightly Active";
            activityLevel = LightlyActive;

        } else if (userChoice == 'c') {
            activityStatus = "Moderately Active";
            activityLevel = ModeratelyActive;

        } else if (userChoice == 'd') {
            activityStatus = "Very Active";
            activityLevel = VeryActive;

        } else if (userChoice == 'e') {
            activityStatus = "Extremely Active";
            activityLevel = ExtremelyActive;
        } else {
            activityStatus = "Not Given";
            activityLevel = 0;
        }
    }

    public char fixActivityChoice(String activityStatus) {
        switch (activityStatus) {
            case "Sedentary":
                return activityChoice = 'a';
            case "Lightly Active":
                return activityChoice = 'b';
            case "Moderately Active":
                return activityChoice = 'c';
            case "Very Active":
                return activityChoice = 'd';
            case "Extremely Active":
                return activityChoice = 'e';
            default:
                return 0;
        }
    }

    // EFFECTS: returns a string representation of User
    @Override
    public String toString() {
        return ("\nName: " + name + "\nGender: " + gender + "\nAge: " + age + "\nHeight: " + height + "\nWeight: "
                + weight + "\nActivity Level: " + activityStatus);
    }

    /*
     * EFFECTS: prints the information of how many daily calories needed for the user to reach his/her goal.
     *          returns a string representation of the above information.
     */
    public String printCalculation() {
        //double userTdee = (double) Math. round(tdeeCalculator() * 100) / 100;
        return ("\nYou need " + tdeeCalculator() + " calories per day to maintain your current weight.\nYou need "
                + (double) Math.round((tdeeCalculator() + 500) * 100) / 100
                + " calories per day to bulk up which is +500 calories per day from "
                + "your maintenance of " + tdeeCalculator() + " calories per day.\nYou need "
                + (double) Math.round((tdeeCalculator() - 500) * 100) / 100
                + " calories per day to loose weight which is a "
                + "500 calorie per day deficit from your maintenance of 3,226 calories per day.");
    }
}
