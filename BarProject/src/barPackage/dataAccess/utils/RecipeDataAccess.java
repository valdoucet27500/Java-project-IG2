package barPackage.dataAccess.utils;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Recipe;
import javafx.collections.ObservableList;

public interface RecipeDataAccess {
    public ObservableList<Recipe> getAllRecipes () throws ReadErrorException;
    public void addRecipe (Recipe recipe) throws AddErrorException;
    public void deleteRecipe (Recipe recipe) throws DeleteErrorException;
    public void updateRecipe (Recipe recipe, Recipe newRecipe) throws ReadErrorException;

}
