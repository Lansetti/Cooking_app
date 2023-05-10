public class Recipe {
    private String name;
    private String description;
    private String ingredients;
    private String time;
    private String type;

    public Recipe(String name, String description, String ingredients, String time, String type) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.time = time;
        this.type = type;
    }
    public String getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe" +
                " Name: " + name + '\n' +
                "Ingredients:" + ingredients + '\n' +
                "Description:" + description + '\n' +
                "Cooking time: " + time + " minutes" + '\n' +
                "Recipe type:" + type + '\n';
    }
    }
