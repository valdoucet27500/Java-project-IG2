package barPackage.business;

import barPackage.dataAccess.utils.DrinkDataAccess;
import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Drink;
import javafx.collections.ObservableList;

public class DrinkManager {
    private DrinkDataAccess drinkDataAccess;
    public DrinkManager() {
        drinkDataAccess = DataConfiguration.getDrinkDataAccess();
    }

    public void addDrink(Drink drink) throws AddErrorException {
        drinkDataAccess.addDrink(drink);
    }

    public void deleteDrink(Drink drink) throws DeleteErrorException {
        drinkDataAccess.deleteDrink(drink);
    }

    public ObservableList<Drink> getAllDrinks() throws ReadErrorException {
        return drinkDataAccess.getAllDrinks();
    }

    public void updateDrink(Drink drink, Drink newDrink) throws UpdateErrorException {
        drinkDataAccess.updateDrink(drink, newDrink);
    }
}
