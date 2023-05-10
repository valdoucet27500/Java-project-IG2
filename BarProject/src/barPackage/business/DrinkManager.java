package barPackage.business;

import barPackage.dataAccess.utils.DrinkDataAccess;
import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.exceptions.AddErrorException;
import barPackage.model.Drink;

public class DrinkManager {
    private DrinkDataAccess drinkDataAccess;
    public DrinkManager() {
        drinkDataAccess = DataConfiguration.getDrinkDataAccess();
    }

    public void addDrink(Drink drink) throws AddErrorException {
        drinkDataAccess.addDrink(drink);
    }
}
