import java.io.*;
import java.util.*;

public class RecipeApp {

    private static ArrayList<Recipe> recipes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;
        System.out.println("Welcome to Nonna's Kitchen");
        do {
            System.out.println("Please choose an option:");
            System.out.println("");
            System.out.println("1. Add a new recipe");
            System.out.println("2. Show one recipe");
            System.out.println("3. Show all recipes");
            System.out.println("4. Remove one recipe");
            System.out.println("5. Show recipes by cooking time");
            System.out.println("6. Show recipes by ingredients");
            System.out.println("7. Show recipes by type");
            System.out.println("0. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addRecipe();
                    saveRecipesToFile();
                    break;
                case 2:
                    loadRecipesFromFile();
                    showOneRecipe();
                    break;
                case 3:
                    loadRecipesFromFile();
                    showAllRecipes();
                    break;
                case 4:
                    loadRecipesFromFile();
                    removeRecipe();
                    break;
                case 5:
                    loadRecipesFromFile();
                    typeCookingTime();
                    break;
                case 6:
                    loadRecipesFromFile();
                    typeIngredients();
                    break;
                case 7:
                    loadRecipesFromFile();
                    typeType();
                    break;
                case 8:
                    loadRecipesFromFile();
                    typeType();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 0);

        System.out.println("Goodbye!");
    }

    private static void addRecipe() {
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

        System.out.println("Recipe added successfully.");
    }
    // Show a single recipe by name.

    private static void showOneRecipe() {
        System.out.println("Please enter the recipe name:");
        String name = scanner.nextLine();

        for (Recipe recipe : recipes) {
            if (recipe.getName().contains(name)) {
                System.out.println(recipe);
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
    }

    // Remove a recipe from the list. The name entered must be equal to the name on
    // the list. Can we edit the code so it shows the repcipe to be deleted
    // and asks the user to confirm.

    private static void removeRecipe() {
        System.out.println("Please enter the recipe name:");
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

                    try {
                        File inputFile = new File("recipes.txt");
                        File tempFile = new File("temp.txt");

                        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                        String lineToRemove = recipe.getName() + "," + recipe.getIngredients() + ","
                                + recipe.getDescription() + ","
                                + recipe.getTime() + "," + recipe.getType();
                        String currentLine;
                        boolean found = false;

                        while ((currentLine = reader.readLine()) != null) {
                            String trimmedLine = currentLine.trim();
                            if (trimmedLine.equals(lineToRemove)) {
                                found = true;
                                continue;
                            }
                            writer.write(currentLine + System.getProperty("line.separator"));
                        }

                        writer.close();
                        reader.close();

                        if (found && !tempFile.renameTo(inputFile)) {
                            throw new IOException("Could not rename temp file to output file");
                        } else if (!found) {
                            System.out.println("Recipe not found in file.");
                        }

                    } catch (IOException e) {
                        System.out.println("Error deleting recipe from file.");
                    }
                } else {
                    System.out.println("Deletion cancelled.");
                }
                break;
            }
        }

        if (!removed) {
            System.out.println("Recipe not found.");
        }
    }

    // Save a recipe to the file

    public static void saveRecipesToFile() {
        try (FileWriter writer = new FileWriter(new File("recipes.txt"), true)) {
            for (Recipe recipe : recipes) {
                writer.write(
                        "\n" + recipe.getName() + "," + recipe.getIngredients() + "," + recipe.getDescription() + ","
                                + recipe.getTime() + "," + recipe.getType() + "\n");
            }
            System.out.println("Recipes saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving recipes to file.");
        }
    }

    // Load all recipies from a file

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
        int time = Integer.valueOf(scanner.nextLine());
        boolean found = false;
        for (Recipe recipe : recipes) {
            if (Integer.parseInt(recipe.getTime()) <= time) {
                System.out.println(recipe);
                found = true;
            }

        }

        if (!found) {
            System.out.println("No recipes found with cooking time less than or equal to " + time + " minutes.");

        }

    }

    // Print a list of recipies containing an Ingredient entered by the user.

    public static void typeIngredients() {
        System.out.println("Please enter an ingredient:");
        String ingredient = scanner.nextLine();

        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().contains(ingredient)) {
                System.out.println(recipe);
                return;
            }
        }

        System.out.println("Recipe not found.");
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
