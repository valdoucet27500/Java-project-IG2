package barPackage.dataAccess.utils;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Drink;
import javafx.collections.ObservableList;

public interface DrinkDataAccess {
    public void addDrink(Drink drink) throws AddErrorException;
    public ObservableList<Drink> getAllDrinks() throws ReadErrorException;
    public void deleteDrink(Drink drink) throws DeleteErrorException;
    public void updateDrink(Drink drink, Drink newDrink) throws UpdateErrorException;

}
