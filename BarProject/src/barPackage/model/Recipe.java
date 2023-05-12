package barPackage.model;

import barPackage.exceptions.StringInputSizeException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Recipe {
    private final static int MAX_NAME_SIZE = 64;
    private final static int MAX_DESCRIPTION_SIZE = 1024;
    private final static int MAX_STEPS_SIZE = 2048;

    private ArrayList<Ingredient> ingredients;
    private ArrayList<Utensil> utensils;
    private Integer recipeId;
    private String recipeName;
    private String steps;
    private String description;
    private LocalDate creationDate;
    private Boolean isFavorite;

    public Recipe(Integer recipeId, String recipeName, String steps, String description, LocalDate creationDate, Boolean isFavorite) {
        ingredients = new ArrayList<>();
        utensils = new ArrayList<>();
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.steps = steps;
        this.description = description;
        this.creationDate = creationDate;
        this.isFavorite = isFavorite;
    }

    public Recipe(String recipeName, String steps, String description, LocalDate creationDate, Boolean isFavorite) {
        this(null, recipeName, steps, description, creationDate, isFavorite);
    }

    public Recipe(String recipeName, String steps, String description, Boolean isFavorite) {
        this(recipeName, steps, description, LocalDate.now(), isFavorite);
    }

    public Recipe(String recipeName, String steps, String description) {
        this(recipeName, steps, description, LocalDate.now(), false);
    }

    public void setRecipeName(String recipeName) throws StringInputSizeException {
        if (recipeName.length() < 0 || recipeName.length() > MAX_NAME_SIZE) {
            throw new StringInputSizeException("La taille du nom de la recette est trop grande.", recipeName.length(), MAX_NAME_SIZE);
        }
    }

    public void setSteps(String steps) throws StringInputSizeException {
        if (steps.length() < 0 || steps.length() > MAX_STEPS_SIZE) {
            throw new StringInputSizeException("La taille des étapes de la recette est trop grande.", steps.length(), MAX_STEPS_SIZE);
        }
    }

    public void setDescription(String description) throws StringInputSizeException {
        if (description.length() < 0 || description.length() > MAX_DESCRIPTION_SIZE) {
            throw new StringInputSizeException("La taille de la description de la recette est trop grande.", description.length(), MAX_DESCRIPTION_SIZE);
        }
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void addUtensil(Utensil utensil) {
        utensils.add(utensil);
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getSteps() {
        return steps;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Utensil> getUtensils() {
        return utensils;
    }
}
