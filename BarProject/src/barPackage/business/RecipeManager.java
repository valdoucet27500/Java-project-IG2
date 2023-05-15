package barPackage.business;

import barPackage.dataAccess.utils.RecipeDataAccess;
import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Consumable;
import barPackage.model.MissingIngredient;
import barPackage.model.Recipe;
import barPackage.model.RecipeWithConsumable;
import javafx.collections.ObservableList;

public class RecipeManager {
    private RecipeDataAccess recipeDataAccess;

    public RecipeManager() {
        recipeDataAccess = DataConfiguration.getRecipeDataAccess();
    }

    public ObservableList<Recipe> getAllRecipes() throws ReadErrorException {
        return recipeDataAccess.getAllRecipes();
    }

    public ObservableList<Recipe> getAllFavoriteRecipes() throws ReadErrorException {
        return recipeDataAccess.getAllFavoriteRecipes();
    }

    public void addRecipe(Recipe recipe) throws AddErrorException {
        recipeDataAccess.addRecipe(recipe);
    }

    public void updateRecipe(Recipe recipe, Recipe newRecipe) throws ReadErrorException {
        recipeDataAccess.updateRecipe(recipe, newRecipe);
    }

    public void deleteRecipe(Recipe recipe) throws DeleteErrorException {
        recipeDataAccess.deleteRecipe(recipe);
    }
    public ObservableList<RecipeWithConsumable> getRecipeWithConsumables(Consumable consumable) throws ReadErrorException {
        return recipeDataAccess.getRecipeWithConsumables(consumable);
    }

    public ObservableList<MissingIngredient> getMissingIngredients(Recipe recipe, double quantity) throws ReadErrorException {
        return recipeDataAccess.getMissingIngredients(recipe, quantity);
    }

    public void consumeRecipe(Recipe recipe, double parseDouble) throws UpdateErrorException {
        recipeDataAccess.consumeRecipe(recipe, parseDouble);
    }
}
