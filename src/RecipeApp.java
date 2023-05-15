import java.io.*;
import java.util.*;

public class RecipeApp {

    private static ArrayList<Recipe> recipes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadRecipesFromFile();
        int choice = 0;
        System.out.println("Welcome to Nonna's Kitchen"); // Welcome message to the user.
        do {
            System.out.println(); // Just for visuals
            System.out.println("Please choose an option:"); // Choosing an option from the menu.
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
            for (;;) {
                if (!scanner.hasNextInt()) {
                    System.out.println("Enter only numbers from 0 to 7!: ");
                    scanner.next(); // discard
                    continue;
                }

                else {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            addRecipe();
                            saveRecipesToFile();
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
                        case 0: // Added for not fireing the default case on exit.
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;
                }
            }
        } while (choice != 0);

    }

    private static void addRecipe() { // User add a recipe to the program and file inserting all the details.
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
        try (FileWriter writer = new FileWriter(new File("recipes.txt"), true)) {
            writer.write(
                    "\n" + recipe.getName() + ";" + recipe.getIngredients() + ";" + recipe.getDescription() + ";"
                            + recipe.getTime() + ";" + recipe.getType() + "\n");

            System.out.println("Recipes saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving recipes to file.");
        }
    }
    // Show a single recipe by name.

    private static void showOneRecipe() {
        System.out.println();
        System.out.println("Please enter the recipe name:");
        for (Recipe recipe : recipes) {
            System.out.print(recipe.getName() + ", "); // Show the names of all the recipes (I cant remember them
                                                       // otherwise)
        }
        System.out.println();
        String name = scanner.nextLine();
        System.out.println();

        for (Recipe recipe : recipes) {
            if (recipe.getName().contains(name)) {
                System.out.println(recipe);
                System.out.println();
                System.out.print("--- Press enter to continue ---");
                scanner.nextLine();
                return;
            }
        }

        System.out.println("Recipe not found.");
    }

    // Show all recipes from the list.

    private static void showAllRecipes() {
        for (Recipe recipe : recipes) {
            System.out.println(recipe);
        }
        System.out.println();
        System.out.print("--- Press enter to continue ---");
        scanner.nextLine();
    }

    // Remove a recipe from the list. The name entered must be equal to the name on
    // the list.

    private static void removeRecipe() {
        for (Recipe recipe : recipes) {
            System.out.print(recipe.getName() + "; "); // Show the names of all the recipes
        }
        System.out.println("\n" + "Please enter the recipe name you want to delete:");
        String name = scanner.nextLine();

        Iterator<Recipe> iterator = recipes.iterator();
        boolean removed = false;

        while (iterator.hasNext()) {
            Recipe recipe = iterator.next();
            if (recipe.getName().equals(name)) {
                System.out.println("Found recipe: ");
                System.out.println(recipe);

                System.out.println("Are you sure you want to delete this recipe? (yes/no)"); // ask confirmation to
                                                                                             // delete the recipe
                String confirmation = scanner.nextLine().toLowerCase();
                if (confirmation.equals("yes")) {
                    iterator.remove();
                    removed = true;
                    System.out.println("Recipe deleted from program.");

                    try {
                        // Open the input file
                        File inputFile = new File("recipes.txt");
                        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                        // Open the output file
                        File outputFile = new File("output.txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

                        String line;
                        while ((line = reader.readLine()) != null) {
                            // Check if the line has the word in the variable "name".
                            if (line.contains(name)) {
                                continue; // skip this line
                            }

                            // Write the line to the output file
                            writer.write(line);
                            writer.newLine();
                        }

                        // Close the input and output files
                        reader.close();
                        writer.close();

                        // Replace the input file with the output file
                        inputFile.delete();
                        outputFile.renameTo(inputFile);

                        System.out.println("Recipe deleted from the file");
                    } catch (IOException e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                }
            }

        }
        if (!removed) {
            System.out.println("Recipe not found.");
        }
    }

    // Save a recipe to the file

    public static void saveRecipesToFile() {

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
        for (;;) {
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

                if (!found) {
                    System.out
                            .println("No recipes found with cooking time less than or equal to " + time + " minutes.");

                }
                break;
            }
        }
    }

    // Print a list of recipies containing an Ingredient entered by the user.

    public static void typeIngredients() {
        System.out.println("Please enter an ingredient: ");
        String ingredient = scanner.nextLine();
        boolean found = false;

        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().contains(ingredient)) {
                System.out.println(recipe);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No recipes found with " + ingredient + " ingredient.");
        }
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
    }
}
