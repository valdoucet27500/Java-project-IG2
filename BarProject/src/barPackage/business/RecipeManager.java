package barPackage.business;

import barPackage.dataAccess.utils.RecipeDataAccess;
import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Content;
import barPackage.model.Recipe;
import javafx.collections.ObservableList;

public class RecipeManager {
    private RecipeDataAccess recipeDataAccess;

    public RecipeManager() {
        recipeDataAccess = DataConfiguration.getRecipeDataAccess();
    }

    public ObservableList<Recipe> getAllRecipes() throws ReadErrorException {
        return recipeDataAccess.getAllRecipes();
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
}
