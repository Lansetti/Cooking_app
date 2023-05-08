import java.io.*;
import java.util.*;

public class RecipeApp {

    private static ArrayList<Recipe> recipes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice = 0;
        do {
            System.out.println("Please choose an option:");
            System.out.println("1. Add new recipe names");
            System.out.println("2. Show one recipe");
            System.out.println("3. Show all recipes");
            System.out.println("4. Remove one recipe");
            System.out.println("5. Save recipe names to file");
            System.out.println("6. Load recipe names from file");
            System.out.println("7. Show recipe by cooking time");
            System.out.println("8. Type the ingredients of the recipe names");
            System.out.println("0. Exit");

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
                    saveRecipesToFile();
                    break;
                case 6:
                    loadRecipesFromFile();
                    break;
                case 7:
                    typeCookingTime();
                    break;
                case 8:
                    typeIngredients();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 0);

        System.out.println("Goodbye!");
    }

    private static void addRecipe() {
        System.out.println("Please enter the recipe name:");
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

    private static void showOneRecipe() {
        System.out.println("Please enter the recipe name:");
        String name = scanner.nextLine();

        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(name)) {
                System.out.println(recipe);
                return;
            }
        }

        System.out.println("Recipe not found.");
    }

    private static void showAllRecipes() {
        for (Recipe recipe : recipes) {
            System.out.println(recipe);
        }
    }

    private static void removeRecipe() {
        System.out.println("Please enter the recipe name:");
        String name = scanner.nextLine();

        Iterator<Recipe> iterator = recipes.iterator();
        boolean removed = false;

        while (iterator.hasNext()) {
            Recipe recipe = iterator.next();
            if (recipe.getName().equals(name)) {
                iterator.remove();
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println("Recipe removed successfully.");
        } else {
            System.out.println("Recipe not found.");
        }
    }

    public static void saveRecipesToFile() {
        try (PrintWriter writer = new PrintWriter(new File("recipes.txt"))) {
            for (Recipe recipe : recipes) {
                writer.println(recipe.getName() + "," + recipe.getIngredients() + "," + recipe.getDescription() + "," + recipe.getTime() + "," + recipe.getType());
            }
            System.out.println("Recipes saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving recipes to file.");
        }
    }

    public static void loadRecipesFromFile() {
        try (Scanner fileScanner = new Scanner(new File("recipes.txt"))) {
            recipes.clear();
            
            while (fileScanner.hasNextLine()) {
                String[] split = fileScanner.nextLine().split(";");
                Recipe recipe = new Recipe(split[0], split[1], split[2], split[3], split[4]);
                recipes.add(recipe);
            }
            System.out.println("Recipes loaded from file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Recipes file not found.");
        }
    }

    public static void typeCookingTime() {
        System.out.println("Please enter the max cooking time:");
         int time = Integer.valueOf(scanner.nextLine ());

        for (Recipe recipe : recipes) {
            if (Integer.parseInt(recipe.getTime()) <= time) {
                System.out.println(recipe);
                return;
            }
            // if (Integer.parseInt(recipe.getTime()) > time) {
            //     System.out.println("Recipes not found");
            // }
            
        }

    }

    public static void typeIngredients() {
        System.out.println("Please enter the recipe name:");
        String name = scanner.nextLine();
        for (Recipe recipe: recipes) {
            if (recipe.getName().equals(name)) {
                System.out.println("Please enter the ingredients of the recipe:");
                String ingredients = scanner.nextLine();
                recipe.setIngredients(ingredients);
                System.out.println("Ingredients added successfully.");
                return;
            }
        }
        System.out.println("Recipe not found.");
    }
}

    

