package barPackage.dataAccess.utils;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Ingredient;
import javafx.collections.ObservableList;

public interface IngredientDataAccess {
    public void addIngredient(Ingredient ingredient) throws AddErrorException;
    public void deleteIngredient(Ingredient ingredient) throws DeleteErrorException;
    public void updateIngredient(Ingredient ingredient, Ingredient newIngredient) throws ReadErrorException;
}
