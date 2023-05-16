import java.io.*;
import java.util.*;

public class RecipeApp {

    public static ArrayList<Recipe> recipes = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // It load all the recipes from a file if found one.
        loadRecipesFromFile();
        int choice = 0;
        // Welcoming the user
        System.out.println("Welcome to Nonna's Kitchen");
        // Menu options.
        do {
            System.out.println();
            System.out.println("Please choose an option:");
            System.out.println("");
            System.out.println("[1] Add a new recipe");
            System.out.println("[2] Show one recipe");
            System.out.println("[3] Show all recipes");
            System.out.println("[4] Remove one recipe");
            System.out.println("[5] Show recipes by cooking time");
            System.out.println("[6] Show recipes by ingredients");
            System.out.println("[7] Show recipes by type");
            System.out.println("[0] Exit");
            System.out.print("Number: ");
            // Check if the user doesn't enter a number from 0 to 7, if he doesn't repeat the message.
            // If he does, continue to the option choosen.
            while (true) {    
                if (!scanner.hasNextInt()) {
                    System.out.println("Enter only numbers from 0 to 7!: ");
                    scanner.next();
                    pausePrint();
                    continue;
                }
                else {    
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            addRecipe();
                            break;
                        case 2:
                            showOneRecipe();
                            break;
                        case 3:
                            showAllRecipes();
                            break;
                        case 4:
                            removeRecipe();
                            break;
                        case 5:
                            typeCookingTime();
                            break;
                        case 6:
                            typeIngredients();
                            break;
                        case 7:
                            typeType();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                }
                break;
            }
        } while (choice != 0);
// Message to the user leaving the App
        System.out.println("Goodbye!");
    }
    
// Add a recipe asking to the user name, ingredients, description, time and type.
    
    public static void addRecipe() {
        System.out.println("Add recipe name:");
        String name = scanner.nextLine();

        System.out.println("Please enter the recipe description:");
        String ingredients = scanner.nextLine();

        System.out.println("Please enter the recipe ingredients:");
        String description = scanner.nextLine();

        System.out.println("Please enter the recipe cooking time:");
        String time = scanner.nextLine();

        System.out.println("Please enter the recipe type(Vegan, vegetarian...):");
        String type = scanner.nextLine();

        Recipe recipe = new Recipe(name, ingredients, description, time, type);
        recipes.add(recipe);
        saveRecipe();
    }
    // Show a single recipe by name.

    public static void showOneRecipe() {
        System.out.println();
        System.out.println("Please enter the recipe name:");
        recipeNames();
        System.out.println();
        String name = scanner.nextLine();
        System.out.println();
// Go through the list and check if there is a match with the name (Case sensitive)
        // If there is, show the recipe, otherwise give message ".. not found"
        for (Recipe recipe : recipes) {
            if (recipe.getName().contains(name)) {
                System.out.println(recipe);
                System.out.println();
                pausePrint();
                return;
            }
        }

        System.out.println("Recipe not found." + "\n");
    }

    // Show all recipes from the list.

    public static void showAllRecipes() {
        for (Recipe recipe : recipes) {
            System.out.println(recipe);
        }
        System.out.println();
        pausePrint();
    }

    // Remove a recipe from the list. The name entered must be equal to the name on
    // the list (Case sensitive)

    public static void removeRecipe() {
        System.out.println("Please enter the recipe name to be removed:");
        recipeNames();
        String name = scanner.nextLine();
    
        Iterator<Recipe> iterator = recipes.iterator();
        boolean removed = false;
    // Check in the list all the recipe with the name input from the user.
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next();
            if (recipe.getName().equals(name)) {
                System.out.println("Found recipe: ");
                System.out.println(recipe);
        // If there is a match, ask confirmation to the user, and if user input "yes", delete the recipe.
                System.out.println("Are you sure you want to delete this recipe? (yes/no)");
                String confirmation = scanner.nextLine().toLowerCase();
                if (confirmation.equals("yes")) {
                    iterator.remove();
                    removed = true;
                    System.out.println("Recipe deleted.");
                }
            }
        }
    
        if (removed) {
            saveRecipe();
            pausePrint();
        } else {
            System.out.println("Recipe not found." + "\n");
            pausePrint();
        }
    }

    // Save a recipe to the file.

    public static void saveRecipe() {
        try (FileWriter writer = new FileWriter(new File("recipes.txt"))) {
            for (Recipe recipe : recipes) {
                writer.write(
                    recipe.getName() + ";" + recipe.getIngredients() + ";" + recipe.getDescription() + ";"
                    + recipe.getTime() + ";" + recipe.getType() + "\n");
            }
            System.out.println("Recipes saved to file successfully." + "\n");
            pausePrint();

        } catch (IOException e) {
            System.out.println("Error saving recipes to file.");
        }
    }

    // Load all recipies from a file to memory

    public static void loadRecipesFromFile() {
        try (Scanner fileScanner = new Scanner(new File("recipes.txt"))) {
            recipes.clear();
// It check each line of the file and split the line in 5 (name, ingredients, description, time, type)
//  that are separeted by ;
            while (fileScanner.hasNextLine()) {
                String[] split = fileScanner.nextLine().split(";");
                Recipe recipe = new Recipe(split[0], split[1], split[2], split[3], split[4]);
                recipes.add(recipe);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Recipes file not found.");
        }
    }

    // Print a list of recipies that can be cooked in n minutes or less, where n is
    // chosen by the user.

    public static void typeCookingTime() {
        System.out.println("Please enter the max cooking time in minutes:");
        while (true) {
            // It checks if the user enter something different than an integer.
            // If the user doesn't enter an integer show the message "enter only integers"
            if (!scanner.hasNextInt()) {
                System.out.println("Enter only integers!: ");
                scanner.next(); // discard
                continue;
            }
            // If user enter an integer, the program go through the list and show the recipes with that time or less
            // If there aren't recipes with that time, show message "No recipes found..."
            else {
                int time = scanner.nextInt();
                boolean found = false;
                for (Recipe recipe : recipes) {
                    if (Integer.parseInt(recipe.getTime()) <= time) {
                        System.out.println(recipe);
                        found = true;
                    }
                }
                if (found) {
                }
                if (!found) {
                    System.out.println("No recipes found with cooking time less than or equal to " + time + " minutes.");
                }
            }
            break;
        }
    }

    // Print a list of recipies containing an Ingredient entered by the user.

    public static void typeIngredients() {
       System.out.println("Please enter an ingredient:");
        String ingredient = scanner.nextLine();
        Boolean found = false;

    // Go through the list and check if there is a match with the ingredients (Case sensitive)
    // If there is a match, shows the recipes.
        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().contains(ingredient)) {
                System.out.println(recipe);
                found = true;
            }
        }
    // If there are no matches it prints the message "No recipes found..."
        if(!found) {
        System.out.println("No recipes found with " + ingredient + " ingredient.");
        }
        
        pausePrint();
        
    }

    // Print a list of recipes that are of a type entered by the user.

    public static void typeType() {
        System.out.println("Please enter a recipe type (eg. Vegan, Vegetarian...):");
        String type = scanner.nextLine();

        boolean found = false;
        // Go through the list and check if there is a match with the type (Case sensitive)
        // If there is a match, shows the recipes.
        for (Recipe recipe : recipes) {
            if (recipe.getType().contains(type)) {
                System.out.println(recipe);
                found = true;
            }
        }
// If there are no matches it prints the message "No recipes found..."
        if (!found) {
            System.out.println("No recipes found for type: " + type);
        }
        pausePrint();
    }

    // Show the names of all the recipes (I cant remember them otherwise)

    public static void recipeNames() {
        for (Recipe recipe : recipes) {
            System.out.print(recipe.getName() + ", ");
        }
        System.out.println();
    }

    // Pause for readability

    public static void pausePrint() {
        System.out.print("--- Press enter to continue ---");
        scanner.nextLine();
    }
}
