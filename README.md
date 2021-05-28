# My Personal Project : StomachGuide

## What does the application?

It helps people to count their calories and calculate their macronutrients easily.
Calculate TDEE for the user and shows how many daily calories they need according to their goal.
Lets you add your food and lets you enter it's name, calories and nutrition facts.

### *Features list*:
- TDEE (Total Daily Energy Expenditure) 
- Calorie Macronutrients tracker
- Lets you enter the food's name, calories and other micronutrients if you want to
- Entering each food to the application and it lists them by their name, calorie and macronutrients

## Who will use it?

People who want to track their calories and macros and wants to know how many calories they need to reach their goal and
reach their goals more efficiently with more scientific approach.

## Why is this project of interest to me?

I have been into fitness and nutrition for 6 years now. This is something I have enthusiasm for and 
I want to prevent people from doing the same mistakes I did when I was following my diet.



####User Stories:
- As a user, I want to be able to check the information I entered about me.
- As a user, I want to be able to add a new food to my daily diet tracker list.
- As a user, I want to be able to view the list of the foods I entered on my diet tracker list with 
  the total value of the all calories that been added.
- As a user, I want to be able to remove any food I entered from my diet tracker list.
- As a user, I want to be able to calculate how many calories do I need to take in order to loose weight, 
  gain weight or maintain my current weight.
- As a user, I want to be able to save my user information and my diet to file.
- As a user, I want to be able to be able to load my user information and my diet from file .
- As a user, I want to be able to get food recommendations for the specific macronutrient I choose. 

####Phase 4: Task 2
I have chosen to make appropriate use of the Map interface.

FoodListing class is where the Map interface was used to create hashMap. Then I used it in my GUI.

#####Methods: 
- addFoodType(String foodType)
- addFoodOptions(String foodType, String name)
- String foodOptionsToString(String foodType)

####Phase 4: Task 3
I can't think of any refactoring since my project is quite simple and basic. As it seen on UML class diagram, my project
goes around 3 class. One to keep user info, one too keep food info and the list class to keep those informations,
but my GUI looks messy. I would probably fix that and make it look better with more clear and reasonable methods. 
I would also make sure that all my methods are robust and fuction without any error in any case. After that,I would try 
to make my program run faster and more efficient.
- by removing duplications and unnecessary codes.
- by going through all my methods one by one in detail.
- by moving some of my methods from ui package to model package.
