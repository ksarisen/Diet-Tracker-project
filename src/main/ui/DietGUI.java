package ui;

import model.Food;
import model.FoodListing;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import static java.lang.String.valueOf;

// GUI for calorie tracker application
public class DietGUI {

    private static final String JSON_STORE = "./data/User's Diet.json";

    protected static JFrame frame;

    public static JTextField txtUserName;
    public static JTextField txtUserGender;
    public static JTextField txtUserAge;
    public static JTextField txtUserHeight;
    public static JTextField txtUserWeight;
    public static JTextArea txtUserActivityChoice;

    public static JTextField txtFoodName;
    public static JTextField txtCalories;
    public static JTextField txtCarbs;
    public static JTextField txtFats;
    public static JTextField txtProteins;
    public static JTextPane txtAll;

    public static JTextPane txtFoodType;
    public static JTextPane txtOptions;

    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    FoodListing productList;
    User profile;

    // EFFECTS: runs the diet tracker application
    public DietGUI() {
        run();
    }

    /*
     * MODIFIES: this
     * EFFECTS: processes the user commands, creates the GUI by creating two separate frames, labels, panels,
     *          text fields and buttons.
     *          app keeps running until user presses exit.
     */
    private void run() {

        productList = new FoodListing("User's Diet");
        profile = new User();

        frame = new JFrame();
        frame.setFont(new Font("GAINZ", Font.BOLD | Font.ITALIC, 13));
        frame.setTitle("DIET");
        frame.setPreferredSize(new Dimension(1000,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainP = new JPanel();
        frame.getContentPane().add(mainP, BorderLayout.CENTER);
        mainP.setLayout(null);
        mainP.setBackground(Color.red.darker());

        JPanel sideP = new JPanel();
        sideP.setBounds(35,210,200, 37);
        sideP.setBackground(Color.BLACK);
        mainP.add(sideP);

        JLabel jlabel = new JLabel("Per 100gr Nutrition Facts:");
        jlabel.setFont(new Font("font", Font.BOLD, 16));
        jlabel.setBounds(0, -8, 200, 50);
        jlabel.setForeground(Color.WHITE);
        sideP.add(jlabel);

        oddButtonsForMainFrame(mainP);

        foodStringTextFields(mainP);

    }

    // MODIFIES: this
    // EFFECTS: Adds keys for the productList's hashmap
    private void hashMapOrganizer() {
        productList.addFoodType("Carbohydrates");
        productList.addFoodType("Fats");
        productList.addFoodType("Proteins");

        productList.addFoodOptions("Carbohydrates", "Basmati rice");
        productList.addFoodOptions("Carbohydrates", "Jasmine rice");
        productList.addFoodOptions("Carbohydrates", "Whole wheat pasta");
        productList.addFoodOptions("Carbohydrates", "Oats");
        productList.addFoodOptions("Carbohydrates", "Pasta");
        productList.addFoodOptions("Carbohydrates", "Sweet potatoes");

        productList.addFoodOptions("Fats", "Olive oil");
        productList.addFoodOptions("Fats", "Coconut oil");
        productList.addFoodOptions("Fats", "Avocado oil");
        productList.addFoodOptions("Fats", "Egg Yolk");
        productList.addFoodOptions("Fats", "Peanut butter");

        productList.addFoodOptions("Proteins", "Chicken");
        productList.addFoodOptions("Proteins", "Beef");
        productList.addFoodOptions("Proteins", "Fish");
        productList.addFoodOptions("Proteins", "Eggs");
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializing of JTextFields that belongs to Food object which contain String values
     */
    public void foodStringTextFields(JPanel mainP) {
        txtFoodName = new JTextField();
        txtFoodName.setFont(new Font("font", Font.BOLD, 15));
        txtFoodName.setBounds(35, 145, 190, 50);
        txtFoodName.setText("Name");
        mainP.add(txtFoodName);
        txtFoodName.setColumns(10);

        foodDoubleTextFields(mainP);

        txtAll = new JTextPane();
        txtAll.setFont(new Font("font", Font.BOLD, 15));
        final JScrollPane Window = new JScrollPane(txtAll);
        Window.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Window.setLocation(280, 130);
        Window.setSize(690, 400);
        mainP.add(Window);
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializing of JTextFields that belongs to Food object which contain Double values
     */
    public void foodDoubleTextFields(JPanel mainP) {
        txtCalories = new JTextField();
        txtCalories.setFont(new Font("font", Font.BOLD, 15));
        txtCalories.setBounds(35, 260, 190, 50);
        txtCalories.setText("Calories");
        mainP.add(txtCalories);

        txtCarbs = new JTextField();
        txtCarbs.setFont(new Font("font", Font.BOLD, 15));
        txtCarbs.setBounds(35, 325, 190, 50);
        txtCarbs.setText("Carbohydrates");
        mainP.add(txtCarbs);

        txtFats = new JTextField();
        txtFats.setFont(new Font("font", Font.BOLD, 15));
        txtFats.setBounds(35, 390, 190, 50);
        txtFats.setText("Fats");
        mainP.add(txtFats);
        txtFats.setColumns(10);

        txtProteins = new JTextField();
        txtProteins.setFont(new Font("font", Font.BOLD, 15));
        txtProteins.setBounds(35, 455, 190, 50);
        txtProteins.setText("Proteins");
        mainP.add(txtProteins);
        txtProteins.setColumns(10);
    }

    /*
     * MODIFIES: this
     * EFFECTS: declaring and initializing of JButtons that belong to Main Frame
     */
    public void oddButtonsForMainFrame(JPanel mainP) {
        JButton button1 = new JButton("Find Food");
        button1.addActionListener(new ActionListener() {
            // EFFECTS: performs an action
            public void actionPerformed(ActionEvent e) {
                audioPlay();
                displayFood(productList);
            }
        });
        button1.setBounds(25, 15, 140, 35);
        button1.setFont(new Font("Aloha", Font.BOLD, 18));
        mainP.add(button1);

        evenButtonsForMainFrame(mainP);

        JButton button3 = new JButton("List Foods");
        button3.addActionListener(new ActionListener() {
            // EFFECTS: performs an action
            public void actionPerformed(ActionEvent e) {
                audioPlay();
                DietGUI.txtAll.setText(profile.toString() + "\n" + profile.printCalculation()
                        + "\n" + (productList.displayAllFoods()));
            }
        });
        button3.setFont(new Font("Aloha", Font.BOLD, 18));
        button3.setBounds(635, 15, 140, 35);
        mainP.add(button3);


        oddButtons2ForMainFrame(mainP);
    }

    /*
     * MODIFIES: this
     * EFFECTS: declaring and initializing of JButtons that belong to Main Frame
     */
    public void evenButtonsForMainFrame(JPanel mainP) {
        secondButtonForMainFrame(mainP);

        JButton button4 = new JButton("User");
        button4.addActionListener(new ActionListener() {
            // EFFECTS: performs an action
            public void actionPerformed(ActionEvent e) {
                audioPlay();
                setProfile(profile);
            }
        });
        button4.setFont(new Font("Aloha", Font.BOLD, 18));
        button4.setBounds(825, 15, 140, 35);
        mainP.add(button4);


        JButton button6 = new JButton("Save");
        button6.addActionListener(new ActionListener() {
            // EFFECTS: performs an action
            public void actionPerformed(ActionEvent e) {
                audioPlay();
                saveFiles();
            }
        });
        button6.setFont(new Font("Aloha", Font.BOLD, 18));
        button6.setBounds(115, 65, 140, 35);
        mainP.add(button6);
    }

    /*
     * MODIFIES: this
     * EFFECTS: declaring and initializing of JButton that belong to Main Frame
     */
    public void secondButtonForMainFrame(JPanel mainP) {
        JButton button2 = new JButton("Add Food");
        button2.addActionListener(new ActionListener() {
            // EFFECTS: performs an action
            public void actionPerformed(ActionEvent e) {
                audioPlay();
                addFood(productList);
            }
        });
        button2.setFont(new Font("Aloha", Font.BOLD, 18));
        button2.setBounds(225, 15, 140, 35);
        mainP.add(button2);


        JButton button8 = new JButton("Consult for Food Options");
        button8.addActionListener(new ActionListener() {
            // EFFECTS: performs an action
            public void actionPerformed(ActionEvent e) {
                audioPlay();
                suggestFood();
            }
        });
        button8.setFont(new Font("Aloha", Font.BOLD, 18));
        button8.setBounds(535, 65, 275, 35);
        mainP.add(button8);
    }

    /*
     * MODIFIES: this
     * EFFECTS: declaring and initializing of JButtons that belong to Main Frame
     */
    public void oddButtons2ForMainFrame(JPanel mainP) {
        JButton button7 = new JButton("Load");
        button7.addActionListener(new ActionListener() {
            // EFFECTS: performs an action
            public void actionPerformed(ActionEvent e) {
                audioPlay();
                loadFiles();
            }
        });
        button7.setFont(new Font("Aloha", Font.BOLD, 18));
        button7.setBounds(325, 65, 140, 35);
        mainP.add(button7);


        JButton button5 = new JButton("Remove Food");
        button5.addActionListener(new ActionListener() {
            // EFFECTS: performs an action
            public void actionPerformed(ActionEvent e) {
                audioPlay();
                removeFood(productList);
            }
        });
        button5.setFont(new Font("Aloha", Font.BOLD, 18));
        button5.setBounds(425, 15, 160, 35);
        mainP.add(button5);
    }

    /*
     * Display the information about the chosen food
     * MODIFIES: this
     * EFFECTS: initialize JTextFields of the chosen food to display the information about it
     */
    public void displayFood(FoodListing productList) {

        String name = DietGUI.txtFoodName.getText();
        int x = productList.find(name);

        try {
            String name1 = productList.returnItem(x).getName();
            String calories = valueOf(productList.returnItem(x).getCalories());
            String carbs = valueOf(productList.returnItem(x).getCarbohydrates());
            String fats = valueOf(productList.returnItem(x).getFats());
            String proteins = valueOf(productList.returnItem(x).getProteins());

            DietGUI.txtFoodName.setText(name1);
            DietGUI.txtCalories.setText(calories);
            DietGUI.txtCarbs.setText(carbs);
            DietGUI.txtFats.setText(fats);
            DietGUI.txtProteins.setText(proteins);

        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(frame, "Food doesn't exist!");
        }
    }

    // EFFECTS: Plays the wav sound file
    public void audioPlay() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("./data/clickSound.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error found while trying to play audio!");
            e.printStackTrace();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: takes the information that user enters and initialize for new Food object and adds to the list
     */
    public void addFood(FoodListing productList) {

        try {
            String name1 =  DietGUI.txtFoodName.getText();
            double calories = Double.parseDouble(DietGUI.txtCalories.getText());
            double carbs = Double.parseDouble(DietGUI.txtCarbs.getText());
            double fats = Double.parseDouble(DietGUI.txtFats.getText());
            double proteins = Double.parseDouble(DietGUI.txtProteins.getText());

            productList.addFoodToList(new Food(name1, calories, carbs, fats, proteins));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Inappropriate Food!");
        }

        txtFoodName.setText("Name");
        txtCalories.setText("Calories");
        txtCarbs.setText("Carbohydrates");
        txtFats.setText("Fats");
        txtProteins.setText("Proteins");
    }

    /*
     * MODIFIES: this
     * EFFECTS: takes the name information of the Food item that user enters and removes that Food item from the list
     */
    public void removeFood(FoodListing productList) {

        String name1 = DietGUI.txtFoodName.getText();

        if (productList.isContainsName(name1)) {
            for (int i = 0; i < productList.returnSize(); i++) {
                if (productList.returnItem(i).getName().equalsIgnoreCase(name1)) {
                    productList.removeFoodFromList(productList.returnItem(i));
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Food doesn't exist!");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: takes the user's information and sets his/her profile by mutating User object that already been declared
     *          and initialize JTextFields that belong to profile
     */
    public void setUser(User user) {
        try {
            user.setName(DietGUI.txtUserName.getText());
            user.setGenderChoice(DietGUI.txtUserGender.getText());
            user.setAge(Double.parseDouble(DietGUI.txtUserAge.getText()));
            user.setHeight(Double.parseDouble(DietGUI.txtUserHeight.getText()));
            user.setWeight(Double.parseDouble(DietGUI.txtUserWeight.getText()));
            user.fixUserActivityLevel(DietGUI.txtUserActivityChoice.getText().charAt(0));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Inappropriate User Info!");
        }

        txtUserName.setText("Name");
        txtUserGender.setText("Choose Gender:\na.Male or b.Female");
        txtUserAge.setText("Age");
        txtUserHeight.setText("Height");
        txtUserWeight.setText("Weight");
        txtUserActivityChoice.setText("Please choose your activity level:\n"
                + "\na.Little to no exercise + work a desk job\n"
                + "b.Light exercise 1-3 days / week\n" + "c.Moderate exercise 3-5 days / week\n"
                + "d.Heavy exercise 6-7 days / week\n" + "e.Very heavy exercise, hard labor job, training 2x/day");
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and opens the second frame with it's panels and buttons.
     */
    public void setProfile(User user) {

        JFrame frame = new JFrame();
        frame.setFont(new Font("PROFILE", Font.BOLD | Font.PLAIN, 12));
        frame.setTitle("USER");
        frame.setPreferredSize(new Dimension(1000,600));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JPanel mainP = new JPanel();
        frame.getContentPane().add(mainP, BorderLayout.CENTER);
        mainP.setLayout(null);
        mainP.setBackground(Color.green.darker());

        buttonsForSecondFrame(user, mainP);

        profileStringTextFields(mainP);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.validate();
    }

    /*
     * MODIFIES: this
     * EFFECTS: declaring and initializing of JButtons that belong to Second Frame
     */
    public void buttonsForSecondFrame(User user, JPanel mainP) {
        JButton button1 = new JButton("Set User");
        button1.addActionListener(new ActionListener() {
            // EFFECTS: performs an action
            public void actionPerformed(ActionEvent e) {
                audioPlay();
                setUser(user);
            }
        });
        button1.setBounds(50, 30, 150, 60);
        button1.setFont(new Font("Aloha", Font.BOLD, 18));
        mainP.add(button1);


        JButton button2 = new JButton("Save");
        button2.addActionListener(new ActionListener() {
            // EFFECTS: performs an action
            public void actionPerformed(ActionEvent e) {
                audioPlay();
                saveFiles();
            }
        });
        button2.setBounds(430, 30, 150, 60);
        button2.setFont(new Font("Aloha", Font.BOLD, 18));
        mainP.add(button2);
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializing of JTextFields that belong to User object which contain String values
     */
    public void profileStringTextFields(JPanel mainP) {
        txtUserName = new JTextField();
        txtUserName.setFont(new Font("font", Font.BOLD, 15));
        txtUserName.setBounds(25, 150, 220, 65);
        txtUserName.setText("Name");
        mainP.add(txtUserName);
        txtUserName.setColumns(10);

        txtUserGender = new JTextField();
        txtUserGender.setFont(new Font("font", Font.BOLD, 15));
        txtUserGender.setBounds(400, 250, 280, 65);
        txtUserGender.setText("Choose Gender:\na.Male or b.Female");
        mainP.add(txtUserGender);
        txtUserGender.setColumns(10);

        profileDoubleTextFields(mainP);

        txtUserActivityChoice = new JTextArea();
        txtUserActivityChoice.setFont(new Font("font", Font.BOLD, 15));
        txtUserActivityChoice.setBounds(300, 350, 700, 200);
        txtUserActivityChoice.setText("\nPlease choose your activity level:\n"
                + "\na.Little to no exercise + work a desk job\n"
                + "b.Light exercise 1-3 days / week\n" + "c.Moderate exercise 3-5 days / week\n"
                + "d.Heavy exercise 6-7 days / week\n" + "e.Very heavy exercise, hard labor job, training 2x/day");
        mainP.add(txtUserActivityChoice);
        txtUserActivityChoice.setColumns(10);
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializing of JTextFields that belong to User object which contain Double values
     */
    public void profileDoubleTextFields(JPanel mainP) {
        txtUserAge = new JTextField();
        txtUserAge.setFont(new Font("font", Font.BOLD, 15));
        txtUserAge.setBounds(25, 250, 220, 65);
        txtUserAge.setText("Age");
        mainP.add(txtUserAge);
        txtUserAge.setColumns(10);

        txtUserHeight = new JTextField();
        txtUserHeight.setFont(new Font("font", Font.BOLD, 15));
        txtUserHeight.setBounds(400, 150, 220, 65);
        txtUserHeight.setText("Height in cm");
        mainP.add(txtUserHeight);
        txtUserHeight.setColumns(10);

        txtUserWeight = new JTextField();
        txtUserWeight.setFont(new Font("font", Font.BOLD, 15));
        txtUserWeight.setBounds(25, 350, 220, 65);
        txtUserWeight.setText("Weight in kg");
        mainP.add(txtUserWeight);
        txtUserWeight.setColumns(10);
    }

    // Referenced by JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: saves the diet to file
    public void saveFiles() {
        try {
            jsonWriter.open();
            jsonWriter.write(productList);
            jsonWriter.close();
            System.out.println("Saved User's diet to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Unable to write to that file!");
        }
    }

    // Referenced by JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads diet from file
    public void loadFiles() {
        try {
            productList = jsonReader.read();
            System.out.println("Loaded " + productList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Unable to read from that file!");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates and opens the third frame with it's panels and buttons.
    public void suggestFood() {
        JFrame frame = new JFrame();
        frame.setFont(new Font("PROFILE", Font.BOLD | Font.PLAIN, 12));
        frame.setTitle("FOOD SUGGESTION");
        frame.setPreferredSize(new Dimension(1000,600));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JPanel mainP = new JPanel();
        frame.getContentPane().add(mainP, BorderLayout.CENTER);
        mainP.setLayout(null);
        mainP.setBackground(Color.blue.darker());

        hashMapOrganizer();

        buttonsForThirdFrame(mainP);

        textFieldsForThirdFrame(mainP);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.validate();
    }

    /*
     * MODIFIES: this
     * EFFECTS: declaring and initializing of JButtons that belong to Third Frame
     */
    public void buttonsForThirdFrame(JPanel mainP) {
        JButton button1 = new JButton("Consult for Food");
        button1.addActionListener(new ActionListener() {
            // EFFECTS: performs an action
            public void actionPerformed(ActionEvent e) {
                audioPlay();
                addFoodOptions(productList);
            }
        });
        button1.setFont(new Font("Aloha", Font.BOLD, 18));
        button1.setBounds(250, 35, 275, 50);
        mainP.add(button1);
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializing of JTextFields that belong to Food Suggestions frame which contain String values
     */
    public void textFieldsForThirdFrame(JPanel mainP) {
        txtFoodType = new JTextPane();
        txtFoodType.setFont(new Font("font", Font.BOLD, 15));
        txtFoodType.setText("Choose your food type to get suggestions:\n\na.Carbohydrates\nb.Fats\nc.Proteins");
        final JScrollPane Window = new JScrollPane(txtFoodType);
        Window.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Window.setLocation(20, 130);
        Window.setSize(350, 400);
        mainP.add(Window);

        txtOptions = new JTextPane();
        txtOptions.setFont(new Font("font", Font.BOLD, 15));
        final JScrollPane Window2 = new JScrollPane(txtOptions);
        Window2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Window2.setLocation(450, 130);
        Window2.setSize(350, 400);
        mainP.add(Window2);
    }

    /*
     * MODIFIES: this
     * EFFECTS: takes the information that user enters and brings the values of Food Listing object's hashmap to show
     *          suggestions for the user.
     */
    public void addFoodOptions(FoodListing list) {
        try {
            String foodType =  DietGUI.txtFoodType.getText();

            if (foodType.equalsIgnoreCase("a")) {
                DietGUI.txtOptions.setText(list.foodOptionsToString("Carbohydrates"));
            } else if (foodType.equalsIgnoreCase("b")) {
                DietGUI.txtOptions.setText(list.foodOptionsToString("Fats"));
            } else if (foodType.equalsIgnoreCase("c")) {
                DietGUI.txtOptions.setText(list.foodOptionsToString("Proteins"));
            } else {
                DietGUI.txtOptions.setText("Choose a, b or c!");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Inappropriate Food Type!");
        }

        txtFoodType.setText("Choose your food type to get suggestions:\n\na.Carbohydrates\nb.Fats\nc.Proteins");
    }
}