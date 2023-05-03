package barPackage.business;

import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.dataAccess.utils.DrinkTypeDataAccess;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.DrinkType;
import javafx.collections.ObservableList;

public class DrinkTypeManager {
    private DrinkTypeDataAccess drinkTypeDataAccess;

    public DrinkTypeManager() {
        drinkTypeDataAccess = DataConfiguration.getDrinkTypeDataAccess();
    }
    public void addDrinkType(DrinkType drinkType) throws AddErrorException {
        drinkTypeDataAccess.addDrinkType(drinkType);
    }
    public ObservableList<DrinkType> getAllDrinkTypes() throws ReadErrorException {
        return drinkTypeDataAccess.getAllDrinkTypes();
    }
    public void deleteDrinkType(DrinkType drinkType) throws DeleteErrorException {
        drinkTypeDataAccess.deleteDrinkType(drinkType);
    }

    public void updateDrinkType(DrinkType drinkType, DrinkType newDrinkType) throws UpdateErrorException {
        drinkTypeDataAccess.updateDrinkType(drinkType,newDrinkType);
    }
}
