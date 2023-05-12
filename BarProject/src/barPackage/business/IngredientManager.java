package barPackage.business;

import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.dataAccess.utils.IngredientDataAccess;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Ingredient;

public class IngredientManager {
    private IngredientDataAccess ingredientDataAccess;

    public IngredientManager() {
        ingredientDataAccess = DataConfiguration.getIngredientDataAccess();
    }

    public void addIngredient(Ingredient ingredient) throws AddErrorException {
        ingredientDataAccess.addIngredient(ingredient);
    }

    public void updateIngredient(Ingredient ingredient, Ingredient newIngredient) throws ReadErrorException {
        ingredientDataAccess.updateIngredient(ingredient, newIngredient);
    }

    public void deleteIngredient(Ingredient ingredient) throws DeleteErrorException {
        ingredientDataAccess.deleteIngredient(ingredient);
    }



}
