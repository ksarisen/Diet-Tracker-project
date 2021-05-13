package ui;

import model.Food;
import model.FoodListing;
import model.User;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static model.Food.BY_SERVING_OF;
import static model.User.*;

// Calorie tracker application which works according to user's commands.
public class DietTrackerApp {

    private static final String JSON_STORE = "./data/User's Diet.json";
    private Scanner userAnswer;                                            // to get user inputs
    private User userInfo;                                                 // user identification
    private FoodListing productList;                                       // list that stores all the food items
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    /*
     * referenced by the TellerApp.java
     * EFFECTS: runs the diet tracker application
     */
    public DietTrackerApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTracker();
    }

    /*
     * referenced by the method runTeller() by TellerApp.java
     * MODIFIES: this
     * EFFECTS: processes user answer
     *          app keeps running until user presses 'f' in the menu.
     */
    public void runTracker() {

        boolean keepRunning = true;
        String command;
        userAnswer = new Scanner(System.in);
        userInfo = new User();
        productList = new FoodListing("User's Diet");

        System.out.println("Would you like to load diet from file ? a.Yes b.No");
        if (userAnswer.hasNext("a") || userAnswer.hasNext("A")) {
            userAnswer.next();
            loadDiet();
        } else {
            userAnswer.next();
            getUserInfo(userInfo);
        }

        while (keepRunning) {
            displayOptions();
            command = createStringOnlyLetters();

            if (command.equalsIgnoreCase("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nTake care!");
    }

    /*
     * referenced by processCommand(String command) method of TellerApp.java
     * MODIFIES: this
     * EFFECTS: processes user command
     */
    private void processCommand(String userCommand) {

        if (userCommand.equalsIgnoreCase("a")) {
            System.out.println(userInfo.toString());
        } else if (userCommand.equalsIgnoreCase("b")) {
            System.out.println(userInfo.printCalculation());
        } else if (userCommand.equalsIgnoreCase("c")) {
            createAndAddNewFood();
        } else if (userCommand.equalsIgnoreCase("d")) {
            System.out.println(productList.displayAllFoods());
        } else if (userCommand.equalsIgnoreCase("e")) {
            removeFood();
        } else if (userCommand.equalsIgnoreCase("f")) {
            saveDiet();
        } else if (userCommand.equalsIgnoreCase("g")) {
            loadDiet();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    /*
     * REQUIRES: userInfo needs to be initialized.
     *           userInfo's name, gender and activity level variables accept values from the user that
     *           contain only letters
     *           userInfo's age, height and weight variables accept values from the user that contain only numbers
     * MODIFIES: this
     * EFFECTS: sets the fields of the userInfo which is the profile of the user that contains his/her information
     *          by asking questions to the user.
     */
    private void getUserInfo(User userInfo) {

        System.out.println("\nWelcome to The Diet Tracker");
        System.out.println("\nLet's get it started.\nMay I get your name ?");
        userInfo.setName(createStringOnlyLetters());

        System.out.println("\nChoose your gender:\na.Male  b.Female");
        userInfo.setGenderChoice(aorB());

        System.out.println("\nMay I get your age ?");
        userInfo.setAge(checkInputMismatchException());

        System.out.println("\nMay I get your height ?");
        userInfo.setHeight(checkInputMismatchException());

        System.out.println("\nMay I get your weight ?");
        userInfo.setWeight(checkInputMismatchException());

        System.out.println("\nPlease choose your activity level:\n" + "\na.Little to no exercise + work a desk job\n"
                + "b.Light exercise 1-3 days / week\n" + "c.Moderate exercise 3-5 days / week\n"
                + "d.Heavy exercise 6-7 days / week\n" + "e.Very heavy exercise, hard labor job, training 2x/day");

        userInfo.fixUserActivityLevel(aorBorCorDorE());
    }

     /* REQUIRES : food's name variable accepts value from the user that contain only letters
      *            all the food's nutrition facts variables accepts values from the user that contain only numbers
      * MODIFIES: this
      * EFFECTS: sets the fields of the empty food object with getting answers from the user.
      */
    public void createAndAddNewFood() {

        System.out.print("\nWhat is the name of the food you wanna add to your daily list ?\n");
        String name = (createStringOnlyLetters());

        System.out.println("\nWhat is the calorie value of the food you wanna to add by serving size of "
                + BY_SERVING_OF + " gr ?");
        double calories = (checkInputMismatchException());

        System.out.println("\nWhat is the amount of carbohydrates of the food you want to add by serving size of "
                + BY_SERVING_OF + " gr ?");
        double carbohydrates = (checkInputMismatchException());

        System.out.println("\nWhat is the amount of fats of the food you want to add by serving size of "
                + BY_SERVING_OF + " gr ?");
        double fats = (checkInputMismatchException());

        System.out.println("\nWhat is the amount of proteins of the food you want to add by serving size of "
                + BY_SERVING_OF + " gr ?");
        double proteins = (checkInputMismatchException());

        productList.addFoodToList(new Food(name, calories, carbohydrates, fats, proteins));
    }

    /*
     * EFFECTS: Checks if the String value that user chose is only contains letters.
     */

    public String createStringOnlyLetters() {
        while (!userAnswer.hasNext("[A-Za-z]+")) {
            System.out.println("Please use letters!");
            userAnswer.next();
        }
        return userAnswer.next();
    }

    /*
     * EFFECTS: Checks if the user chose 'a' or 'b' and not entered some random letter.
     */

    public String aorB() {
        while (!userAnswer.hasNext("[A-Ba-b]+")) {
            System.out.println("Please choose the option a or b!");
            userAnswer.next();
        }
        return userAnswer.next();
    }

    /*
     * EFFECTS: Checks if the user chose one of the options in the given menu and not entered
     * some random letter.
     */

    public char aorBorCorDorE() {
        while (!userAnswer.hasNext("[a-e]+")) {
            System.out.println("Please choose the option a, b, c, d or e!");
            userAnswer.next();
        }
        return Character.toLowerCase(userAnswer.next().charAt(0));
    }

    // EFFECTS: Covers the inputMismatchException error.

    public double checkInputMismatchException() {
        double value = 0;
        do {
            try {
                value = userAnswer.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("Invalid number. Please use numbers, not letters! ");
            }
            userAnswer.nextLine(); // clears the buffer
        } while (value <= 0);
        return value;
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes the food object that user chooses from the list.
     */
    public void removeFood() {
        System.out.print("What is the name of the food item you want to remove ?\n");
        String toBeRemoved = userAnswer.next();

        if (productList.isContainsName(toBeRemoved)) {
            for (int i = 0; i < productList.returnSize(); i++) {
                if (toBeRemoved.equalsIgnoreCase(productList.returnItem(i).getName())) {
                    productList.removeFoodFromList(productList.returnItem(i));
                    System.out.println("\n" + toBeRemoved + " is been removed.");
                    break;
                }
            }
        } else {
            System.out.println("The food you entered doesn't exist in the list.");
        }
    }

    /*
     * referenced by displayMenu() method of the TellerApp.java
     * EFFECTS: displays the main menu to user.
     */
    public void displayOptions() {
        System.out.println("\nMenu: ");
        System.out.println("a --> Check user information");
        System.out.println("b --> Check your total daily calorie needs");
        System.out.println("c --> Create and add new food item");
        System.out.println("d --> See the foods you entered");
        System.out.println("e --> Remove a food item from the daily list");
        System.out.println("f --> Save diet to file");
        System.out.println("g --> Load diet to file");
        System.out.print("q --> Exit\n");
    }

    // Referenced by JsonSerializationDemo
    // EFFECTS: saves the diet to file
    private void saveDiet() {
        try {
            jsonWriter.open();
            jsonWriter.write(productList);
            jsonWriter.close();
            System.out.println("Saved User's diet to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Referenced by JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads diet from file
    private void loadDiet() {
        try {
            productList = jsonReader.read();
            System.out.println("Loaded " + productList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
