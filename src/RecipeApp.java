import java.io.*;
import java.util.*;

public class RecipeApp {

    public static ArrayList<Recipe> recipes = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadRecipesFromFile();
        int choice = 0;
        System.out.println("Welcome to Nonna's Kitchen");
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
        } while (choice != 0);

        System.out.println("Goodbye!");
    }

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

        for (Recipe recipe : recipes) {
            if (recipe.getName().contains(name)) {
                System.out.println(recipe);
                System.out.println();
                pausePrint();
                return;
            }
        }

        System.out.println("Recipe not found.");
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
    // the list. Can we edit the code so it shows the repcipe to be deleted
    // and asks the user to confirm.

    public static void removeRecipe() {
        System.out.println("Please enter the recipe name to be removed:");
        recipeNames();
        String name = scanner.nextLine();
    
        Iterator<Recipe> iterator = recipes.iterator();
        boolean removed = false;
    
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next();
            if (recipe.getName().equals(name)) {
                System.out.println("Found recipe: ");
                System.out.println(recipe);
    
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
        } else {
            System.out.println("Recipe not found.");
        }
    }

    // Save a recipe to the file

    public static void saveRecipe() {
        try (FileWriter writer = new FileWriter(new File("recipes.txt"))) {
            for (Recipe recipe : recipes) {
                writer.write(
                    recipe.getName() + ";" + recipe.getIngredients() + ";" + recipe.getDescription() + ";"
                    + recipe.getTime() + ";" + recipe.getType() + "\n");
            }
            System.out.println("Recipes saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving recipes to file.");
        }
    }

    // Load all recipies from a file to memory

    public static void loadRecipesFromFile() {
        try (Scanner fileScanner = new Scanner(new File("recipes.txt"))) {
            recipes.clear();

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
            if (!scanner.hasNextInt()) {
                System.out.println("Enter only integers!: ");
                scanner.next(); // discard
                continue;
            }
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
                    System.out
                            .println("No recipes found with cooking time less than or equal to " + time + " minutes.");
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


        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().contains(ingredient)) {
                System.out.println(recipe);
                found = true;
            }
        }

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
        for (Recipe recipe : recipes) {
            if (recipe.getType().contains(type)) {
                System.out.println(recipe);
                found = true;
            }
        }

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