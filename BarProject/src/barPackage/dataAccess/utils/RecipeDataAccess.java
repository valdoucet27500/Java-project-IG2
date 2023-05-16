package barPackage.dataAccess.utils;

import barPackage.exceptions.*;
import barPackage.model.Consumable;
import barPackage.model.MissingIngredient;
import barPackage.model.Recipe;
import barPackage.model.RecipeWithConsumable;
import javafx.collections.ObservableList;

public interface RecipeDataAccess {
    public ObservableList<Recipe> getAllRecipes () throws ReadErrorException;
    public ObservableList<Recipe> getAllFavoriteRecipes () throws ReadErrorException;
    public void addRecipe (Recipe recipe) throws AddErrorException;
    public void deleteRecipe (Recipe recipe) throws DeleteErrorException;
    public void updateRecipe (Recipe recipe, Recipe newRecipe) throws ReadErrorException;

    public ObservableList<RecipeWithConsumable> getRecipeWithConsumables(Consumable consumable) throws ReadErrorException;

    public ObservableList<MissingIngredient> getMissingIngredients(Recipe recipe, Double quantity) throws ReadErrorException;

    public void consumeRecipe(Recipe recipe, Double parseDouble) throws UpdateErrorException, NumberInputValueException;
}
